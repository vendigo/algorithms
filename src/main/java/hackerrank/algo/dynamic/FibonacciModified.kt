package hackerrank.algo.dynamic

import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t1 = scanner.nextInt()
    val t2 = scanner.nextInt()
    val n = scanner.nextInt()

    println(fiboModified(t1, t2, n))

    scanner.close()
}

fun fiboModified(t1: Int, t2: Int, n: Int): BigInteger {
    var pprev = BigInteger(t1.toString())
    var prev = BigInteger(t2.toString())

    for (i in 3..n) {
        val current = pprev + prev.pow(2)
        pprev = prev
        prev = current
    }

    return prev
}

