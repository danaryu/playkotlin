package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class JunitCalculatorTest {

    @Test
    fun addTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun addTest_byDataClass() {
        /**
         * Kotlin의 Data class는  toString(), hashCode(), equals(), copy()를 자동으로 생성해준다.
         */
        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        val expectedCalculator = Calculator(8)
        assertThat(calculator == expectedCalculator).isTrue // eqauls 함수가 구현되어있으면 success
    }

    @Test
    fun addTest_byPublicConstructor() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun addTest_byBackingProperties() {
        /**
         * Backing Properties를 통해 custom getter 생성
         * Setter는 막혀있다!
         */
        //given
        val calculator = Calculator(5)

        //when
        calculator.add(3)

        //then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.minus(3)

        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    fun multiplyTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.multiply(3)

        //then
        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun divideTest() {
        //given
        val calculator = Calculator(5)

        //when
        calculator.divide(2)

        //then
        assertThat(calculator.number).isEqualTo(2)
    }


    @Test
    fun divideExceptionTest() {
        //given
        val calculator = Calculator(5)

        // when & then
        assertThrows<IllegalArgumentException>("예외가 발생해야 한다") {
            calculator.divide(0)
        }
    }

    @Test
    fun divideExceptionTestWithMessage() {
        //given
        val calculator = Calculator(5)

        // when
        val message = assertThrows<IllegalArgumentException>("예외가 발생해야 한다") {
            calculator.divide(0)
        }.message

        //then
        assertEquals(message, "0으로 나눌 수 없습니다")
        //assertThat(message).isEqualTo("0으로 나눌 수 없습니다")
    }

    @Test
    fun divideExceptionTestWithScopeFunction() {
        //given
        val calculator = Calculator(5)

        // when & then
        assertThrows<IllegalArgumentException>("예외가 발생해야 한다") {
            calculator.divide(0)
        }.apply {
            assertEquals(message, "0으로 나눌 수 없습니다")
        }
    }
}

