package day1;
@main def main =
  val file = scala.io.Source.fromResource("01/input.txt").getLines().mkString("\n")
  val lists = makeLists(file)
  println(part1.tupled(lists))
  println(part2.tupled(lists))

def makeLists(file: String) = 
  val maybeNums = file.split('\n').map: line =>
    val spaceSplit = line.split(" ")
    List(spaceSplit.headOption.flatMap(_.toIntOption), spaceSplit.lastOption.flatMap(_.toIntOption))
  val first = maybeNums.map(_.head).flatten
  val second = maybeNums.map(_.last).flatten
  (first, second)

def part1(list1: Array[Int], list2: Array[Int]) =
  list1.sorted.zip(list2.sorted).map({case (first, second) => (first - second).abs}).sum

def part2(list1: Array[Int], list2: Array[Int]) =
  val map = list2.groupBy(identity).mapValues(_.size)
  list1.map(num => num * map.getOrElse(num, 0)).sum