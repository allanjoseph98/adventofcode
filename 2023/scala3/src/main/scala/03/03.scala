package day3

import scala.util.matching.Regex.Match

@main
def main =
  val file =
    scala.io.Source.fromResource("03/input.txt").getLines().mkString("\n")
  val prep = prepare(file)
  println(part1(prep))
  println(part2(prep))

def isSymbol(c: Char) = !c.isDigit && c != '.'
def safeLno(i: Int, most: Int): Int = Math.max(Math.min(i, most), 0)
def within(a: Int, start: Int, end: Int): Boolean = start <= a && a <= end
val numRegex = """\d+""".r

case class SymbolWithNum(coords: Coords, num: Int)

case class Coords(lno: Int, index: Int)

def getHits(numMatch: Match, lines: Array[String], line: String, lno: Int) =
  val startMinusOne = safeLno(numMatch.start - 1, line.length() - 1)
  val endPlusOne = safeLno(numMatch.end, line.length() - 1)
  val left = isSymbol(line.charAt(startMinusOne))
  val right = isSymbol(line.charAt(endPlusOne))
  def otherLineSymbolIndices(i: Int) =
    lines(safeLno(i, lines.length - 1)).zipWithIndex
      .map((char: Char, index: Int) =>
        if isSymbol(char) then Some(index) else None
      )
      .flatten
  def withinCond(s: Int) =
    s >= 0 && s < lines.length && within(s, startMinusOne, endPlusOne)
  val above = otherLineSymbolIndices(lno - 1).find(withinCond)
  val below = otherLineSymbolIndices(lno + 1).find(withinCond)
  val aboveCoords = above.map(Coords(safeLno(lno - 1, line.length()), _))
  val belowCoords = below.map(Coords(safeLno(lno + 1, line.length()), _))
  val rightCoords = if left then Some(Coords(lno, startMinusOne)) else None
  val leftCoords = if right then Some(Coords(lno, endPlusOne)) else None
  numMatch.matched.toIntOption.flatMap(num =>
    List(aboveCoords, belowCoords, rightCoords, leftCoords).flatten.headOption
      .map(SymbolWithNum(_, num))
  )

def prepare(input: String) =
  val lines = input.split("\n")
  lines.zipWithIndex
    .flatMap: (line: String, lno: Int) =>
      numRegex
        .findAllMatchIn(line)
        .map(getHits(_, lines, line, lno))

def part1(hits: Array[Option[SymbolWithNum]]) =
  hits.flatMap(_.map(_.num)).sum

def part2(hits: Array[Option[SymbolWithNum]]) =
  val allCoords = hits.flatten.map(_.coords)
  val duplicates = allCoords.diff(allCoords.distinct)
  hits.flatten
    .filter(e => duplicates.contains(e.coords))
    .groupBy(_.coords)
    .filter(_._2.length == 2)
    .map(g => g._2.map(_.num).product)
    .sum
