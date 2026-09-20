package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.binary.*


/**
 * Protocals for determining the operand
 *
 * TODO: (absolute x & t absoluted indexed, y-indirect, relative, and ) CPU cycles are variable depending on specific conditions.
 */
    fun Cpu6502.implied() { programCounter++ }

    fun Cpu6502.accumulator() { programCounter++ }

    fun Cpu6502.immediate() {
        effectiveAddress = programCounter
        programCounter++

    }

    fun Cpu6502.absolute() {

        val test1 = 0x09u.toUByte()
        val test2 = 0x09u.toUByte()

        val result1 = test1 + test2
        val result2 = test1.toUShort().

        val low = memory.read(programCounter)
        programCounter++
        val high = memory.read(programCounter)
        programCounter++
        effectiveAddress = low.combineHigh(high)
    }

    fun Cpu6502.absoluteX() {
        val low = memory.read(programCounter)
        programCounter++
        val high = memory.read(programCounter)
        programCounter++
        effectiveAddress = low.combineHigh(high) + x
    }

    fun Cpu6502.absoluteY() {
        val low = memory.read(programCounter)
        programCounter++
        val high = memory.read(programCounter)
        programCounter++
        effectiveAddress = low.combineHigh(high) + y
    }

    fun Cpu6502.zeroPage() {
        val address = memory.read(programCounter)
        programCounter++
        effectiveAddress = address.toU16()
    }

    fun Cpu6502.zeroPageX() {
        val address = (memory.read(programCounter) + x)
        programCounter++
        effectiveAddress = address.toU16()
    }

    fun Cpu6502.zeroPageY() {
        val address = (memory.read(programCounter) + y)
        programCounter++
        effectiveAddress = address.toU16()
    }

    fun Cpu6502.indirect() {
        val lookUpLow = memory.read(programCounter)
        programCounter++
        val lookUpHigh = memory.read(programCounter)
        programCounter++
        val lookUpAddress = lookUpLow.combineHigh(lookUpHigh)
        val dataLow = memory.read(lookUpAddress)
        val dataHigh = memory.read(lookUpAddress.inc())
        effectiveAddress = dataLow.combineHigh(dataHigh)
    }

    fun Cpu6502.indirectX() {
        val lookUpAddress = (memory.read(programCounter) + x)
        programCounter++
        val operandAddress = memory.read(lookUpAddress).combineHigh(memory.read(lookUpAddress.inc()))
        effectiveAddress = operandAddress
    }

    fun Cpu6502.indirectY() {
        val lookUpAddress = memory.read(programCounter)
        programCounter++
        val low = memory.read(lookUpAddress)
        val high = memory.read(lookUpAddress.inc())
        val operandAddress = low.combineHigh(high) + y
        effectiveAddress = operandAddress
    }

    fun Cpu6502.relative() {
        val offset = memory.read(programCounter)
        programCounter++
        effectiveAddress = (programCounter + offset)
    }