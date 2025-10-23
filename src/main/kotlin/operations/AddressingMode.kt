package org.example.operations

import org.example.Cpu6502
import org.example.get
import org.example.util.U16


/**
 * Protocals for determining the operand
 *
 * TODO: (absolute x & t absoluted indexed, y-indirect, relative, and ) CPU cycles are variable depending on specific conditions.
 */
    fun Cpu6502.implied() { programCounter++ }

    fun Cpu6502.immediate(): U16 {
        val operand = memory[programCounter]
        programCounter++
        return operand.toU16()
    }

    fun Cpu6502.absolute(): U16 {
        val low = memory[programCounter]
        programCounter++
        val high = memory[programCounter]
        programCounter++
        return low.combineHigh(high)
    }

    fun Cpu6502.absoluteX(): U16 {
        val low = memory[programCounter]
        programCounter++
        val high = memory[programCounter]
        programCounter++
        return low.combineHigh(high) + x
    }

    fun Cpu6502.absoluteY(): U16 {
        val low = memory[programCounter]
        programCounter++
        val high = memory[programCounter]
        programCounter++
        return low.combineHigh(high) + y
    }

    fun Cpu6502.zeroPage(): U16 {
        val address = memory[programCounter]
        programCounter++
        return address.toU16()
    }

    fun Cpu6502.zeroPageX(): U16 {
        val address = (memory[programCounter] + x)
        programCounter++
        return address.toU16()
    }

    fun Cpu6502.zeroPageY(): U16 {
        val address = (memory[programCounter] + y)
        programCounter++
        return address.toU16()
    }

    fun Cpu6502.indirect(): U16 {
        val lookUpLow = memory[programCounter]
        programCounter++
        val lookUpHigh = memory[programCounter]
        programCounter++
        val lookUpAddress = lookUpLow.combineHigh(lookUpHigh)
        val dataLow = memory[lookUpAddress]
        val dataHigh = memory[lookUpAddress.inc()]
        return dataLow.combineHigh(dataHigh)
    }

    fun Cpu6502.indirectX(): U16 {
        val lookUpAddress = (memory[programCounter] + x)
        programCounter++
        val operandAddress = memory[lookUpAddress].combineHigh(memory[lookUpAddress.inc()])
        return operandAddress
    }

    fun Cpu6502.indirectY(): U16 {
        val lookUpAddress = memory[programCounter]
        programCounter++
        val low = memory[lookUpAddress]
        val high = memory[lookUpAddress.inc()]
        val operandAddress = low.combineHigh(high) + y
        return operandAddress
    }

    fun Cpu6502.relative(): U16 {
        val offset = memory[programCounter]
        programCounter++
        return (programCounter + offset)
    }