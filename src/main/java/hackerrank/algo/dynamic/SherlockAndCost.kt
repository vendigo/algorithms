package hackerrank.algo.dynamic

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()

    repeat(t) {
        val n = scanner.nextInt()
        val b = (1..n).asSequence().map { scanner.nextInt() }.toList()
        println(maxS(b))
    }

    scanner.close()
}

fun maxS(b: List<Int>): Int {
    var high = 0
    var low = 0

    for (i in 1 until b.size) {
        val highToLow = Math.abs(b[i - 1] - 1)
        val lowToHigh = Math.abs(b[i] - 1)

        val lowNext = Math.max(low, high + highToLow)
        val highNext = Math.max(high, low + lowToHigh)

        low = lowNext
        high = highNext
    }

    return Math.max(high, low)
}