package org.example

import org.example.operations.brk
import org.example.operations.immediate
import org.example.operations.implied
import org.example.operations.indirectX
import org.example.operations.ora
import org.example.util.U16
import org.example.util.U8
import org.example.util.getBit
import org.example.util.toU16
import org.example.util.toU8
import org.example.util.withBit
import java.lang.IllegalStateException


class Cpu6502(
    val memory: Bus = Bus(size = 65535),
) {
    var programCounter: U16 = 0x0000u.toU16()
    var status: U8 = 0x00u.toU8()
    var stackPointer: U8 = 0x00u.toU8()
    var accumulator: U8 = 0x00u.toU8()
    var x: U8 = 0x00u.toU8()
    var y: U8 = 0x00u.toU8()

    var effectiveAddress: U16? = null
    var cycleCount = 0

    var negativeFlag: Boolean
        get() = status.getBit(7)   //(status and NegativeFlagMask) > 0x00u
        set(value) {
            status = status.withBit(7, value)
        }

    var overflowFlag: Boolean
        get() = status.getBit(6)
        set(value) {
            status = status.withBit(6, value)

        }

    var decimalFlag: Boolean
        get() = status.getBit(3)
        set(value) {
            status = status.withBit(3, value)

        }

    var interruptDisableFlag: Boolean
        get() = status.getBit(2)
        set(value) {
            status = status.withBit(2, value)
        }

    var zeroFlag: Boolean
        get() = status.getBit(1)
        set(value) {
            status = status.withBit(1, value)
        }

    var carryFlag: Boolean
        get() = status.getBit(0)
        set(value) {
            status = status.withBit(0, value)
        }

    fun runCycle() {
        if (cycleCount == 0) {
            val opcode = memory[programCounter]
            programCounter++
            executeInstruction(opcode)
        }
        cycleCount--
    }

    private fun executeInstruction(opcode: U8) {
        when (opcode) {
            0x00u.toU8() -> {
                implied()
                brk()
                cycleCount += 7
            }
            0x01u.toU8() -> {
                indirectX()
                ora()
                cycleCount += 6
            }

        }
    }

    private fun getInstructionName() {

    }

    private fun getInstructionSize() {

    }

    private fun getInstructionBaseCycleCount() {

    }
}