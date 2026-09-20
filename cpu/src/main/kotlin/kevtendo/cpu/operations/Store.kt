package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.getBit
import kevtendo.common.binary.isZero
import kevtendo.common.binary.get
import kevtendo.common.binary.set

/**
 * Data from memory -> accumulator
 * Effected flags: zero, negative
 */

fun Cpu6502.lda() {
    val operand = memory.read(effectiveAddress!!)
    accumulator = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.ldx() {
    val operand = memory.read(effectiveAddress!!)
    x = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.ldy() {
    val operand = memory.read(effectiveAddress!!)
    y = operand
    negativeFlag = operand.getBit(bit = 7)
    zeroFlag = operand.isZero()
}

fun Cpu6502.sta() {
    memory.write(effectiveAddress!!, accumulator)
}

fun Cpu6502.stx() {
    memory.write(effectiveAddress!!, x)
}

fun Cpu6502.sty() {
    memory.write(effectiveAddress!!, y)
}