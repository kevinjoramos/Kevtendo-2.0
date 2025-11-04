package org.example.operations

import org.example.Cpu6502
import org.example.util.U16
import org.example.util.toU16
import org.example.set
import org.example.get
import org.example.util.combineHigh
import org.example.util.toHighByte
import org.example.util.toLowByte
import org.example.util.toU8

fun Cpu6502.brk() {
    memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toHighByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toLowByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = status
    stackPointer--
}

fun Cpu6502.jmp(effectiveAddress: U16) {
    programCounter = effectiveAddress
}

fun Cpu6502.jsr(effectiveAddress: U16) {
    val returnAddress = programCounter - 1u.toU16()
    memory[stackPointer.combineHigh(0x01u.toU8())] = returnAddress.toHighByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = returnAddress.toLowByte()
    stackPointer--
    programCounter = effectiveAddress
}

fun Cpu6502.rti() {
    stackPointer++
    status = memory[stackPointer.combineHigh(0x01u.toU8())]
    stackPointer++
    val low = memory[stackPointer.combineHigh(0x01u.toU8())]
    stackPointer++
    val high = memory[stackPointer.combineHigh(0x01u.toU8())]
    programCounter = low.combineHigh(high)
}

fun Cpu6502.rts() {
    stackPointer++
    val low = memory[stackPointer.combineHigh(0x01u.toU8())]
    stackPointer++
    val high = memory[stackPointer.combineHigh(0x01u.toU8())]
    programCounter = low.combineHigh(high)
}