package day1;

class day1Tests extends munit.FunSuite {
  test("part1"):
    val testInput1 = scala.io.Source.fromResource("01/testinput1.txt")
    val part1Obtained = part1(testInput1.getLines().mkString("\n"))
    val part1Expected = 142

    assertEquals(part1Obtained, part1Expected)

  test("part2"):
    val testInput1 = scala.io.Source.fromResource("01/testinput2.txt")
    val part2Obtained = part2(testInput1.getLines().mkString("\n"))
    val part2Expected = 281

    assertEquals(part2Obtained, part2Expected)

}
