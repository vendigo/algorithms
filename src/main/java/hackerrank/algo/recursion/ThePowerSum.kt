package hackerrank.algo.recursion

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val x = scanner.nextInt()
    val n = scanner.nextInt()

    println(nWays(x, n, 1))
    scanner.close()
}

fun nWays(total: Int, power: Int, num: Int): Long {
    val v = total - num.pow(power)

    if (v < 0) {
        return 0
    } else if (v == 0) {
        return 1
    } else {
        return nWays(v, power, num + 1) + nWays(total, power, num + 1)
    }
}

fun Int.pow(power: Int): Int {
    var res = this
    for (i in 2..power) {
        res *= this
    }
    return res
}