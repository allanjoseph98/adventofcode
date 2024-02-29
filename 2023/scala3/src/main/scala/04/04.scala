package day4

@main
def main =
  val file =
    scala.io.Source.fromResource("04/input.txt").getLines().mkString("\n")
  val prepared = prep(file)
  println(part1(prepared))
  println(part2(prepared))

case class Card(winners: Array[Int], draw: Array[Int])
case class Copy(id: Int, bonuses: List[Int], var copies: Int = 1)

def toIntArr(line: String): Array[Int] =
  line.split(' ').map(_.toIntOption).flatten

def prep(input: String) =
  input
    .split('\n')
    .map: line =>
      val split = line.split('|')
      (
        split.headOption.map(toIntArr),
        split.lastOption.map(toIntArr)
      ) match {
        case (Some(winners), Some(draw)) => Some(Card(winners, draw))
        case _                           => None
      }
    .flatten

def wins(card: Card): Int =
  card.winners.count(card.draw.contains(_))

def score(card: Card): Int =
  val pow = wins(card) - 1
  if pow >= 0 then Math.pow(2, pow).toInt else 0

def part1(prep: Array[Card]) =
  prep.map(score).sum

def part2(prep: Array[Card]) =
  val bonuses = prep
    .map(wins)
    .zipWithIndex
    .map({ case (win, index) =>
      Copy(index + 1, List.range(index + 2, index + 2 + win))
    })
  for
    copy <- bonuses
    child <- copy.bonuses
  do
    bonuses.find(_.id == child) match {
      case Some(orig) => orig.copies += copy.copies
      case None       => None
    }
  bonuses.map(_.copies).sum
