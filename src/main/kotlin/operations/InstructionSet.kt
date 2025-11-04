package org.example.operations

import org.example.Cpu6502
import org.example.util.U16
import org.example.util.U8
import org.example.util.toU8

class InstructionSet(val cpu: Cpu6502) {
    private val table = listOf<Instruction>(
        // ADC
        Instruction(
            opcode = 0x69u.toU8(),
            cycles = 2,
            addressingMode = cpu::immediate,
            operation = cpu::adc
        )

        /*// $00 - 0F
        Instruction(
            opcode = 0x00u.toU8(),
            name = "BRK impl",
            size = 1,
            operation = { cpu.brk() }
        ),
        Instruction(
            opcode = 0x01u.toU8(),
            name = "ORA X, ind",
            size = 2,
            operation = { cpu.run { ora(indirectX()) } }
        ),
        undefinedInstruction(0x02u.toU8()),
        undefinedInstruction(0x03u.toU8()),
        undefinedInstruction(0x04u.toU8()),
        Instruction(
            opcode = 0x05u.toU8(),
            name = "ORA zpg",
            size = 2,
            operation = {
                cpu.run { ora(zeroPage()) }
            }
        ),
        Instruction(
            opcode = 0x06u.toU8(),
            name = "ASL zpg",
            size = 2,
            operation = {
                cpu.run { asl(zeroPage()) }
            }
        ),
        undefinedInstruction(0x07u.toU8()),
        Instruction(
            opcode = 0x08u.toU8(),
            name = "PHP impl",
            size = 1,
            operation = { cpu.php() }
        ),
        Instruction(
            opcode = 0x09u.toU8(),
            name = "ORA #",
            size = 2,
            operation = {
                cpu.run { ora(immediate()) }
            }
        ),
        Instruction(
            opcode = 0x0Au.toU8(),
            name = "ASL A",
            size = 2,
            operation = {
                cpu.run { asla() }
            }
        ),
        undefinedInstruction(0x0Bu.toU8()),
        undefinedInstruction(0x0Cu.toU8()),
        Instruction(
            opcode = 0x0Du.toU8(),
            name = "ORA abs",
            size = 3,
            operation = {
                cpu.run { ora(absolute()) }
            }
        ),
        Instruction(
            opcode = 0x0Eu.toU8(),
            name = "ASL abs",
            size = 3,
            operation = {
                cpu.run { asl(absolute()) }
            }
        ),
        undefinedInstruction(0x0Fu.toU8()),
        // $10 - $1F
        Instruction(
            opcode = 0x10u.toU8(),
            name = "BPL rel",
            size = 2,
            operation = { cpu.run { bpl(relative()) } }
        ),
        Instruction(
            opcode = 0x11u.toU8(),
            name = "ORA ind,Y",
            size = 2,
            operation = { cpu.run { ora(indirectY()) } }
        ),
        undefinedInstruction(0x12u.toU8()),
        undefinedInstruction(0x13u.toU8()),
        undefinedInstruction(0x14u.toU8()),
        Instruction(
            opcode = 0x15u.toU8(),
            name = "ORA zpg,X",
            size = 2,
            operation = { cpu.run { ora(zeroPageX()) } }
        ),
        Instruction(
            opcode = 0x16u.toU8(),
            name = "ASL zpg,X",
            size = 2,
            operation = { cpu.run { asl(zeroPageX()) } }
        ),
        undefinedInstruction(0x17u.toU8()),
        Instruction(
            opcode = 0x18u.toU8(),
            name = "CLC impl",
            size = 1,
            operation = { cpu.run { clc() } }
        ),
        Instruction(
            opcode = 0x19u.toU8(),
            name = "ORA abs,X",
            size = 2,
            operation = { cpu.run { asl(zeroPageX()) } }
        ),*/
    )

    /*private fun undefinedInstruction(opcode: U8): Instruction =
        Instruction(
            opcode = opcode,
            name = "???",
            size = 0,
            operation = { throw IllegalStateException("called undefined illegal opcode $opcode") }
        )*/
}

data class Instruction(
    val opcode: U8,
    val cycles: Int,
    val addressingMode: () -> Unit,
    val operation: () -> Unit,
) {
    fun execute() {
        addressingMode()
        operation()
    }
}