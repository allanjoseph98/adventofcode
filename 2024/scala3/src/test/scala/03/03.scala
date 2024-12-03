package day3

class day3Tests extends munit.FunSuite {
  val file =
    scala.io.Source.fromResource("03/testinput.txt").getLines().mkString("\n")
  test("part1"):
    assertEquals(part1(file), 161)
  test("part2"):
    assertEquals(part2(file), 48)
}