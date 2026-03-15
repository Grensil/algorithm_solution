package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class GreedySolution {

    // ==================== 귤 고르기 (Level 2) ====================
    // k개의 귤을 고를 때 크기 종류를 최소화하는 방법 반환
    // 개수가 많은 크기부터 담아 종류 수를 최소화
    fun tangerineSolution(k: Int, tangerine: IntArray): Int {
        val countMap = mutableMapOf<Int, Int>()

        for (x in tangerine) {
            countMap[x] = (countMap[x] ?: 0) + 1
        }

        val countList = countMap.values.sortedDescending()

        var sum = 0
        var count = 0
        for (x in countList) {
            sum += x
            count += 1
            if (sum >= k) return count
        }

        return count
    }

    // ==================== 테스트 ====================

    @Test
    fun testGreedySolution() {
        // 귤 고르기
        assertEquals(3, tangerineSolution(6, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)))
        assertEquals(2, tangerineSolution(4, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)))
        assertEquals(1, tangerineSolution(2, intArrayOf(1, 1, 1, 1, 2, 2, 2, 3)))
    }
}
