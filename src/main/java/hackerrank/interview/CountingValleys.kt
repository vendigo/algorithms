import java.util.*

fun countingValleys(n: Int, s: String): Int {
    var prevLevel = 0
    var level = 0
    var valleys = 0
    s.chars().forEach {
        if (it.toChar() == 'D') {
            level--
        } else {
            level++
        }

        if (prevLevel == 0 && level == -1) {
            valleys++
        }

        prevLevel = level
    }
    return valleys
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    val n = scan.nextLine().trim().toInt()
    val s = scan.nextLine()
    val result = countingValleys(n, s)
    println(result)
}
