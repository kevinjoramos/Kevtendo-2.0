package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.*

fun Cpu6502.brk() {
    memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toHighByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = programCounter.toLowByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = status
    stackPointer--
}

fun Cpu6502.jmp() {
    programCounter = effectiveAddress!!
}

fun Cpu6502.jsr() {
    val returnAddress = programCounter - 1u.toU16()
    memory[stackPointer.combineHigh(0x01u.toU8())] = returnAddress.toHighByte()
    stackPointer--
    memory[stackPointer.combineHigh(0x01u.toU8())] = returnAddress.toLowByte()
    stackPointer--
    programCounter = effectiveAddress!!
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

fun Cpu6502.nop() {}