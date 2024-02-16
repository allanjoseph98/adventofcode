package day2

class day1Tests extends munit.FunSuite {
  val testInput =
    scala.io.Source.fromResource("02/testinput.txt").getLines().mkString("\n")
  val games = parseGames(testInput).toList

  test("part1"):
    val part1Obtained = part1(games)
    val part1Expected = 8
    assertEquals(part1Obtained, part1Expected)

  test("part2"):
    val part2Obtained = part2(games)
    val part2Expected = 2286
    assertEquals(part2Obtained, part2Expected)
}
