package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.getBit
import kevtendo.common.binary.isZero

fun Cpu6502.and() {
    accumulator = accumulator and memory[effectiveAddress!!]
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}

fun Cpu6502.bit() {
    val operand = memory[effectiveAddress!!]
    val result = accumulator and operand
    negativeFlag = operand.getBit(7)
    carryFlag = operand.getBit(6)
    zeroFlag = result.isZero()
}

fun Cpu6502.eor() {
    accumulator = accumulator xor memory[effectiveAddress!!]
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}

fun Cpu6502.ora() {
    accumulator = accumulator or memory[effectiveAddress!!]
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}