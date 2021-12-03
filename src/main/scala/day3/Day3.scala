package day3

import scala.io.Source

object Day3 {
  def solve1(numbers: List[String]): Unit = {
    val gamma = (0 until numbers.head.length)
      .map(i => if numbers.count(x => x(i) == '1') >= numbers.length / 2.0 then '1' else '0')
      .mkString("")
    val epsilon = gamma.map(x => if x == '1' then '0' else '1')
    println(Integer.parseInt(epsilon, 2) * Integer.parseInt(gamma, 2))
  }

  def solve2(numbers: List[String]): Unit = {
    var (oxygen, co2) = (numbers, numbers)

    for (i <- 0 until numbers.head.length if oxygen.size > 1) {
      val dominant = if oxygen.count(x => x(i) == '1') >= oxygen.length / 2.0 then '1' else '0'
      oxygen = oxygen.filter(x => x(i) == dominant)
    }

    for (i <- 0 until numbers.head.length if co2.size > 1) {
      val notDominant = if co2.count(x => x(i) == '1') >= co2.length / 2.0 then '0' else '1'
      co2 = co2.filter(x => x(i) == notDominant)
    }

    println(Integer.parseInt(oxygen.head, 2) * Integer.parseInt(co2.head, 2))
  }

  def main(args: Array[String]): Unit = {
    val numbers = Source.fromResource("day3_input").getLines().map(_.strip()).toList
    solve1(numbers)
    solve2(numbers)
  }
}