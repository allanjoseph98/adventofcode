package day2

class day2Tests extends munit.FunSuite {
  val file =
    scala.io.Source.fromResource("02/testinput.txt").getLines().mkString("\n")
  test("part1"):
    assertEquals(part1(file), 2)
  test("part2"):
    assertEquals(part2(file), 4)
}
