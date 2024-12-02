package day1;

class day1Tests extends munit.FunSuite {
    val file = scala.io.Source.fromResource("01/testinput1.txt").getLines().mkString("\n")
    val lists = makeLists(file)
    test("part1"):
        assertEquals(part1.tupled(lists), 11)
    test("part2"):
        assertEquals(part2.tupled(lists), 31)
}