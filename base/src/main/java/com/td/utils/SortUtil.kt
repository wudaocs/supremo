package com.td.utils

object SortUtil {
    private fun quickSort(arrs: IntArray, start: Int, end: Int): IntArray {
        var arr = arrs
        val pivot = arr[start]
        var i = start
        var j = end
        while (i < j) {
            while (i < j && arr[j] > pivot) {
                j--
            }
            while (i < j && arr[i] < pivot) {
                i++
            }
            if (arr[i] == arr[j] && i < j) {
                i++
            } else {
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
        if (i - 1 > start) arr = quickSort(arr, start, i - 1)
        if (j + 1 < end) arr = quickSort(arr, j + 1, end)
        return arr
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var arr = intArrayOf(3, 3, 3, 7, 9, 5, 6, 7, 8, 9, 1)
        val len = arr.size - 1
        arr = quickSort(arr, 0, len)
        for (i in arr) {
            print(i.toString() + "\t")
        }
    }
}