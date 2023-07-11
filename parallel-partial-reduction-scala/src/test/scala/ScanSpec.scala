
class ScanSpec extends org.specs2.mutable.Specification:

  case class TestFixture(input: Array[Int], expectedOut: Array[Int], threshold: Int)
  object TestFixture {
    def fromInput(input: Array[Int], threshold: Int): TestFixture =
      val output = Array.fill(input.length)(-1)
      SequentialScan.scan(input, output)
      TestFixture(input, output, threshold)
  }


  val testSet = Seq(
    TestFixture.fromInput(Array(3, 1, 5, 2, 4), 3),
    TestFixture.fromInput(Array(3, 1, 5, 2, 4), 100000),
    TestFixture.fromInput(Array(0, 0, 1, 5, 2, 3, 6,0, 0, 7, 5, 2, 3, 8), 5),
    TestFixture.fromInput(Array(0, 0, 1, 5, 2, 3, 6,0, 0, 7, 5, 2, 3, 8), 2),
    TestFixture.fromInput(Array(0, 0, 1, 5, 2, 3, 6,0, 0, 7, 5, 2, 3, 8), 100000)
  )

  "Scan should compute the max values to the left of each index" >> {
    "In this basic example" >> {
      testSet.foreach{test =>
        var out = Array.fill(test.input.length)(-1)
        ParallelScan.scan(test.input,out, test.threshold)
        out must ===(test.expectedOut)}

    }
  }
