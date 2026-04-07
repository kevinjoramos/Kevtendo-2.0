package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.getBit
import kevtendo.common.binary.isZero

/**
 * Data from memory -> accumulator
 * Effected flags: zero, negative
 */

fun Cpu6502.lda() {
    val operand = memory[effectiveAddress!!]
    accumulator = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.ldx() {
    val operand = memory[effectiveAddress!!]
    x = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.ldy() {
    val operand = memory[effectiveAddress!!]
    y = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.sta() {
    memory[effectiveAddress!!] = accumulator
}

fun Cpu6502.stx() {
    memory[effectiveAddress!!] = x
}

fun Cpu6502.sty() {
    memory[effectiveAddress!!] = y
}