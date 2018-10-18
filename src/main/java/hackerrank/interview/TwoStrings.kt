import java.util.*

fun twoStrings(s1: String, s2: String): String {
    val s1Chars = s1.chars().iterator().asSequence().toSet()
    val s2Chars = s2.chars().iterator().asSequence().toSet()
    return if (s1Chars.intersect(s2Chars).isNotEmpty()) {
        "YES"
    } else {
        "NO"
    }
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    val q = scan.nextLine().trim().toInt()

    for (qItr in 1..q) {
        val s1 = scan.nextLine()
        val s2 = scan.nextLine()
        val result = twoStrings(s1, s2)
        println(result)
    }
}
