package hackerrank.algo.dynamic

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()
    val cache = mutableMapOf<Int, Int>()

    repeat(t) {
        val n = scanner.nextInt()
        val counts = (1..n).asSequence().map { scanner.nextInt() }.toList()
        println(solution(counts, cache))
    }

    scanner.close()
}

fun solution(counts: List<Int>, cache: MutableMap<Int, Int>): Long {
    val minElement = counts.min() as Int
    val baselines = minElement - 4..minElement

    return baselines.map { baseline ->
        counts.mySumByLong { number -> difference(number, baseline, cache).toLong() }
    }.min() as Long
}

fun difference(number: Int, baseline: Int, cache: MutableMap<Int, Int>): Int {
    val key = number - baseline
    if (key in cache) {
        return cache[key]!!
    }

    var result = 0
    var rest = number - baseline

    for (step in listOf(5, 2, 1)) {
        while (rest >= step && rest > 0) {
            rest -= step
            result++
        }
    }

    cache[key] = result
    return result
}

inline fun <T> Iterable<T>.mySumByLong(selector: (T) -> Long): Long {
    val sum: Long = this
            .map { selector(it) }
            .sum()
    return sum
}