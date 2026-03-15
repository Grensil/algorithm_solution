package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class BFSSolution {

    // ==================== 게임 맵 최단거리 (Level 2) ====================
    // (0,0) 에서 (n-1,m-1) 까지 이동하는 최단 거리 반환, 도달 불가 시 -1
    // BFS로 상하좌우 탐색, 방문한 칸은 재방문 금지
    fun solution1(maps: Array<IntArray>): Int {
        // TODO
        return -1
    }

    // ==================== 테스트 ====================

    @Test
    fun testBFSSolution() {
        // 게임 맵 최단거리
        assertEquals(11, solution1(arrayOf(intArrayOf(1,0,1,1,1), intArrayOf(1,0,1,0,1), intArrayOf(1,0,1,1,1), intArrayOf(1,1,1,0,1), intArrayOf(0,0,0,0,1))))
        assertEquals(-1, solution1(arrayOf(intArrayOf(1,0,1,1,1), intArrayOf(1,0,1,0,1), intArrayOf(1,0,1,1,1), intArrayOf(1,1,1,0,0), intArrayOf(0,0,0,0,1))))
    }
}
