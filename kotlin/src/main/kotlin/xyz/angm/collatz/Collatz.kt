package xyz.angm.collatz

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicLong

const val MAX = 10_000_000L
const val MAX_MAP = 1_000_000L
const val BLOCK_SIZE = 1000L

fun main() {
    var total = 0L
    val map = LongArray(MAX_MAP.toInt())

    for (num in 2L until MAX_MAP) {
        val res = exec(num, num, map)
        total += res
        map[num.toInt()] = res
    }

    val atom = AtomicLong()
    runBlocking {
        for (blockStart in MAX_MAP until MAX step BLOCK_SIZE) {
            launch(Dispatchers.Default) {
                for (num in blockStart until (blockStart + BLOCK_SIZE)) {
                    atom.addAndGet(exec(num, MAX_MAP, map))
                }
            }
        }
    }

    println(total + atom.get())
}

fun exec(initial: Long, minBound: Long, map: LongArray): Long {
    var stepCount = 0
    var current = initial
    while (current >= minBound) {
        if ((current and 1) == 0L) current /= 2
        else current = (current * 3) + 1
        stepCount++
    }
    return stepCount + map[current.toInt()]
}