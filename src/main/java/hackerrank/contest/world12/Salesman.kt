package hackerrank.contest.world12

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()

    repeat(t) {
        val n = scanner.nextInt()
        val xArr = (1..n).asSequence()
                .map { scanner.nextInt() }
                .sorted()
                .toList()
        val result = xArr.max() as Int - xArr.min() as Int
        println(result)
    }
}