package org.example.operations

import org.example.Cpu6502
import org.example.util.U16
import org.example.get
import org.example.set

fun Cpu6502.dec(effectiveAddress: U16) {
    val result = memory[effectiveAddress].dec()
    memory[effectiveAddress] = result
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
}

fun Cpu6502.dex() {
    x--
    negativeFlag = x.getBit(7)
    zeroFlag = x.isZero()
}

fun Cpu6502.dey() {
    y--
    negativeFlag = y.getBit(7)
    zeroFlag = y.isZero()
}

fun Cpu6502.inc(effectiveAddress: U16) {
    val result = memory[effectiveAddress].inc()
    memory[effectiveAddress] = result
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
}

fun Cpu6502.inx() {
    x++
    negativeFlag = x.getBit(7)
    zeroFlag = x.isZero()
}

fun Cpu6502.iny() {
    y++
    negativeFlag = y.getBit(7)
    zeroFlag = y.isZero()
}


