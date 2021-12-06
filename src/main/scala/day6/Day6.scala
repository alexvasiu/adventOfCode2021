package day6

import scala.io.Source

object Day6 {
  def calculate(nrs: List[Int], days: Int): Long = {
    var data = nrs.groupBy(x => x).map(x => (x._1, x._2.size.toLong))

    (0 until days).foreach(_ => {
      if (data.contains(0)) {
        data = data.updated(7, data.getOrElse(7, 0L) + data.getOrElse(0, 0L))
        data = data.updated(9, data.getOrElse(9, 0L) + data.getOrElse(0, 0L))
        data = data.updated(0, 0L)
      }

      data = data.map((key, value) => (key - 1, value))
    })

    data.values.sum
  }

  def main(args: Array[String]): Unit = {
    val nrs = Source.fromResource("day6_input").getLines().toList.head.split(",").map(_.toInt).toList

    println(calculate(nrs, 80))  // Part 1
    println(calculate(nrs, 256)) // Part 2
  }
}