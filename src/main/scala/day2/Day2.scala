package day2

import scala.io.Source

object Day2 {
  def calculate(function: List[(String, Int)] => Long): Unit = {
    val data = Source.fromResource("day2_input").getLines().map(line => {
      val values = line.strip().split(" ")
      (values(0), values(1).toInt)
    }).toList

    println(function(data))
  }

  def solve1(): Unit = {
    calculate(data => {
      var (h, depth) = (0, 0)

      for (pair <- data) {
        pair(0) match {
          case "forward" => h += pair(1)
          case "down" => depth += pair(1)
          case "up" => depth -= pair(1)
        }
      }

      h * depth
    })
  }

  def solve2(): Unit = {
    calculate(data => {
      var (h, aim, depth) = (0, 0, 0)

      for (pair <- data) {
        pair(0) match {
          case "forward" => h += pair(1); depth += aim * pair(1)
          case "down" => aim += pair(1)
          case "up" => aim -= pair(1)
        }
      }

      h * depth
    })
  }

  def main(args: Array[String]): Unit = {
    solve1()
    solve2()
  }
}
