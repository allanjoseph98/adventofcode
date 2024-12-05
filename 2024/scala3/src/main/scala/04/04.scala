package day4

@main def main =
  val file =
    scala.io.Source.fromResource("04/input.txt").getLines().toList
  println(part1(file))
  println(part2(file))

def countXmas(line: String, xmas: Boolean): Int =
  line.sliding(4).count(_.contains(if xmas then "XMAS" else "MAS"))

def countDir(dir: List[String], xmas: Boolean = true): Int =
  dir.map(line => countXmas(line, xmas) + countXmas(line.reverse, xmas)).sum

def makeDiag(
    file: List[String],
    below: Boolean,
    ascending: Boolean
): List[String] =
  val start = if below then 1 else 0
  (start until file.length)
    .map: lno =>
      file.zipWithIndex
        .map: (line, index) =>
          val j = index + (if below then -lno else lno)
          line.lift(if ascending then file.length - 1 - j else j)
        .flatten
        .mkString
    .toList

def part1(file: List[String]) =
  val verts = (0 until file.length)
    .map: i =>
      file.map(_.lift(i)).flatten.mkString
    .toList

  val diags = for
    bool1 <- List(true, false)
    bool2 <- List(true, false)
  yield countDir(makeDiag(file, bool1, bool2))
  val linear = file ::: verts

  countDir(linear) + diags.sum

def part2(file: List[String]): Int =
  (for
    i <- 2 until file.length
    j <- 2 until file(i).length
  yield
    val line1 = file(i - 2).substring(j - 2, j + 1)
    val line2 = file(i - 1).substring(j - 2, j + 1)
    val line3 = file(i).substring(j - 2, j + 1)
    val slides = List(line1, line2, line3)
    val descending =
      countDir(makeDiag(slides, false, false), false)
    val ascending =
      countDir(makeDiag(slides, false, true), false)
    if ascending == 1 && descending == 1 then 1 else 0
  ).sum
