package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.combineHigh
import kevtendo.common.binary.toHighByte
import kevtendo.common.binary.toLowByte
import kevtendo.common.binary.toU16
import kevtendo.common.binary.toU8
import kevtendo.common.binary.set
import kevtendo.common.binary.get

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