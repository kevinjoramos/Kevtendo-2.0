package org.example.operations

import org.example.Cpu6502

fun Cpu6502.tax() {
    x = accumulator
    negativeFlag = x.getBit(7)
    zeroFlag = x.isZero()
}

fun Cpu6502.tay() {
    y = accumulator
    negativeFlag = y.getBit(7)
    zeroFlag = y.isZero()
}

fun Cpu6502.tsx() {
    x = stackPointer
    negativeFlag = x.getBit(7)
    zeroFlag = x.isZero()
}

fun Cpu6502.txa() {
    accumulator = x
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}

fun Cpu6502.txs() {
    stackPointer = x
}

fun Cpu6502.tya() {
    accumulator = y
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
}