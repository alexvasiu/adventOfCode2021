package day1

import scala.io.Source

object Day1 {
  def getData: List[Int] =
    Source.fromResource("day1_input").getLines().map(_.toInt).toList

  def countIncreases(measurements: List[Int], offset: Int): Int =
    (0 until measurements.length - offset)
      .map(index => if measurements(index + offset) > measurements(index) then 1 else 0)
      .sum

  def solve1(): Unit =
    println(countIncreases(getData, 1))

  def solve2(): Unit =
    println(countIncreases(getData, 3))

  def main(args: Array[String]): Unit =
    solve1()
    solve2()
}
