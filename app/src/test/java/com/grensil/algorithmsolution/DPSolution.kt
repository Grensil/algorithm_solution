package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class DPSolution {

    // ==================== 도둑질 (Level 4) ====================
    // 원형으로 배치된 집에서 인접하지 않게 털 수 있는 최댓값 반환
    // dp[i] = i번째 집까지 고려했을 때 최대 금액
    // TODO: 미완성
    fun solution4(money: IntArray): Int {
        return 0
    }

    // ==================== 타일 장식물 (Level 3) ====================
    // 피보나치 수열로 구성된 타일의 둘레 길이 반환 (mod 1234567)
    // dpArray[n+1] = 피보나치(n+1), 둘레 = 가로 + 세로 = fib(n) + fib(n+1)
    fun solution(n: Int): Int {
        val dpArray = IntArray(n + 2)
        dpArray[1] = 0
        dpArray[2] = 1
        for (x in 3 until dpArray.size) {
            dpArray[x] = dpArray[x - 2] + dpArray[x - 1]
        }
        return dpArray[n + 1] % 1234567
    }

    // ==================== 2*n 타일링 (Level 3) ====================
    // 2*n 크기 공간을 2*1 타일로 채우는 경우의 수 반환 (mod 1234567)
    // dp[n] = dp[n-1] + dp[n-2]
    fun dpSolution(n: Int): Int {
        if (n <= 2) return n
        val dpArray = IntArray(n + 1)
        dpArray[1] = 1
        dpArray[2] = 2
        for (x in 3..n) {
            dpArray[x] = (dpArray[x - 2] + dpArray[x - 1]) % 1234567
        }
        return dpArray[n]
    }

    // ==================== N으로 표현 (Level 3) ====================
    // N과 사칙연산만으로 number를 만들 때 필요한 최소 N 사용 횟수 반환
    // dp[k] = N을 k번 사용해서 만들 수 있는 숫자 집합
    fun dpSolution2(N: Int, number: Int): Int {
        val dp = List(9) { mutableSetOf<Int>() }

        for (k in 1..8) {
            // 연속수 (N, NN, NNN...) 추가
            dp[k].add(getNumber(N, k))

            // i번 + j번 조합으로 k번 채우기
            for (i in 1 until k) {
                val j = k - i
                for (num1 in dp[i]) {
                    for (num2 in dp[j]) {
                        dp[k].add(num1 + num2)
                        dp[k].add(num1 - num2)
                        dp[k].add(num1 * num2)
                        if (num2 != 0) dp[k].add(num1 / num2)
                    }
                }
            }

            if (dp[k].contains(number)) return k
        }

        return -1
    }

    private fun getNumber(N: Int, repeat: Int): Int {
        return N.toString().repeat(repeat).toInt()
    }

    // ==================== 피보나치 (Level 2) ====================
    // n번째 피보나치 수 반환 (mod 1234567)
    fun fibonacciSolution(n: Int): Int {
        val map = mutableMapOf<Int, Int>()

        fun fibonacci(x: Int): Int = (map[x - 1]!! + map[x - 2]!!) % 1234567

        fun makeFibonacciMap(x: Int) {
            if (map.contains(n)) return
            else if (x == 0) map[0] = 0
            else if (x == 1) map[1] = 1
            else map[x] = fibonacci(x)
        }

        for (x in 0..n) makeFibonacciMap(x)
        return map[n]!!
    }

    // ==================== 점프와 순간이동 (Level 3) ====================
    // n칸을 이동할 때 건전지 사용 횟수(점프 횟수) 최솟값 반환
    // dp[n] = dp[n-1] + dp[n-2] (순간이동 활용)
    fun jumpSolution(n: Int): Long {
        val dp = LongArray(n + 1)
        dp[1] = 1
        dp[2] = 2
        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        return dp[n]
    }

    // ==================== 2*n 타일링 2 (Level 3) ====================
    // 2*n 바닥을 1*2 타일로 채우는 경우의 수 반환 (mod 1,000,000,007)
    // dp[n] = dp[n-1] + dp[n-2]
    fun tilingSolution(n: Int): Int {
        if (n <= 2) return n
        val dp = IntArray(n + 1)
        dp[1] = 1
        dp[2] = 2
        for (i in 3..n) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000007
        }
        return dp[n]
    }

    // ==================== 테스트 ====================

    @Test
    fun testDPSolution() {
        // 타일 장식물
        assertEquals(1, solution(2))
        assertEquals(2, solution(3))
        assertEquals(3, solution(4))

        // 2*n 타일링
        assertEquals(1, dpSolution(1))
        assertEquals(2, dpSolution(2))
        assertEquals(3, dpSolution(3))
        assertEquals(5, dpSolution(4))

        // N으로 표현
        assertEquals(4, dpSolution2(5, 12))
        assertEquals(2, dpSolution2(5, 55))

        // 피보나치
        assertEquals(2, fibonacciSolution(3))
        assertEquals(1232586, fibonacciSolution(155))

        // 점프와 순간이동
        assertEquals(5L, jumpSolution(4))
        assertEquals(89L, jumpSolution(10))

        // 2*n 타일링 2
        assertEquals(5, tilingSolution(4))

        // 도둑질 (TODO: 구현 후 활성화)
        // assertEquals(4, solution4(intArrayOf(1, 2, 3, 1)))
    }
}
