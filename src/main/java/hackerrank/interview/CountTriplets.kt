import java.math.BigInteger

fun countTriplets(arr: Array<Long>, r: Long): String {
    var sum = BigInteger.ZERO

    val mp2 = mutableMapOf<Long, BigInteger>()
    val mp3 = mutableMapOf<Long, BigInteger>()

    arr.forEach {
        if (mp3.containsKey(it)) {
            sum += mp3[it]!!
        }
        if (mp2.containsKey(it)) {
            mp3[it * r] = mp3.getOrDefault(it * r, BigInteger.ZERO) + mp2[it]!!
        }
        mp2[it * r] = mp2.getOrDefault(it * r, BigInteger.ZERO) + BigInteger.ONE
    }

    return sum.toString()
}


fun main(args: Array<String>) {
    val nr = readLine()!!.trimEnd().split(" ")
    val n = nr[0].toInt()
    val r = nr[1].toLong()
    val arr = readLine()!!.trimEnd().split(" ").map { it.toLong() }.toTypedArray()
    val ans = countTriplets(arr, r)
    println(ans)
}
