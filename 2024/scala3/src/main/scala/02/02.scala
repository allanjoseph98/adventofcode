package day2

@main def main =
  val file =
    scala.io.Source.fromResource("02/input.txt").getLines().mkString("\n")
  println(part1(file))
  println(part2(file))

def validReport(report: List[Int], grace: Boolean): Boolean =
  val zipped = report.zip(report.tail)
  val looksIncreasing: Boolean =
    zipped.count({ case (first, second) => first < second }) >= zipped.size / 2
  if grace then
    (0 to report.length - 1).exists: index =>
      val split = report.splitAt(index)
      val oneRemoved = split._1 ++ split._2.tail
      validReport(oneRemoved, false)
  else zipped.forall(safeLevel(_, _, looksIncreasing))

def safeLevel(first: Int, second: Int, looksIncreasing: Boolean): Boolean =
  val diff = (first - second).abs
  (diff >= 1 && diff <= 3 && (if looksIncreasing then first < second
                              else first > second))

def countSafe(file: String, grace: Boolean) =
  file
    .split('\n')
    .count: report =>
      val levels = report.split(' ').map(_.toIntOption).flatten.toList
      validReport(levels, grace)

def part1(file: String) = countSafe(file, grace = false)
def part2(file: String) = countSafe(file, grace = true)
