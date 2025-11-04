package org.example.operations

import org.example.Cpu6502
import org.example.get
import org.example.util.combineHigh


/**
 * Protocals for determining the operand
 *
 * TODO: (absolute x & t absoluted indexed, y-indirect, relative, and ) CPU cycles are variable depending on specific conditions.
 */
    fun Cpu6502.implied() { programCounter++ }

    fun Cpu6502.immediate() {
        val operand = memory[programCounter]
        programCounter++
        effectiveAddress = operand.toU16()
    }

    fun Cpu6502.absolute() {
        val low = memory[programCounter]
        programCounter++
        val high = memory[programCounter]
        programCounter++
        effectiveAddress = low.combineHigh(high)
    }

    fun Cpu6502.absoluteX() {
        val low = memory[programCounter]
        programCounter++
        val high = memory[programCounter]
        programCounter++
        effectiveAddress = low.combineHigh(high) + x
    }

    fun Cpu6502.absoluteY() {
        val low = memory[programCounter]
        programCounter++
        val high = memory[programCounter]
        programCounter++
        effectiveAddress = low.combineHigh(high) + y
    }

    fun Cpu6502.zeroPage() {
        val address = memory[programCounter]
        programCounter++
        effectiveAddress = address.toU16()
    }

    fun Cpu6502.zeroPageX() {
        val address = (memory[programCounter] + x)
        programCounter++
        effectiveAddress = address.toU16()
    }

    fun Cpu6502.zeroPageY() {
        val address = (memory[programCounter] + y)
        programCounter++
        effectiveAddress = address.toU16()
    }

    fun Cpu6502.indirect() {
        val lookUpLow = memory[programCounter]
        programCounter++
        val lookUpHigh = memory[programCounter]
        programCounter++
        val lookUpAddress = lookUpLow.combineHigh(lookUpHigh)
        val dataLow = memory[lookUpAddress]
        val dataHigh = memory[lookUpAddress.inc()]
        effectiveAddress = dataLow.combineHigh(dataHigh)
    }

    fun Cpu6502.indirectX() {
        val lookUpAddress = (memory[programCounter] + x)
        programCounter++
        val operandAddress = memory[lookUpAddress].combineHigh(memory[lookUpAddress.inc()])
        effectiveAddress = operandAddress
    }

    fun Cpu6502.indirectY() {
        val lookUpAddress = memory[programCounter]
        programCounter++
        val low = memory[lookUpAddress]
        val high = memory[lookUpAddress.inc()]
        val operandAddress = low.combineHigh(high) + y
        effectiveAddress = operandAddress
    }

    fun Cpu6502.relative() {
        val offset = memory[programCounter]
        programCounter++
        effectiveAddress = (programCounter + offset)
    }