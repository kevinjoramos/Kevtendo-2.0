package org.example.operations

import org.example.Cpu6502
import org.example.util.U16
import org.example.get
import org.example.util.getBit
import org.example.util.isZero
import org.example.util.toU16
import org.example.util.toU8

// carry is +1 because each operation represents the next "segment" of addition. So this operation can be used for low and high bytes.
// overflow only occurs when adding 2 positives produces a negative or adding 2 negatives produces a positive.
fun Cpu6502.adc(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    val result =
        accumulator.toU16()
            .plus(operand.toU16())
            .plus((if (carryFlag) 1u else 0u).toU16())
    negativeFlag = result.getBit(7)
    overflowFlag = run {
        val a7 = accumulator.getBit(7)
        val o7 = operand.getBit(7)
        val r7 = result.getBit(7)
        a7 == o7 && a7 != r7
    }
    zeroFlag = result.isZero()
    carryFlag = result > 255u.toU16()
    accumulator = result.toU8()
}

// carry is -1 because it represents the previous subtraction borrowing from this value.
// overflow only occurs when subtracting a negative from a positive makes a negative or subtracting a positive from a negative makes a positive.
fun Cpu6502.sbc(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    val result = accumulator
        .minus(operand)
        .minus(
            carryFlag.not().toU8()
        )
    negativeFlag = result.getBit(7)
    overflowFlag = run {
        val a7 = accumulator.getBit(7)
        val o7 = operand.getBit(7)
        val r7 = result.getBit(7)
        o7 == r7 && o7 != a7
    }
    zeroFlag = result.isZero()
    carryFlag = accumulator >= operand + carryFlag.not().toU8()
    accumulator = result
}

fun Cpu6502.cmp(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    val result = accumulator - operand
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
    carryFlag = accumulator >= operand
}

fun Cpu6502.cpx(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    val result = x - operand
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
    carryFlag = x >= operand
}

fun Cpu6502.cpy(effectiveAddress: U16) {
    val operand = memory[effectiveAddress]
    val result = y - operand
    negativeFlag = result.getBit(7)
    zeroFlag = result.isZero()
    carryFlag = y >= operand
}