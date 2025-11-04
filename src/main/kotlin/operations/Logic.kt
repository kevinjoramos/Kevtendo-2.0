package org.example.operations

import org.example.Cpu6502
import org.example.get
import org.example.util.U16
import org.example.util.getBit
import org.example.util.isZero

fun Cpu6502.and(effectiveAddress: U16) {
    accumulator = accumulator and memory[effectiveAddress]
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}

fun Cpu6502.bit(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    val result = accumulator and operand
    negativeFlag = operand.getBit(7)
    carryFlag = operand.getBit(6)
    zeroFlag = result.isZero()
}

fun Cpu6502.eor(effectiveAddress: U16) {
    accumulator = accumulator xor memory[effectiveAddress]
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}

fun Cpu6502.ora() {
    accumulator = accumulator or memory[effectiveAddress!!]
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}