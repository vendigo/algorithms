import java.util.*

fun repeatedString(s: String, n: Long): Long {
    val indices = countAs(s)
    val fullA = indices.last()
    val len = s.length

    val fullPart = n / len * fullA
    val rest = (n % len).toInt()

    return fullPart +
            if (rest != 0) {
                indices[rest - 1]
            } else 0
}

fun countAs(s: String): Array<Int> {
    val indices = Array(s.length) { 0 }
    var foundA = 0

    (0 until indices.size).forEach { i ->
        val ch = s[i]
        if (ch == 'a') {
            foundA++
        }
        indices[i] = foundA
    }

    return indices
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    val s = scan.nextLine()
    val n = scan.nextLine().trim().toLong()
    val result = repeatedString(s, n)
    println(result)
}