import utils.*

object ParallelScan extends ScanInterface {

  // sequentialUpsweep get the max from the slice windows
  override def sequentialUpsweep(input: Array[Int], from: Int, until: Int): Int =
    input.slice(from, until).max

  // get the max from the slice windows (sequentially or recursively in parallel depending on the tuning threshold)
  override def upsweep(input: Array[Int], from: Int, until: Int, threshold: Int): Tree =
    if (until - from <= threshold) {
      // Base case, default to sequential upsweep
      Tree.Leaf(from, until, sequentialUpsweep(input, from, until))
    } else {
      // Recursive partial parallel reduction case
      val mid = (from + until) / 2

      val (left, right) = parallel[Tree, Tree](
        upsweep(input, from, mid, threshold),
        upsweep(input, mid, until, threshold)
      )
      Tree.Node(left, right)
    }

  // Using the startValue as base max, update the output with the max from the input or the accumulted current max
  // As result the output slice will be ordered
  override def sequentialDownsweep(input: Array[Int], output: Array[Int], startingValue: Int, from: Int, until: Int): Unit =
    var max = startingValue
    for (i <- from until until) {
      max = math.max(max, input(i))
      output(i) = max
    }

  //  downs-weep the slide sequentially or in parallel depending on the tuning threshold
  override def downsweep(input: Array[Int], output: Array[Int], startingValue: Int, tree: Tree): Unit =
    tree match {
      case Tree.Leaf(from, until, _) =>
        sequentialDownsweep(input, output, startingValue, from, until)
      case Tree.Node(left, right) =>
        downsweep(input, output, startingValue, left)
        downsweep(input, output, math.max(startingValue, left.maxPrevious), right)

    }

  override def scan(input: Array[Int], output: Array[Int], threshold: Int): Unit =
    val tree = upsweep(input, 0, input.length, threshold)
    downsweep(input, output, input(0), tree)
}
