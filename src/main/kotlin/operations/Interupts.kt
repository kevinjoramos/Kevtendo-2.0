package org.example.operations

import org.example.Cpu6502
import org.example.get
import org.example.set
import org.example.util.toHighByte
import org.example.util.toLowByte
import org.example.util.toU16
import org.example.util.toU8

fun Cpu6502.irq() {
    if (!interruptDisableFlag) {
        memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toHighByte()
        stackPointer--
        memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toLowByte()
        stackPointer--
        memory[stackPointer.combineHigh(0x01u.toU8())] = status
        stackPointer--
        programCounter = memory[IrqVector.inc()].combineHigh(memory[IrqVector])
    }
}


fun Cpu6502.nmi() {
    memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toHighByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toLowByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = status
    stackPointer--
    programCounter = memory[NmiVector.inc()].combineHigh(memory[NmiVector])
}

private val IrqVector = 0xFFFAu.toU16()
private val NmiVector = 0xFFFEu.toU16()
private val ResVector = 0xFFFCu.toU16()