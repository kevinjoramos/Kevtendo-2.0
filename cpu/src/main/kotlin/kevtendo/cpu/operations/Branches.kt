package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502

fun Cpu6502.bcc() {
    if (!carryFlag) {
        programCounter = effectiveAddress!!
    }
}

fun Cpu6502.bcs() {
    if (carryFlag) {
        programCounter = effectiveAddress!!
    }
}

fun Cpu6502.beq() {
    if (zeroFlag) {
        programCounter = effectiveAddress!!
    }
}

fun Cpu6502.bmi() {
    if (negativeFlag) {
        programCounter = effectiveAddress!!
    }
}

fun Cpu6502.bne() {
    if (!zeroFlag) {
        programCounter = effectiveAddress!!
    }
}

fun Cpu6502.bpl() {
    if (!negativeFlag) {
        programCounter = effectiveAddress!!
    }
}

fun Cpu6502.bvc() {
    if (!overflowFlag) {
        programCounter = effectiveAddress!!
    }
}

fun Cpu6502.bvs() {
    if (overflowFlag) {
        programCounter = effectiveAddress!!
    }
}
