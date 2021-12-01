package day1

import scala.io.Source

object Day1 {
  def countIncreases(measurements: List[Int], offset: Int): Int =
    (0 until measurements.length - offset)
      .count(index => measurements(index + offset) > measurements(index))

  def main(args: Array[String]): Unit =
    val measurements = Source.fromResource("day1_input").getLines().map(_.toInt).toList

    println(countIncreases(measurements, 1))  // Part 1
    println(countIncreases(measurements, 3))  // Part 2
}
