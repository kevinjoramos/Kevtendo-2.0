package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.combineHigh
import kevtendo.common.binary.getBit
import kevtendo.common.binary.isZero
import kevtendo.common.binary.toU8
import kevtendo.common.binary.set
import kevtendo.common.binary.get

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