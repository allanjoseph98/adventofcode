package day2

enum Colors:
  case Red, Green, Blue

case class Draw(cubes: Int, color: Colors)

case class Round(draws: List[Draw])

case class Game(id: Int, rounds: List[Round])

@main
def main =
  val file =
    scala.io.Source.fromResource("02/input.txt").getLines().mkString("\n")
  val games = parseGames(file).toList
  println(part1(games))
  println(part2(games))

val GameRegex = """(?i)game\s(?<id>\d+)""".r
val DrawRegex = """(?i)(?<cubes>\d+)\s(?<color>red|green|blue)""".r
val limit = Round(
  List(Draw(12, Colors.Red), Draw(13, Colors.Green), Draw(14, Colors.Blue))
)

def drawStrtoDrawOpt(draw: String) =
  DrawRegex
    .findFirstMatchIn(draw)
    .flatMap: drawMatch =>
      val colorStr = drawMatch.group("color")
      val color: Colors =
        if colorStr.equalsIgnoreCase("red") then Colors.Red
        else if colorStr.equalsIgnoreCase("green") then Colors.Green
        else Colors.Blue
      val cubes = drawMatch.group("cubes").toIntOption
      cubes match {
        case Some(cubes) => Some(Draw(cubes, color))
        case _           => None
      }

def parseGames(input: String) = input
  .split("\n")
  .map: line =>
    val id = GameRegex
      .findFirstMatchIn(line)
      .map(_.group("id"))
      .flatMap(_.toIntOption)
      .getOrElse(-1)
    val rounds = line
      .split(";")
      .map(
        _.split(",")
          .map(drawStrtoDrawOpt)
          .flatten
          .toList
      )
      .map(Round(_))
      .toList
    Game(id, rounds)

def part1(games: List[Game]) =
  games
    .filter: game =>
      game.rounds
        .flatMap(round =>
          for
            draw <- round.draws
            limit <- limit.draws
          yield draw.color == limit.color && draw.cubes > limit.cubes
        )
        .forall(fail => !fail)
    .map(_.id)
    .sum

def part2(games: List[Game]) =
  games
    .map: game =>
      Colors.values
        .map: color =>
          game.rounds
            .flatMap(_.draws.filter(_.color == color))
            .maxBy(_.cubes)
            .cubes
        .product
    .sum
