package day1;

import scala.util.matching.Regex.Match
@main def main =
  val file1 =
    scala.io.Source.fromResource("01/input.txt").getLines().mkString("\n")
  val file2 =
    scala.io.Source.fromResource("01/input2.txt").getLines().mkString("\n")
  println(part1(file2))
  println(part2(file1))

def lineToNum(line: String) =
  val digits = line
    .filter(_.isDigit)
  if digits.isEmpty() then None else s"${digits.head}${digits.last}".toIntOption

def part1(input: String) = input
  .split("\n")
  .map(lineToNum)
  .map(_.getOrElse(0))
  .sum

val numMap = List(
  "zero",
  "one",
  "two",
  "three",
  "four",
  "five",
  "six",
  "seven",
  "eight",
  "nine"
).zipWithIndex.toMap

def matchToNum(partial: String) =
  for i <- 0 to partial.length()
  yield
    val trunc = partial.substring(0, i)
    if numMap.contains(trunc) then numMap.get(trunc)
    else trunc.toIntOption

def part2(input: String) =
  val numRegex =
    ("(?=(" + (numMap.map(_._1).mkString("|") + """|\d""") + "))").r
  input
    .split("\n")
    .map: line =>
      val matches = line.tails.map(matchToNum).toList.flatten.flatten
      (matches.headOption, matches.lastOption) match {
        case (Some(_1), Some(_2)) => s"${_1}${_2}".toIntOption
        case _                    => None
      }
    .map(_.getOrElse(0))
    .sum
