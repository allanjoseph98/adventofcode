package day3

import scala.util.matching.Regex.Match

@main def main =
  val file =
    scala.io.Source.fromResource("03/input.txt").getLines().mkString("\n")
  println(part1(file))
  println(part2(file))

def groupScore(matches: Iterator[Match]) =
  matches
    .map: groups =>
      (
        groups.group("num1").toIntOption,
        groups.group("num2").toIntOption
      ) match {
        case (Some(first), Some(second)) => Option(first * second)
        case _                           => None
      }
    .flatten
    .sum

def prep(file: String) = """mul\((?<num1>\d{1,3}),(?<num2>\d{1,3})\)""".r
  .findAllMatchIn(file)

def part1(file: String) =
  groupScore(prep(file))

def part2(file: String) =
  val dos = """do\(\)""".r.findAllMatchIn(file).map(_.start).toList
  val donts = """don't\(\)""".r.findAllMatchIn(file).map(_.start).toList
  groupScore(
    prep(file)
      .filter: mul =>
        donts
          .filter(_ < mul.start)
          .maxOption
          .fold(true)(dont => dos.exists(doo => doo > dont && doo < mul.start))
  )
