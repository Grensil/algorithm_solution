package com.grensil.algorithmsolution

import junit.framework.TestCase.assertEquals
import org.junit.Test

class HashSolution {

    // ==================== 전화번호 목록 (Level 2) ====================
    // 한 번호가 다른 번호의 접두어인 경우가 있으면 false, 없으면 true 반환
    // 각 번호의 부분 문자열이 hashSet에 존재하는지 확인
    fun solution1(s: MutableList<String>): Boolean {
        val hashSet = s.toHashSet()

        for (number in hashSet) {
            for (len in 1 until number.length) {
                if (hashSet.contains(number.substring(0, len))) return false
            }
        }
        return true
    }

    // ==================== 완주하지 못한 선수 (Level 1) ====================
    // participant 중 completion에 없는 선수 이름 반환 (동명이인 고려)
    // participantMap에 참가자 수를 카운팅 후 완주자 수를 차감, count > 0인 이름 반환
    fun solution2(participant: MutableList<String>, completion: MutableList<String>): String {
        val participantMap = HashMap<String, Int>()

        for (name in participant) {
            participantMap[name] = (participantMap[name] ?: 0) + 1
        }

        for (name in completion) {
            participantMap[name] = (participantMap[name] ?: 0) - 1
        }

        return participantMap.filter { (_, count) -> count > 0 }.keys.first()
    }

    // ==================== 테스트 ====================

    @Test
    fun testHashSolution1() {
        assertEquals(false, solution1(mutableListOf("119", "97674223", "1195524421")))
        assertEquals(true, solution1(mutableListOf("123", "456", "789")))
        assertEquals(false, solution1(mutableListOf("12", "123", "1235", "567", "88")))
    }

    @Test
    fun testHashSolution2() {
        assertEquals("leo", solution2(mutableListOf("leo", "kiki", "eden"), mutableListOf("eden", "kiki")))
        assertEquals("vinko", solution2(mutableListOf("marina", "josipa", "nikola", "vinko", "filipa"), mutableListOf("josipa", "filipa", "marina", "nikola")))
        assertEquals("mislav", solution2(mutableListOf("mislav", "stanko", "mislav", "ana"), mutableListOf("stanko", "ana", "mislav")))
    }
}
