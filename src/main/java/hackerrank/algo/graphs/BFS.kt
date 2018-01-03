import java.util.*
import kotlin.collections.HashSet

const val EDGE_WEIGHT = 6

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val queriesCount = scanner.nextInt()

    repeat(queriesCount) {
        val nodesCount = scanner.nextInt()
        val edgesCount = scanner.nextInt()

        val graph = mutableMapOf<Int, Node>()
        for (i in 1..nodesCount) {
            graph[i] = Node(i)
        }

        repeat(edgesCount) {
            val startNode = scanner.nextInt()
            val endNode = scanner.nextInt()
            graph[startNode]?.addNeighbor(graph[endNode] as Node)
        }

        val start = scanner.nextInt()
        findDistances(graph[start])

        (1..nodesCount)
                .filter { it != start }
                .forEach { print("${graph[it]?.distance} ") }
        println()
    }
    scanner.close()
}

fun findDistances(start: Node?) {
    if (start == null) {
        return
    }

    val deque = ArrayDeque<Node>()
    start.distance = 0
    deque.add(start)

    while (!deque.isEmpty()) {
        val curr = deque.remove()
        for (neighbor in curr.neighbors) {
            if (neighbor.distance == -1) {
                neighbor.distance = curr.distance + EDGE_WEIGHT
                deque.add(neighbor)
            }
        }
    }
}

data class Node(val id: Int) {
    var distance: Int = -1
    var neighbors: MutableSet<Node> = HashSet()

    fun addNeighbor(neighbor: Node) {
        neighbors.add(neighbor)
        neighbor.neighbors.add(this)
    }
}