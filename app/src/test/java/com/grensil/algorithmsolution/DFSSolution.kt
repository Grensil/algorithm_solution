package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class DFSSolution {

    // ==================== 타겟 넘버 (Level 2) ====================
    // 숫자를 순서대로 더하거나 빼서 target을 만드는 방법의 수 반환
    // DFS로 각 숫자마다 +/- 두 가지 경우를 탐색
    fun solution1(numbers: IntArray, target: Int): Int {
        return dfs1(numbers, target, 0, 0)
    }

    fun dfs1(numbers: IntArray, target: Int, index: Int, sum: Int): Int {
        if (index == numbers.size) return if (sum == target) 1 else 0
        return dfs1(numbers, target, index + 1, sum + numbers[index]) +
               dfs1(numbers, target, index + 1, sum - numbers[index])
    }

    // ==================== 여행경로 (Level 3) ====================
    // 주어진 항공권으로 방문할 수 있는 모든 공항을 알파벳 순으로 방문하는 경로 반환
    // DFS로 모든 경로 탐색, 알파벳 순 우선 방문
    fun solution2(tickets: Array<Array<String>>): Array<String> {
        val visited = BooleanArray(tickets.size)
        val path = mutableListOf("ICN")
        tickets.sortWith(compareBy({ it[0] }, { it[1] }))
        dfs2(tickets, visited, path)
        return path.toTypedArray()
    }

    fun dfs2(tickets: Array<Array<String>>, visited: BooleanArray, path: MutableList<String>): Boolean {
        if (path.size == tickets.size + 1) return true
        for (i in tickets.indices) {
            if (tickets[i][0] == path.last() && !visited[i]) {
                visited[i] = true
                path.add(tickets[i][1])
                if (dfs2(tickets, visited, path)) return true
                visited[i] = false
                path.removeLast()
            }
        }
        return false
    }

    // ==================== 피로도 (Level 2) ====================
    // 유저가 탐험할 수 있는 최대 던전 수 반환
    // DFS로 던전 방문 순서의 모든 순열 탐색, 각 경우의 탐험 가능 던전 수 중 최대값 반환
    fun solution3(k: Int, dungeons: Array<IntArray>): Int {
        val visited = BooleanArray(dungeons.size)
        var count = 0
        for (index in dungeons.indices) {
            count = maxOf(count, dfs3(index, k, dungeons, visited))
        }
        return count
    }

    fun dfs3(index: Int, current: Int, dungeons: Array<IntArray>, visited: BooleanArray): Int {
        if (visited[index] || current < dungeons[index][0]) return 0
        visited[index] = true
        var max = 0
        for (next in dungeons.indices) {
            max = maxOf(max, dfs3(next, current - dungeons[index][1], dungeons, visited))
        }
        visited[index] = false
        return max + 1
    }

    // ==================== 테스트 ====================

    @Test
    fun testDFSSolution() {
        // 타겟 넘버
        assertEquals(5, solution1(intArrayOf(1, 1, 1, 1, 1), 3))
        assertEquals(2, solution1(intArrayOf(4, 1, 2, 1), 4))

        // 여행경로
        assertEquals(listOf("ICN", "JFK", "HND", "IAD"), solution2(arrayOf(arrayOf("ICN", "JFK"), arrayOf("HND", "IAD"), arrayOf("JFK", "HND"))).toList())
        assertEquals(listOf("ICN", "ATL", "ICN", "SFO", "ATL", "SFO"), solution2(arrayOf(arrayOf("ICN", "SFO"), arrayOf("ICN", "ATL"), arrayOf("SFO", "ATL"), arrayOf("ATL", "ICN"), arrayOf("ATL", "SFO"))).toList())

        // 피로도
        assertEquals(3, solution3(80, arrayOf(intArrayOf(80, 20), intArrayOf(50, 40), intArrayOf(30, 10))))
    }
}
