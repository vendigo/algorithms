import java.math.BigInteger

//TODO: Fix 3 failing tests
fun countTriplets(arr: Array<Long>, r: Long): String {
    val valueToIndices = arr.withIndex().groupBy({ it.value }, { it.index })

    if (r != 1L) {
        var sum = 0L

        valueToIndices.keys.forEach { first ->
            if (valueToIndices.containsKey(first)) {
                val second = first * r
                if (valueToIndices.containsKey(second)) {
                    val third = second * r
                    if (valueToIndices.containsKey(third)) {
                        sum += (valueToIndices[first]!!.size * valueToIndices[second]!!.size * valueToIndices[third]!!.size).toLong()
                    }
                }
            }
        }
        return sum.toString()
    }

    return valueToIndices.values.map { it.size.toLong() }
            .map { triplets(it) }
            .multiply()
}

private fun List<BigInteger>.multiply(): String {
    var result = BigInteger.valueOf(1L)
    this.forEach {
        result *= it
    }
    return result.toString()
}

fun triplets(size: Long): BigInteger {
    if (size < 3) {
        return BigInteger.ZERO
    }
    return BigInteger.valueOf(size) * BigInteger.valueOf(size - 1) * BigInteger.valueOf(size - 2) / BigInteger.valueOf(6)
}

fun main(args: Array<String>) {
    val nr = readLine()!!.trimEnd().split(" ")
    val n = nr[0].toInt()
    val r = nr[1].toLong()
    val arr = readLine()!!.trimEnd().split(" ").map { it.toLong() }.toTypedArray()
    val ans = countTriplets(arr, r)
    println(ans)
}
