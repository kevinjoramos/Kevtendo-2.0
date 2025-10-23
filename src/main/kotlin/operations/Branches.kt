package org.example.operations

import org.example.Cpu6502
import org.example.util.U16

fun Cpu6502.bcc(effectiveAddress: U16) {
    if (!carryFlag) {
        programCounter = effectiveAddress
    }
}

fun Cpu6502.bcs(effectiveAddress: U16) {
    if (carryFlag) {
        programCounter = effectiveAddress
    }
}

fun Cpu6502.beq(effectiveAddress: U16) {
    if (zeroFlag) {
        programCounter = effectiveAddress
    }
}

fun Cpu6502.bmi(effectiveAddress: U16) {
    if (negativeFlag) {
        programCounter = effectiveAddress
    }
}

fun Cpu6502.bne(effectiveAddress: U16) {
    if (!zeroFlag) {
        programCounter = effectiveAddress
    }
}

fun Cpu6502.bpl(effectiveAddress: U16) {
    if (!negativeFlag) {
        programCounter = effectiveAddress
    }
}

fun Cpu6502.bvc(effectiveAddress: U16) {
    if (!overflowFlag) {
        programCounter = effectiveAddress
    }
}

fun Cpu6502.bvs(effectiveAddress: U16) {
    if (overflowFlag) {
        programCounter = effectiveAddress
    }
}
