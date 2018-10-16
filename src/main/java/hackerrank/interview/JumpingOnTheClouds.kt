import java.util.*

fun jumpingOnClouds(c: Array<Int>): Int {
    return steps(c, c.size - 1)
}

fun steps(c: Array<Int>, i: Int): Int {
    if (i == 0) {
        return 0
    }
    if (c[i] == 1) {
        return Int.MAX_VALUE
    }

    if (i == 1) {
        return 1
    }

    return 1 + Math.min(steps(c, i - 1), steps(c, i - 2))
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    scan.nextLine()
    val c = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
    val result = jumpingOnClouds(c)
    println(result)
}
