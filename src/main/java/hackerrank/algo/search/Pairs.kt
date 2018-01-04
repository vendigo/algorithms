package hackerrank.algo.search

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val k = scanner.nextLong()
    val a = (1..n).asSequence().map { scanner.nextLong() }.sorted().toList()

    val count = (0..n - 2)
            .map { a.binarySearch(k + a[it], it, n) }
            .count { it > 0 }

    println(count)
    scanner.close()
}