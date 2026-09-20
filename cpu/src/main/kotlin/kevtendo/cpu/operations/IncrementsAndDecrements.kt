package kevtendo.cpu.operations

import kevtendo.common.binary.getBit
import kevtendo.common.binary.isZero
import kevtendo.cpu.Cpu6502

fun Cpu6502.dec() {
    val result = memory.read(effectiveAddress!!).dec()
    memory.write(effectiveAddress!!, result)
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

fun Cpu6502.inc() {
    val result = memory.read(effectiveAddress!!).inc()
    memory.write(effectiveAddress!!, result)
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


