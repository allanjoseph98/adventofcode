package day4

class day4Tests extends munit.FunSuite {
  val file =
    scala.io.Source.fromResource("04/testinput.txt").getLines().toList
  test("part1"):
    assertEquals(part1(file), 18)
  test("part2"):
    assertEquals(part2(file), 9)
}
