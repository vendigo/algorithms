package hackerrank.algo.greedy

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val k = scanner.nextInt()
    val c = (1..n).asSequence().map { scanner.nextLong() }.sorted().toList()

    var sum = 0L
    var i = 0
    while (sum < k) {
        sum += c[i++]
    }

    println(i - 1)
    scanner.close()
}