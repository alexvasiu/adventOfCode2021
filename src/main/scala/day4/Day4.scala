package day4

import scala.io.Source
import scala.language.postfixOps
import scala.util.control.Breaks.{break, breakable}

object Day4 {
  def main(args: Array[String]): Unit = {
    val lines = Source.fromResource("day4_input").getLines().toList
    val bingoNumbers = lines.head.split(",").map(_.toInt).toList
    val players = Array.ofDim[Int]((lines.length - 1) / 6, 5, 5)
    for (index <- (2 until lines.length).by(6)) {
      val player = Array.ofDim[Int](5, 5)
      for (i <- 0 until 5) {
        players((index - 2) / 6)(i) = lines(index + i).split(" ").filter(_.nonEmpty).map(_.toInt)
      }
    }

    var winners = Set[Int]()
    var scores = Array[Int]()

    for (bingoNumber <- bingoNumbers) {
      for (player <- players.indices) {
        if (!winners.contains(player)) {
          breakable {
            for (i <- 0 until 5; j <- 0 until 5) {
              if (players(player)(i)(j) == bingoNumber) {
                players(player)(i)(j) = -1
                break
              }
            }
          }

          breakable {
            for (i <- 0 until 5) {
              if (players(player)(i).count(_ == -1) == 5 || players(player).map(_(i)).count(_ == -1) == 5) {
                winners += player
                scores :+= bingoNumber * players(player).flatten.filter(_ != -1).sum
                break
              }
            }
          }
        }
      }
    }

    println(scores.head) // Part 1
    println(scores.last) // Part 2
  }
}
