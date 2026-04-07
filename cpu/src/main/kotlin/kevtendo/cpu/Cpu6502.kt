package kevtendo.cpu

import kevtendo.cpu.operations.AddressingMode
import kevtendo.cpu.operations.Instruction
import kevtendo.cpu.operations.InstructionSet
import kevtendo.cpu.operations.Operation
import kevtendo.cpu.operations.absolute
import kevtendo.cpu.operations.absoluteX
import kevtendo.cpu.operations.absoluteY
import kevtendo.cpu.operations.accumulator
import kevtendo.cpu.operations.adc
import kevtendo.cpu.operations.and
import kevtendo.cpu.operations.asl
import kevtendo.cpu.operations.asla
import kevtendo.cpu.operations.bcc
import kevtendo.cpu.operations.bcs
import kevtendo.cpu.operations.beq
import kevtendo.cpu.operations.bit
import kevtendo.cpu.operations.bmi
import kevtendo.cpu.operations.bne
import kevtendo.cpu.operations.bpl
import kevtendo.cpu.operations.brk
import kevtendo.cpu.operations.bvc
import kevtendo.cpu.operations.bvs
import kevtendo.cpu.operations.clc
import kevtendo.cpu.operations.cld
import kevtendo.cpu.operations.cli
import kevtendo.cpu.operations.clv
import kevtendo.cpu.operations.cmp
import kevtendo.cpu.operations.cpx
import kevtendo.cpu.operations.cpy
import kevtendo.cpu.operations.dec
import kevtendo.cpu.operations.dex
import kevtendo.cpu.operations.dey
import kevtendo.cpu.operations.eor
import kevtendo.cpu.operations.immediate
import kevtendo.cpu.operations.implied
import kevtendo.cpu.operations.inc
import kevtendo.cpu.operations.indirect
import kevtendo.cpu.operations.indirectX
import kevtendo.cpu.operations.indirectY
import kevtendo.cpu.operations.inx
import kevtendo.cpu.operations.iny
import kevtendo.cpu.operations.jmp
import kevtendo.cpu.operations.jsr
import kevtendo.cpu.operations.lda
import kevtendo.cpu.operations.ldx
import kevtendo.cpu.operations.ldy
import kevtendo.cpu.operations.lsr
import kevtendo.cpu.operations.lsra
import kevtendo.cpu.operations.nop
import kevtendo.cpu.operations.ora
import kevtendo.cpu.operations.pha
import kevtendo.cpu.operations.php
import kevtendo.cpu.operations.pla
import kevtendo.cpu.operations.plp
import kevtendo.cpu.operations.relative
import kevtendo.cpu.operations.rol
import kevtendo.cpu.operations.rola
import kevtendo.cpu.operations.ror
import kevtendo.cpu.operations.rora
import kevtendo.cpu.operations.rti
import kevtendo.cpu.operations.rts
import kevtendo.cpu.operations.sbc
import kevtendo.cpu.operations.sec
import kevtendo.cpu.operations.sed
import kevtendo.cpu.operations.sei
import kevtendo.cpu.operations.sta
import kevtendo.cpu.operations.stx
import kevtendo.cpu.operations.sty
import kevtendo.cpu.operations.tax
import kevtendo.cpu.operations.tay
import kevtendo.cpu.operations.tsx
import kevtendo.cpu.operations.txa
import kevtendo.cpu.operations.txs
import kevtendo.cpu.operations.tya
import kevtendo.cpu.operations.zeroPage
import kevtendo.cpu.operations.zeroPageX
import kevtendo.cpu.operations.zeroPageY
import kevtendo.common.binary.U16
import kevtendo.common.binary.U8
import kevtendo.common.binary.getBit
import kevtendo.common.binary.toU16
import kevtendo.common.binary.toU8
import kevtendo.common.binary.withBit
import kevtendo.common.binary.Bus
import kevtendo.common.binary.U8Array
import kevtendo.common.binary.get


class Cpu6502(
    val memory: Bus = U8Array(size = 65535),
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
            val instruction = InstructionSet.fetchInstruction(opcode)
            doOperation(instruction)
            cycleCount += instruction.cycles
        }
        cycleCount--
    }

    private fun doOperation(
        instruction: Instruction
    ) {
        when (instruction.addressingMode) {
            AddressingMode.Implied -> implied()
            AddressingMode.Accumulator -> accumulator()
            AddressingMode.Immediate -> immediate()
            AddressingMode.ZeroPage -> zeroPage()
            AddressingMode.ZeroPageX -> zeroPageX()
            AddressingMode.ZeroPageY -> zeroPageY()
            AddressingMode.Relative -> relative()
            AddressingMode.Absolute -> absolute()
            AddressingMode.AbsoluteX -> absoluteX()
            AddressingMode.AbsoluteY -> absoluteY()
            AddressingMode.Indirect -> indirect()
            AddressingMode.IndirectX -> indirectX()
            AddressingMode.IndirectY -> indirectY()
        }
        when (instruction.operation) {
            Operation.ADC -> adc()
            Operation.AND -> and()
            Operation.ASL -> if (instruction.addressingMode == AddressingMode.Accumulator)  asla() else asl()
            Operation.BCC -> bcc()
            Operation.BCS -> bcs()
            Operation.BEQ -> beq()
            Operation.BIT -> bit()
            Operation.BMI -> bmi()
            Operation.BNE -> bne()
            Operation.BPL -> bpl()
            Operation.BRK -> brk()
            Operation.BVC -> bvc()
            Operation.BVS -> bvs()
            Operation.CLC -> clc()
            Operation.CLD -> cld()
            Operation.CLI -> cli()
            Operation.CLV -> clv()
            Operation.CMP -> cmp()
            Operation.CPX -> cpx()
            Operation.CPY -> cpy()
            Operation.DEC -> dec()
            Operation.DEX -> dex()
            Operation.DEY -> dey()
            Operation.EOR -> eor()
            Operation.INC -> inc()
            Operation.INX -> inx()
            Operation.INY -> iny()
            Operation.JMP -> jmp()
            Operation.JSR -> jsr()
            Operation.LDA -> lda()
            Operation.LDX -> ldx()
            Operation.LDY -> ldy()
            Operation.LSR -> if (instruction.addressingMode == AddressingMode.Accumulator) lsra() else lsr()
            Operation.NOP -> nop()
            Operation.ORA -> ora()
            Operation.PHA -> pha()
            Operation.PHP -> php()
            Operation.PLA -> pla()
            Operation.PLP -> plp()
            Operation.ROL -> if (instruction.addressingMode == AddressingMode.Accumulator) rola() else rol()
            Operation.ROR -> if (instruction.addressingMode == AddressingMode.Accumulator) rora() else ror()
            Operation.RTI -> rti()
            Operation.RTS -> rts()
            Operation.SBC -> sbc()
            Operation.SEC -> sec()
            Operation.SED -> sed()
            Operation.SEI -> sei()
            Operation.STA -> sta()
            Operation.STX -> stx()
            Operation.STY -> sty()
            Operation.TAX -> tax()
            Operation.TAY -> tay()
            Operation.TSX -> tsx()
            Operation.TXA -> txa()
            Operation.TXS -> txs()
            Operation.TYA -> tya()
        }
    }
}