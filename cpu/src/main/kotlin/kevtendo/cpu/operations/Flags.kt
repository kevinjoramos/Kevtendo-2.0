package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502

fun Cpu6502.clc() {
    carryFlag = false
}

fun Cpu6502.cld() {
    decimalFlag = false
}

fun Cpu6502.cli() {
    interruptDisableFlag = false
}

fun Cpu6502.clv() {
    overflowFlag = false
}

fun Cpu6502.sec() {
    carryFlag = true
}

fun Cpu6502.sed() {
    decimalFlag = true
}

fun Cpu6502.sei() {
    interruptDisableFlag = true
}