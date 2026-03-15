package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.LinkedList
import java.util.Queue

class BFSSolution {

    // ==================== 게임 맵 최단거리 (Level 2) ====================
    // (0,0) 에서 (n-1,m-1) 까지 이동하는 최단 거리 반환, 도달 불가 시 -1
    // BFS로 상하좌우 탐색, 방문한 칸은 재방문 금지
    fun solution1(maps: Array<IntArray>): Int {

        val n = maps.size
        val m = maps[0].size
        val dist = Array(n) { IntArray(m) { -1 } }
        val queue: Queue<Pair<Int,Int>> = LinkedList()

        dist[0][0] = 1
        queue.add(Pair(0, 0))

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            // 상
            if (r-1 >= 0 && dist[r-1][c] == -1 && maps[r-1][c] == 1) {
                dist[r-1][c] = dist[r][c] + 1
                queue.add(Pair(r-1, c))
            }
            // 하
            if (r+1 < n && dist[r+1][c] == -1 && maps[r+1][c] == 1) {
                dist[r+1][c] = dist[r][c] + 1
                queue.add(Pair(r+1, c))
            }
            // 좌
            if (c-1 >= 0 && dist[r][c-1] == -1 && maps[r][c-1] == 1) {
                dist[r][c-1] = dist[r][c] + 1
                queue.add(Pair(r, c-1))
            }
            // 우
            if (c+1 < m && dist[r][c+1] == -1 && maps[r][c+1] == 1) {
                dist[r][c+1] = dist[r][c] + 1
                queue.add(Pair(r, c+1))
            }
        }

        return dist[n-1][m-1]
    }

    // ==================== 테스트 ====================

    @Test
    fun testBFSSolution() {
        // 게임 맵 최단거리
        assertEquals(11, solution1(arrayOf(intArrayOf(1,0,1,1,1), intArrayOf(1,0,1,0,1), intArrayOf(1,0,1,1,1), intArrayOf(1,1,1,0,1), intArrayOf(0,0,0,0,1))))
        assertEquals(-1, solution1(arrayOf(intArrayOf(1,0,1,1,1), intArrayOf(1,0,1,0,1), intArrayOf(1,0,1,1,1), intArrayOf(1,1,1,0,0), intArrayOf(0,0,0,0,1))))
    }
}
