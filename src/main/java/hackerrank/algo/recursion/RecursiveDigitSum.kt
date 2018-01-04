package hackerrank.algo.recursion

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val n = scanner.next()
    val k = scanner.nextInt()

    var res = (sum(n) * k).toString()
    while (res.length > 1) {
        res = sum(res).toString()
    }

    println(res)
    scanner.close()
}

fun sum(s: String): Long {
    return s.toCharArray()
            .map { it.toString().toLong() }
            .sum()
}

