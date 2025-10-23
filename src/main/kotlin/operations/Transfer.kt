package org.example.operations

import org.example.Cpu6502
import org.example.set
import org.example.util.U16

fun Cpu6502.tax() {
    x = accumulator
    negativeFlag = x.isSetAt(7)
}