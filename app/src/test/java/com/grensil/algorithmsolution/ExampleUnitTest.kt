package com.grensil.algorithmsolution

import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun binaryTransferTest() {
        val s = 1025

        val result = getBinaryInt(s)
        println("result : $result")
    }
}

fun solution(s: String): List<Int> {
    var answer: IntArray = intArrayOf()
    var count = 0
    var filteredString = s.replace("0", "")
    var answer_count = s.length - filteredString.length
    //s가 1이 아닌 동안
    //이진 변환
    while (filteredString != "1") {
        filteredString = binaryTransfer(filteredString)
        count += 1
    }
    return listOf(answer_count, count)
}

//"1111" -> "100"으로  변환하는 메소드
fun binaryTransfer(binaryString: String): String {
    //글자 4
    val length = binaryString.length
    val binaryResult = getBinaryInt(length)
    val binaryString = binaryResult.toString()
    return binaryString
}

//8 -> 1000 단순 이진법 변환 메소드
fun getBinaryInt(length: Int): List<Int> {
    val resultList = mutableListOf<Int>()
    var num = length

    while (num != 1) {
        resultList.add(num % 2) // 나머지를 저장
        num /= 2               // 2로 나눔
    }
    resultList.add(1) // 마지막 1 추가

    return resultList.reversed() // 올바른 순서를 위해 뒤집어서 반환
}
