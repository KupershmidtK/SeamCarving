package seamcarving

import kotlin.math.min

class Seam {
//    private lateinit var weight: Array<DoubleArray>
//    private lateinit var path: IntArray

    fun seam(energy: Array<DoubleArray>): IntArray {
//        path = IntArray(width) { Int.MIN_VALUE }
        return shortPath(fillSumEnergy(energy))
    }

    private fun fillSumEnergy(source: Array<DoubleArray>): Array<DoubleArray> {
        val width = source.size
        val height = source[0].size
        val target = Array(width) { DoubleArray(height) { Double.NaN } }

        //target[0] = source[0]
        for (x in 0 until width) { target[x][0] = source[x][0] }

        for (y in 1 until height)
            for (x in 0 until width) {
                val left = if(x != 0) target[x - 1][y - 1] else Double.MAX_VALUE
                val up = target[x][y - 1]
                val right = if (x != width - 1) target[x + 1][y - 1] else Double.MAX_VALUE
                val min = minVal(left, up, right)
                target[x][y] = target[x + min][y - 1] + source[x][y]
            }
        return target
    }

    private fun minVal(left: Double, up: Double, right: Double): Int {
        return if (left < up) {
            if (left < right) -1 // left
            else 1 // right
        } else {
            if (up < right) 0 // up
            else 1 // right
        }
    }

    private fun shortPath(target:  Array<DoubleArray>): IntArray {
        val width = target.size
        val height = target[0].size
        val path = IntArray(height) { 0 }

//        path[height - 1] = indexOfMin(target[height - 1])
        var minIdx = 0
        var minValue = Double.MAX_VALUE
        for (i in 0 until width) {
            if (target[i][height - 1] < minValue) {
                minIdx = i
                minValue = target[i][height - 1]
            }
        }
        path[height - 1] = minIdx
        /////////////////////////////////////////////////

        for (i in height - 1 downTo 1) {
            val pos = path[i]
            val left = if(pos != 0) target[pos - 1][i - 1] else Double.MAX_VALUE
            val up = target[pos][i - 1]
            val right = if (pos != width - 1) target[pos + 1][i - 1] else Double.MAX_VALUE
            path[i - 1] = path[i] + minVal(left, up, right)
        }
        return path
    }

    private fun indexOfMin(array: DoubleArray): Int {
        var minIdx = 0
        var minValue = Double.MAX_VALUE
        for (i in 0 .. array.lastIndex) {
            if (array[i] < minValue) {
                minIdx = i
                minValue = array[i]
            }
        }
        return minIdx
    }
}