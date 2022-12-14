package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JunitTest {

    // java의 static
    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            println("모든 테스트 시작 전")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("모든 테스트 시작 후")
        }
    }

    @BeforeEach
    fun beforeEach() {
        println("각 테스트 시작하기 전")
    }

    @AfterEach
    fun afterEach() {
        println("각 테스트 시작한 후")
    }

    @Test
    fun test1() {
        println("테스트입니다")
    }

    @Test
    fun test2() {
        println("테스트2입니다")
    }

}