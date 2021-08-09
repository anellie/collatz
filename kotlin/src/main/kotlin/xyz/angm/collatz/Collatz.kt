package xyz.angm.collatz

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Long.min
import java.util.concurrent.atomic.AtomicLong

fun main(args: Array<String>) {
    val max = args[0].toLong()
    val maxMap = min(10_000_000L, max / 10L)
    val blockSize = maxMap / 1000L

    var total = 0L
    val map = LongArray(maxMap.toInt())

    for (num in 2L until maxMap) {
        val res = exec(num, num, map)
        total += res
        map[num.toInt()] = res
    }

    val atom = AtomicLong()
    runBlocking {
        for (blockStart in maxMap until max step blockSize) {
            launch(Dispatchers.Default) {
                for (num in blockStart until (blockStart + blockSize)) {
                    atom.addAndGet(exec(num, maxMap, map))
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