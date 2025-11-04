package org.example.operations

import org.example.Cpu6502
import org.example.get
import org.example.set
import org.example.util.U16
import org.example.util.getBit
import org.example.util.isZero
import org.example.util.toU8
import org.example.util.withBit

fun Cpu6502.asla() {
    val initial = accumulator
    accumulator = accumulator.shl(1)
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
    carryFlag = initial.getBit(7)
}

fun Cpu6502.asl(effectiveAddress: U16) {
    val initial = memory[effectiveAddress]
    val result = initial.shl(1)
    memory[effectiveAddress] = result
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
    carryFlag = initial.getBit(7)
}

fun Cpu6502.lsra() {
    val initial = accumulator
    accumulator = accumulator.shr(1)
    negativeFlag = false
    zeroFlag = accumulator.isZero()
    carryFlag = initial.getBit(0)
}

fun Cpu6502.lsr(effectiveAddress: U16) {
    val initial = memory[effectiveAddress]
    val result = initial.shr(1)
    memory[effectiveAddress] = result
    negativeFlag = false
    zeroFlag = result.isZero()
    carryFlag = initial.getBit(0)
}

fun Cpu6502.rola() {
    val initial = accumulator
    accumulator = accumulator.shl(1).withBit(0, carryFlag)
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
    carryFlag = initial.getBit(7)
}

fun Cpu6502.rol(effectiveAddress: U16) {
    val initial = memory[effectiveAddress]
    val result = initial.shl(1).withBit(0, carryFlag)
    memory[effectiveAddress] = result
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
    carryFlag = initial.getBit(7)
}

fun Cpu6502.rora() {
    val initial = accumulator
    accumulator = accumulator.shr(1).withBit(7, carryFlag)
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
    carryFlag = initial.getBit(0)
}

fun Cpu6502.ror(effectiveAddress: U16) {
    val initial = memory[effectiveAddress]
    val result = initial.shr(1).withBit(7, carryFlag)
    memory[effectiveAddress] = result
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
    carryFlag = initial.getBit(0)
}