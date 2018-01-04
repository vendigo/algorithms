package hackerrank.algo.recursion

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val t = scanner.nextInt()

    repeat(t) {
        val n = scanner.nextInt()
        val passwords = (1..n).asSequence().map { scanner.next() }.toList()
        val acctPass = scanner.next()
        val foundPasswords = mutableListOf<String>()
        val cache = mutableMapOf<String, Boolean>()
        val hacked = hackPassword(passwords, acctPass, foundPasswords, cache)
        if (hacked) {
            println(foundPasswords.reversed().joinToString(separator = " "))
        } else {
            println("WRONG PASSWORD")
        }
    }
    scanner.close()
}

fun hackPassword(passwords: List<String>, actPass: String, foundPasswords: MutableList<String>,
                 cache: MutableMap<String, Boolean>): Boolean {
    if (actPass in cache) {
        return cache[actPass] as Boolean
    }

    if (actPass.isEmpty()) {
        return true
    }

    var found = false

    for (pass in passwords) {
        val ind = actPass.indexOf(pass)
        if (ind == 0) {
            val remainingPass = actPass.substring(pass.length)
            found = hackPassword(passwords, remainingPass, foundPasswords, cache)
            cache[actPass] = found
        }
        if (found) {
            foundPasswords += pass
            break
        }
    }

    if (!found) {
        foundPasswords.clear()
    }
    cache[actPass] = found
    return found
}

