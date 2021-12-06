package day5

import scala.io.Source

object Day5 {
  def getNumberOfOverlaps(segments: List[((Int, Int), (Int, Int))]): Int = {
    var pointsOverlap = Map[(Int, Int), Int]()

    for (segment <- segments) {
      var points = Array[(Int, Int)](segment(0))
      val step = (segment(1)(0).compareTo(segment(0)(0)), segment(1)(1).compareTo(segment(0)(1)))
      var start: (Int, Int) = segment(0)

      while (start != segment(1)) {
        start = (start(0) + step(0), start(1) + step(1))
        points :+= start
      }

      for (point <- points) {
        pointsOverlap = pointsOverlap.updated(point, pointsOverlap.getOrElse(point, 0) + 1)
      }
    }

    pointsOverlap.count(p => p._2 > 1)
  }

  def main(args: Array[String]): Unit = {
    val data = Source.fromResource("day5_input").getLines().map(line => {
      val values = line.split(" -> ")
      val left = values(0).split(",")
      val right = values(1).split(",")

      ((left(0).toInt, left(1).toInt), (right(0).toInt, right(1).toInt))
    }).toList

    println(getNumberOfOverlaps(data.filter(seg => seg(0)(0) == seg(1)(0) || seg(0)(1) == seg(1)(1)))) // Part 1
    println(getNumberOfOverlaps(data)) // Part 2
  }
}
