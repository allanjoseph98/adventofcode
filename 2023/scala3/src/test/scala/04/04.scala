package day4

class day4Tests extends munit.FunSuite {
  val testInput =
    scala.io.Source.fromResource("04/testinput.txt").getLines().mkString("\n")
  val prepared = prep(testInput)

  test("part1"):
    val part1Obtained = part1(prepared)
    val part1Expected = 13
    assertEquals(part1Obtained, part1Expected)

  test("part2"):
    val part2Obtained = part2(prepared)
    val part2Expected = 30
    assertEquals(part2Obtained, part2Expected)
}
