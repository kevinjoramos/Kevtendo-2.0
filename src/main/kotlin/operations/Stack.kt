package org.example.operations

import org.example.Cpu6502
import org.example.get
import org.example.set
import org.example.util.toU8

fun Cpu6502.pha() {
    memory[stackPointer.combineHigh(0x01u.toU8())] = accumulator
    stackPointer--
}

fun Cpu6502.php() {
    memory[stackPointer.combineHigh(0x01u.toU8())] = status
    stackPointer--
}

fun Cpu6502.pla() {
    stackPointer++
    accumulator = memory[stackPointer.combineHigh(0x01u.toU8())]
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}

fun Cpu6502.plp() {
    stackPointer++
    status = memory[stackPointer.combineHigh(0x01u.toU8())]
}