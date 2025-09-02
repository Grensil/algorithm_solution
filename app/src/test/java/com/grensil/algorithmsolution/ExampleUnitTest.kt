package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun binaryTransferTest() {
        val s1 = "110010101001"
        val result1 = solution1(s1)
        assertEquals(result1,listOf(3,8))

        val s2 = "01110"
        val result2 = solution1(s2)
        assertEquals(result2,listOf(3,3))

        val s3 = "1111111"
        val result3 = solution1(s3)
        assertEquals(result3,listOf(4,1))

    }

    @Test
    fun fibonacciTest() {
        val n1 = 3
        val result1 = solution2(n1)
        assertEquals(result1,2)

        val n2 = 155
        val result2 = solution2(n2)
        assertEquals(result2,1232586)
    }

    @Test
    fun jadenCaseTest() {
        val s1 = "3people unFollowed me"
        val result1 = solution3(s1)
        assertEquals(result1,"3people Unfollowed Me")

        val s2 = "for the last week"
        val result2 = solution3(s2)
        assertEquals(result2,"For The Last Week")
    }


}


fun solution3(s: String): String {
    return s.split(" ").joinToString(" ") { word ->
        if (word.isEmpty()) {
            ""
        } else {
            if (word[0].isLetter()) {
                word[0].uppercaseChar() + word.substring(1).lowercase()
            } else {
                word.lowercase()
            }
        }
    }
}

fun solution2(n: Int): Int {

    val map = mutableMapOf<Int,Int>()

    fun fibonacci(x: Int) : Int {
        return (map[x - 1]!! + map[x - 2]!!) % 1234567
    }

    fun makeFibonacciMap(x : Int)  {
        if(map.contains(n)) return
        else if(x == 0) map[0] = 0
        else if(x == 1) map[1]  = 1
        else map[x] = fibonacci(x)
    }

    for(x in 0..n) {
        makeFibonacciMap(x)
    }

    return map[n]!!
}

// [이진 변환 횟수 , 사라진 0 숫자]
fun solution1(s: String): List<Int> {

    fun binaryTransfer(binaryString: String): String {
        val length = binaryString.length
        return length.toString(2)  // 🔧 이게 가장 안전!
    }

    var count = 0
    var disapperZeroCount = 0
    var originString = s

    while (originString != "1") {
        disapperZeroCount += originString.count { it == '0' }
        originString = originString.replace("0", "")
        val resultString = binaryTransfer(originString)  // 2진법 변환

        originString = resultString
        count += 1
    }
    return listOf(count, disapperZeroCount)
}