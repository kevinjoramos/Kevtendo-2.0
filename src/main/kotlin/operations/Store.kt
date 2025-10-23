package org.example.operations

import org.example.Cpu6502
import org.example.absolute
import org.example.absoluteX
import org.example.absoluteY
import org.example.immediate
import org.example.indirectX
import org.example.indirectY
import org.example.util.U8
import org.example.util.toU8
import org.example.zeroPage
import org.example.zeroPageX

/**
 * Data from memory -> accumulator
 * Effected flags: zero, negative
 */

class LDA(private val cpu: Cpu6502) {
    fun indirectY() = cpu.apply {
        val operand = cpu.indirectY()
        zeroFlag = operand == 0u.toU8()
        negativeFlag = operand.isSetAt(bit = 7)
    }
}

fun Cpu6502.lda(operand: U8) {
    accumulator = operand
    negativeFlag = operand.isSetAt(bit = 7)
    zeroFlag = operand == 0u.toU8()
}

fun Cpu6502.ldx(operand: U8) {
    x = operand
    negativeFlag = operand.isSetAt(bit = 7)
    zeroFlag = operand == 0u.toU8()
}

fun Cpu6502.ldy(operand: U8) {
    y = operand
    negativeFlag = operand.isSetAt(bit = 7)
    zeroFlag = operand == 0u.toU8()
}

fun Cpu6502.ldy(operand: U8) {
    y = operand
    negativeFlag = operand.isSetAt(bit = 7)
    zeroFlag = operand == 0u.toU8()
}