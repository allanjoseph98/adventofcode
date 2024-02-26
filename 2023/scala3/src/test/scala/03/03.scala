package day3

class day1Tests extends munit.FunSuite {
  val testInput =
    scala.io.Source.fromResource("03/testinput.txt").getLines().mkString("\n")
  val prep = prepare(testInput)

  test("part1"):
    val part1Obtained = part1(prep)
    val part1Expected = 4361
    assertEquals(part1Obtained, part1Expected)

  test("part2"):
    val part2Obtained = part2(prep)
    val part2Expected = 467835
    assertEquals(part2Obtained, part2Expected)
}
