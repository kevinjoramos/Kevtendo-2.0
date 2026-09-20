package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.getBit
import kevtendo.common.binary.isZero
import kevtendo.common.binary.withBit
import kevtendo.common.binary.get
import kevtendo.common.binary.set

fun Cpu6502.asla() {
    val initial = accumulator
    accumulator = accumulator.shl(1)
    negativeFlag = accumulator.getBit(7)
    zeroFlag = accumulator.isZero()
    carryFlag = initial.getBit(7)
}

fun Cpu6502.asl() {
    val initial = memory.read(effectiveAddress!!)
    val result = initial.shl(1)
    memory.write(effectiveAddress!!, result)
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

fun Cpu6502.lsr() {
    val initial = memory.read(effectiveAddress!!)
    val result = initial.shr(1)
    memory.write(effectiveAddress!!, result)
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

fun Cpu6502.rol() {
    val initial = memory.read(effectiveAddress!!)
    val result = initial.shl(1).withBit(0, carryFlag)
    memory.write(effectiveAddress!!, result)
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

fun Cpu6502.ror() {
    val initial = memory.read(effectiveAddress!!)
    val result = initial.shr(1).withBit(7, carryFlag)
    memory.write(effectiveAddress!!, result)
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
    carryFlag = initial.getBit(0)
}