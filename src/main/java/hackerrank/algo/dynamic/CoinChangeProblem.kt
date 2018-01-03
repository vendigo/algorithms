package hackerrank.algo.dynamic

import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val money = scanner.nextInt()
    val coinCount = scanner.nextInt()
    val coins = (1..coinCount).asSequence().map { scanner.nextInt() }.toList()
    println(numWays(money, coins))
}

fun numWays(money: Int, coins: List<Int>): Long {
    return numWays(money, coins, 0, HashMap())
}

fun numWays(money: Int, coins: List<Int>, coinIndex: Int, cache: MutableMap<String, Long>): Long {
    if (money == 0) {
        return 1
    }

    if (coinIndex >= coins.size) {
        return 0
    }

    val key = "$money-$coinIndex"
    if (key in cache) {
        return cache[key] as Long
    }

    var amountWithCoin = 0
    var ways = 0L
    while (amountWithCoin <= money) {
        val remaining = money - amountWithCoin
        ways += numWays(remaining, coins, coinIndex + 1, cache)
        amountWithCoin += coins[coinIndex]
    }

    cache.putIfAbsent(key, ways)
    return ways
}
