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

    // ==================== 신고 결과 받기 (Level 1) ====================
    // 각 유저가 처리 결과 메일을 받은 횟수를 id_list 순서대로 반환
    // - 동일 유저에 대한 중복 신고는 1회로 처리
    // - k번 이상 신고된 유저는 정지, 해당 유저를 신고한 모든 유저에게 메일 발송
    fun solution3(idList: MutableList<String>, report: MutableList<String>, k: Int): IntArray {

        //id_list	report	k	result
        //["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
        //["con", "ryan"]	["ryan con", "ryan con", "ryan con", "ryan con"]	3	[0,0]

        //reportedMap  신고 당한 횟수 -> frodo 2 , neo 2 muzi 1

        // 신고 한 사람 reporterMap  muzi -> [frodo,neo] , frodo -> [neo] , appeach -> [frodo,muzi], neo -> []

        //그러면 위 두개의 map 을 가지고 hashMap 에서의 값이 k 보다 큰 사람만 frodo 를 신고한 사람 muzi,apeeach +=1

        val reportedMap = HashMap<String, Int>()
        val reporterMap = HashMap<String, MutableList<String>>()
        val resultArray = IntArray(idList.size)

        for (name in report) {
            val (reporter, reported) = name.split(" ")
            val list = reporterMap.getOrPut(reporter) { mutableListOf() }
            if (!list.contains(reported)) {  // 중복 체크
                list.add(reported)
                reportedMap[reported] = (reportedMap[reported] ?: 0) + 1
            }
        }

        for (i in idList.indices) {
            val name = idList[i]
            if ((reportedMap[name] ?: 0) >= k) {
                reporterMap.forEach { (reporter, reported) ->
                    if (reported.contains(name)) {
                        resultArray[idList.indexOf(reporter)] += 1
                    }
                }
            }
        }

        return resultArray
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
        assertEquals(
            "leo",
            solution2(mutableListOf("leo", "kiki", "eden"), mutableListOf("eden", "kiki"))
        )
        assertEquals(
            "vinko",
            solution2(
                mutableListOf("marina", "josipa", "nikola", "vinko", "filipa"),
                mutableListOf("josipa", "filipa", "marina", "nikola")
            )
        )
        assertEquals(
            "mislav",
            solution2(
                mutableListOf("mislav", "stanko", "mislav", "ana"),
                mutableListOf("stanko", "ana", "mislav")
            )
        )
    }

    @Test
    fun testHashSolution3() {
        assertEquals(
            intArrayOf(2, 1, 1, 0).toList(),
            solution3(
                mutableListOf("muzi", "frodo", "apeach", "neo"),
                mutableListOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
                2
            ).toList()
        )
        assertEquals(
            intArrayOf(0, 0).toList(),
            solution3(
                mutableListOf("con", "ryan"),
                mutableListOf("ryan con", "ryan con", "ryan con", "ryan con"),
                3
            ).toList()
        )
    }
}
