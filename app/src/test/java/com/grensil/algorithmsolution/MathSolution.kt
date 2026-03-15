package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MathSolution {

    // ==================== 숫자의 표현 (Level 2) ====================
    // 자연수 n을 연속한 자연수들의 합으로 표현하는 방법의 수 반환
    fun solution1(n: Int): Int {
        //[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
        var nArray = IntArray(n) { it+1 }
        var count = 0

        //0번째부터 1번 ~ 끝까지 반복시켜서 n 이 되는지
        //1번째부터 2번~ 끝까지 반복시켜서 N 이 되느지
        for(x in 0..nArray.size-1) {
           for(y in x..nArray.size-1) {
               if(nArray.sliceArray(IntRange(x,y)).sum() == n)
                   count+=1
           }
        }
        return count
    }

    // ==================== 테스트 ====================

    @Test
    fun testMathSolution() {
        // 숫자의 표현
        assertEquals(4, solution1(15))
    }
}
