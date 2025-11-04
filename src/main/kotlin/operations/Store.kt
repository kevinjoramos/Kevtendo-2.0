package org.example.operations

import org.example.Cpu6502
import org.example.get
import org.example.set
import org.example.util.U16
import org.example.util.getBit
import org.example.util.isZero

/**
 * Data from memory -> accumulator
 * Effected flags: zero, negative
 */

fun Cpu6502.lda(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    accumulator = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.ldx(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    x = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.ldy(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    y = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.sta(effectiveAddress: U16) {
    memory[effectiveAddress] = accumulator
}

fun Cpu6502.stx(effectiveAddress: U16) {
    memory[effectiveAddress] = x
}

fun Cpu6502.sty(effectiveAddress: U16) {
    memory[effectiveAddress] = y
}