import org.scalameter.api.*
class PerformanceBenchmark extends Bench.ForkedTime {

  val arrays = for {
    size <- Gen.range("size")(300000, 100000000, 10000000)
  } yield Array.fill(size)(util.Random.nextInt)

  measure method "SequentialScan" in {
    using(arrays)  in { array =>
      val out = Array.fill(array.length)(-1)
      SequentialScan.scan(array, out)
    }
  }
  measure method "Parallel" in {
    using(arrays)  in { array =>
      val out = Array.fill(array.length)(-1)
      ParallelScan.scan(array, out,1000000)
    }
  }
}