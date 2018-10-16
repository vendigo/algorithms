import java.util.*

fun sockMerchant(ar: List<Int>): Int {
    return ar.groupingBy { it }.eachCount()
            .values
            .map { it / 2 }
            .sum()
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val ar = (1..n).asSequence().map { scan.nextInt() }.toList()
    val result = sockMerchant(ar)
    println(result)
}
