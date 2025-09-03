package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class DPSolution {

    @Test
    fun binaryTransferTest() {
        val s1 = 18
        val result1 = sugarDeliveryDP(s1)
        assertEquals(result1,4)

        val s2 = 4
        val result2 = sugarDeliveryDP(s2)
        assertEquals(result2,-1)

        val s3 = 6
        val result3 = sugarDeliveryDP(s3)
        assertEquals(result3,2)

        val s4 = 9
        val result4 = sugarDeliveryDP(s4)
        assertEquals(result4,3)

        val s5 = 11
        val result5 = sugarDeliveryDP(s5)
        assertEquals(result5,3)
    }
}

fun sugarDeliveryGreedy(n : Int) : Int {

    for(x in n/5 downTo 0) {
        val remain = n - (x*5)
        if(remain % 3 ==0) {
            val y = remain / 3
            return x + y
        }
    }
    return -1
}

fun sugarDeliveryDP(n : Int) : Int {
    val dp = IntArray(n + 1) { Int.MAX_VALUE }  // ✅ 불가능한 값으로 초기화
    dp[0] = 0  // 0kg은 0개 봉지로 가능

    for (i in 0..n) {
        if (dp[i] == Int.MAX_VALUE) continue  // ✅ 불가능한 경우 스킵
        if (i + 3 <= n) dp[i + 3] = minOf(dp[i + 3], dp[i] + 1)  // ✅ 최소값
        if (i + 5 <= n) dp[i + 5] = minOf(dp[i + 5], dp[i] + 1)  // ✅ 최소값
    }

    return if (dp[n] == Int.MAX_VALUE) -1 else dp[n]
}