package kevtendo.cpu.operations

import kevtendo.common.binary.U8

object InstructionSet {

    fun fetchInstruction(opcode: U8): Instruction {
        return table[opcode.toUInt().toInt()] ?: throw IllegalStateException("called undefined illegal opcode $opcode")
    }

    private val table = Array<Instruction?>(256) { null }
        .apply {
            // ADC
            set(0x69u.toInt(), Instruction(AddressingMode.Immediate, Operation.ADC, 2))
            set(0x65u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.ADC, 3))
            set(0x75u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.ADC, 4))
            set(0x6Du.toInt(), Instruction(AddressingMode.Absolute, Operation.ADC, 4))
            set(0x7Du.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.ADC, 4))
            set(0x79u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.ADC, 4))
            set(0x61u.toInt(), Instruction(AddressingMode.IndirectX, Operation.ADC, 6))
            set(0x71u.toInt(), Instruction(AddressingMode.IndirectY, Operation.ADC, 5))

            // AND
            set(0x29u.toInt(), Instruction(AddressingMode.Immediate, Operation.AND, 2))
            set(0x25u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.AND, 3))
            set(0x35u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.AND, 4))
            set(0x2Du.toInt(), Instruction(AddressingMode.Absolute, Operation.AND, 4))
            set(0x3Du.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.AND, 4))
            set(0x39u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.AND, 4))
            set(0x21u.toInt(), Instruction(AddressingMode.IndirectX, Operation.AND, 6))
            set(0x31u.toInt(), Instruction(AddressingMode.IndirectY, Operation.AND, 5))

            // ASL
            set(0x0Au.toInt(), Instruction(AddressingMode.Accumulator, Operation.ASL, 2))
            set(0x06u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.ASL, 5))
            set(0x16u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.ASL, 6))
            set(0x0Eu.toInt(), Instruction(AddressingMode.Absolute, Operation.ASL, 6))
            set(0x1Eu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.ASL, 7))

            // BCC
            set(0x90u.toInt(), Instruction(AddressingMode.Relative, Operation.BCC, 2))

            // BCS
            set(0xB0u.toInt(), Instruction(AddressingMode.Relative, Operation.BCS, 2))

            // BEQ
            set(0xF0u.toInt(), Instruction(AddressingMode.Relative, Operation.BEQ, 2))

            // BIT
            set(0x24u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.BIT, 3))
            set(0x2Cu.toInt(), Instruction(AddressingMode.Absolute, Operation.BIT, 4))

            // BMI
            set(0x30u.toInt(), Instruction(AddressingMode.Relative, Operation.BMI, 2))

            // BNE
            set(0xD0u.toInt(), Instruction(AddressingMode.Relative, Operation.BNE, 2))

            // BPL
            set(0x10u.toInt(), Instruction(AddressingMode.Relative, Operation.BPL, 2))

            // BRK
            set(0x00u.toInt(), Instruction(AddressingMode.Implied, Operation.BRK, 7))

            // BVC
            set(0x50u.toInt(), Instruction(AddressingMode.Relative, Operation.BVC, 2))

            // BVS
            set(0x70u.toInt(), Instruction(AddressingMode.Relative, Operation.BVS, 2))

            // CLC
            set(0x18u.toInt(), Instruction(AddressingMode.Implied, Operation.CLC, 2))

            // CLD
            set(0xD8u.toInt(), Instruction(AddressingMode.Implied, Operation.CLD, 2))

            // CLI
            set(0x58u.toInt(), Instruction(AddressingMode.Implied, Operation.CLI, 2))

            // CLV
            set(0xB8u.toInt(), Instruction(AddressingMode.Implied, Operation.CLV, 2))

            // CMP
            set(0xC9u.toInt(), Instruction(AddressingMode.Immediate, Operation.CMP, 2))
            set(0xC5u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.CMP, 3))
            set(0xD5u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.CMP, 4))
            set(0xCDu.toInt(), Instruction(AddressingMode.Absolute, Operation.CMP, 4))
            set(0xDDu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.CMP, 4))
            set(0xD9u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.CMP, 4))
            set(0xC1u.toInt(), Instruction(AddressingMode.IndirectX, Operation.CMP, 6))
            set(0xD1u.toInt(), Instruction(AddressingMode.IndirectY, Operation.CMP, 5))

            // CPX
            set(0xE0u.toInt(), Instruction(AddressingMode.Immediate, Operation.CPX, 2))
            set(0xE4u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.CPX, 3))
            set(0xECu.toInt(), Instruction(AddressingMode.Absolute, Operation.CPX, 4))

            // CPY
            set(0xC0u.toInt(), Instruction(AddressingMode.Immediate, Operation.CPY, 2))
            set(0xC4u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.CPY, 3))
            set(0xCCu.toInt(), Instruction(AddressingMode.Absolute, Operation.CPY, 4))

            // DEC
            set(0xC6u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.DEC, 5))
            set(0xD6u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.DEC, 6))
            set(0xCEu.toInt(), Instruction(AddressingMode.Absolute, Operation.DEC, 6))
            set(0xDEu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.DEC, 7))

            // DEX
            set(0xCAu.toInt(), Instruction(AddressingMode.Implied, Operation.DEX, 2))

            // DEY
            set(0x88u.toInt(), Instruction(AddressingMode.Implied, Operation.DEY, 2))

            // EOR
            set(0x49u.toInt(), Instruction(AddressingMode.Immediate, Operation.EOR, 2))
            set(0x45u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.EOR, 3))
            set(0x55u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.EOR, 4))
            set(0x4Du.toInt(), Instruction(AddressingMode.Absolute, Operation.EOR, 4))
            set(0x5Du.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.EOR, 4))
            set(0x59u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.EOR, 4))
            set(0x41u.toInt(), Instruction(AddressingMode.IndirectX, Operation.EOR, 6))
            set(0x51u.toInt(), Instruction(AddressingMode.IndirectY, Operation.EOR, 5))

            // INC
            set(0xE6u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.INC, 5))
            set(0xF6u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.INC, 6))
            set(0xEEu.toInt(), Instruction(AddressingMode.Absolute, Operation.INC, 6))
            set(0xFEu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.INC, 7))

            // INX
            set(0xE8u.toInt(), Instruction(AddressingMode.Implied, Operation.INX, 2))

            // INY
            set(0xC8u.toInt(), Instruction(AddressingMode.Implied, Operation.INY, 2))

            // JMP
            set(0x4Cu.toInt(), Instruction(AddressingMode.Absolute, Operation.JMP, 3))
            set(0x6Cu.toInt(), Instruction(AddressingMode.Indirect, Operation.JMP, 5))

            // JSR
            set(0x20u.toInt(), Instruction(AddressingMode.Absolute, Operation.JSR, 6))

            // LDA
            set(0xA9u.toInt(), Instruction(AddressingMode.Immediate, Operation.LDA, 2))
            set(0xA5u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.LDA, 3))
            set(0xB5u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.LDA, 4))
            set(0xADu.toInt(), Instruction(AddressingMode.Absolute, Operation.LDA, 4))
            set(0xBDu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.LDA, 4))
            set(0xB9u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.LDA, 4))
            set(0xA1u.toInt(), Instruction(AddressingMode.IndirectX, Operation.LDA, 6))
            set(0xB1u.toInt(), Instruction(AddressingMode.IndirectY, Operation.LDA, 5))

            // LDX
            set(0xA2u.toInt(), Instruction(AddressingMode.Immediate, Operation.LDX, 2))
            set(0xA6u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.LDX, 3))
            set(0xB6u.toInt(), Instruction(AddressingMode.ZeroPageY, Operation.LDX, 4))
            set(0xAEu.toInt(), Instruction(AddressingMode.Absolute, Operation.LDX, 4))
            set(0xBEu.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.LDX, 4))

            // LDY
            set(0xA0u.toInt(), Instruction(AddressingMode.Immediate, Operation.LDY, 2))
            set(0xA4u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.LDY, 3))
            set(0xB4u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.LDY, 4))
            set(0xACu.toInt(), Instruction(AddressingMode.Absolute, Operation.LDY, 4))
            set(0xBCu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.LDY, 4))

            // LSR
            set(0x4Au.toInt(), Instruction(AddressingMode.Accumulator, Operation.LSR, 2))
            set(0x46u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.LSR, 5))
            set(0x56u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.LSR, 6))
            set(0x4Eu.toInt(), Instruction(AddressingMode.Absolute, Operation.LSR, 6))
            set(0x5Eu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.LSR, 7))

            // NOP
            set(0xEAu.toInt(), Instruction(AddressingMode.Implied, Operation.NOP, 2))

            // ORA
            set(0x09u.toInt(), Instruction(AddressingMode.Immediate, Operation.ORA, 2))
            set(0x05u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.ORA, 3))
            set(0x15u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.ORA, 4))
            set(0x0Du.toInt(), Instruction(AddressingMode.Absolute, Operation.ORA, 4))
            set(0x1Du.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.ORA, 4))
            set(0x19u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.ORA, 4))
            set(0x01u.toInt(), Instruction(AddressingMode.IndirectX, Operation.ORA, 6))
            set(0x11u.toInt(), Instruction(AddressingMode.IndirectY, Operation.ORA, 5))

            // PHA
            set(0x48u.toInt(), Instruction(AddressingMode.Implied, Operation.PHA, 3))

            // PHP
            set(0x08u.toInt(), Instruction(AddressingMode.Implied, Operation.PHP, 3))

            // PLA
            set(0x68u.toInt(), Instruction(AddressingMode.Implied, Operation.PLA, 4))

            // PLP
            set(0x28u.toInt(), Instruction(AddressingMode.Implied, Operation.PLP, 4))


            // ROL
            set(0x2Au.toInt(), Instruction(AddressingMode.Accumulator, Operation.ROL, 2))
            set(0x26u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.ROL, 5))
            set(0x36u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.ROL, 6))
            set(0x2Eu.toInt(), Instruction(AddressingMode.Absolute, Operation.ROL, 6))
            set(0x3Eu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.ROL, 7))

            // ROR
            set(0x6Au.toInt(), Instruction(AddressingMode.Accumulator, Operation.ROR, 2))
            set(0x66u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.ROR, 5))
            set(0x76u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.ROR, 6))
            set(0x6Eu.toInt(), Instruction(AddressingMode.Absolute, Operation.ROR, 6))
            set(0x7Eu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.ROR, 7))

            // RTI
            set(0x40u.toInt(), Instruction(AddressingMode.Implied, Operation.RTI, 6))

            // RTS
            set(0x60u.toInt(), Instruction(AddressingMode.Implied, Operation.RTS, 6))

            // SBC
            set(0xE9u.toInt(), Instruction(AddressingMode.Immediate, Operation.SBC, 2))
            set(0xE5u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.SBC, 3))
            set(0xF5u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.SBC, 4))
            set(0xEDu.toInt(), Instruction(AddressingMode.Absolute, Operation.SBC, 4))
            set(0xFDu.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.SBC, 4))
            set(0xF9u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.SBC, 4))
            set(0xE1u.toInt(), Instruction(AddressingMode.IndirectX, Operation.SBC, 6))
            set(0xF1u.toInt(), Instruction(AddressingMode.IndirectY, Operation.SBC, 5))

            // SEC
            set(0x38u.toInt(), Instruction(AddressingMode.Implied, Operation.SEC, 2))

            // SED
            set(0xF8u.toInt(), Instruction(AddressingMode.Implied, Operation.SED, 2))

            // SEI
            set(0x78u.toInt(), Instruction(AddressingMode.Implied, Operation.SEI, 2))

            // STA
            set(0x85u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.STA, 3))
            set(0x95u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.STA, 4))
            set(0x8Du.toInt(), Instruction(AddressingMode.Absolute, Operation.STA, 4))
            set(0x9Du.toInt(), Instruction(AddressingMode.AbsoluteX, Operation.STA, 5))
            set(0x99u.toInt(), Instruction(AddressingMode.AbsoluteY, Operation.STA, 5))
            set(0x81u.toInt(), Instruction(AddressingMode.IndirectX, Operation.STA, 6))
            set(0x91u.toInt(), Instruction(AddressingMode.IndirectY, Operation.STA, 6))

            // STX
            set(0x86u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.STX, 3))
            set(0x96u.toInt(), Instruction(AddressingMode.ZeroPageY, Operation.STX, 4))
            set(0x8Eu.toInt(), Instruction(AddressingMode.Absolute, Operation.STX, 4))

            // STY
            set(0x84u.toInt(), Instruction(AddressingMode.ZeroPage, Operation.STY, 3))
            set(0x94u.toInt(), Instruction(AddressingMode.ZeroPageX, Operation.STY, 4))
            set(0x8Cu.toInt(), Instruction(AddressingMode.Absolute, Operation.STY, 4))

            // TAX
            set(0xAAu.toInt(), Instruction(AddressingMode.Implied, Operation.TAX, 2))

            // TAY
            set(0xA8u.toInt(), Instruction(AddressingMode.Implied, Operation.TAY, 2))

            // TSX
            set(0xBAu.toInt(), Instruction(AddressingMode.Implied, Operation.TSX, 2))

            // TXA
            set(0x8Au.toInt(), Instruction(AddressingMode.Implied, Operation.TXA, 2))

            // TXS
            set(0x9Au.toInt(), Instruction(AddressingMode.Implied, Operation.TXS, 2))

            // TYA
            set(0x98u.toInt(), Instruction(AddressingMode.Implied, Operation.TYA, 2))
        }
}


enum class AddressingMode {
    Implied,
    Accumulator,
    Immediate,
    ZeroPage,
    ZeroPageX,
    ZeroPageY,
    Relative,
    Absolute,
    AbsoluteX,
    AbsoluteY,
    Indirect,
    IndirectX,
    IndirectY,
}

enum class Operation {
    ADC,
    AND,
    ASL,
    BCC,
    BCS,
    BEQ,
    BIT,
    BMI,
    BNE,
    BPL,
    BRK,
    BVC,
    BVS,
    CLC,
    CLD,
    CLI,
    CLV,
    CMP,
    CPX,
    CPY,
    DEC,
    DEX,
    DEY,
    EOR,
    INC,
    INX,
    INY,
    JMP,
    JSR,
    LDA,
    LDX,
    LDY,
    LSR,
    NOP,
    ORA,
    PHA,
    PHP,
    PLA,
    PLP,
    ROL,
    ROR,
    RTI,
    RTS,
    SBC,
    SEC,
    SED,
    SEI,
    STA,
    STX,
    STY,
    TAX,
    TAY,
    TSX,
    TXA,
    TXS,
    TYA,
}

data class Instruction(
    val addressingMode: AddressingMode,
    val operation: Operation,
    val cycles: Int,
)