package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringSolution {

    // ==================== 이진 변환 반복하기 (Level 2) ====================
    // 문자열이 "1"이 될 때까지 이진 변환 반복
    // 반환: [변환 횟수, 제거된 0의 개수]
    fun binaryTransferSolution(s: String): List<Int> {
        var count = 0
        var disappearZeroCount = 0
        var current = s

        while (current != "1") {
            disappearZeroCount += current.count { it == '0' }
            current = current.replace("0", "")
            current = current.length.toString(2)
            count += 1
        }
        return listOf(count, disappearZeroCount)
    }

    // ==================== JadenCase (Level 2) ====================
    // 문자열의 각 단어 첫 글자를 대문자로, 나머지는 소문자로 변환
    fun jadenCaseSolution(s: String): String {
        return s.split(" ").joinToString(" ") { word ->
            if (word.isEmpty()) ""
            else if (word[0].isLetter()) word[0].uppercaseChar() + word.substring(1).lowercase()
            else word.lowercase()
        }
    }

    // ==================== 테스트 ====================

    @Test
    fun testStringSolution() {
        // 이진 변환 반복하기
        assertEquals(listOf(3, 8), binaryTransferSolution("110010101001"))
        assertEquals(listOf(3, 3), binaryTransferSolution("01110"))
        assertEquals(listOf(4, 1), binaryTransferSolution("1111111"))

        // JadenCase
        assertEquals("3people Unfollowed Me", jadenCaseSolution("3people unFollowed me"))
        assertEquals("For The Last Week", jadenCaseSolution("for the last week"))
    }
}
