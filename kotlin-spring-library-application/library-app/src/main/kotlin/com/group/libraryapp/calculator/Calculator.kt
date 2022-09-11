package com.group.libraryapp.calculator

/* data */ class Calculator(
/**
 // Backing field 사용
    private var _number: Int
 */
    var number: Int
) {
    /*
    // BackingField
    val number: Int
        get() = this.number
    */
    fun add(operand: Int) {
        this.number += operand
    }

    fun minus(operand: Int) {
        this.number -= operand
    }

    fun multiply(operand: Int) {
        this.number *= operand
    }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw java.lang.IllegalArgumentException("0으로 나눌 수 없습니다")
        }
        this.number /= operand
    }
}