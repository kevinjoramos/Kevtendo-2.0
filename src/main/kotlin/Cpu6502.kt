package org.example

import org.example.util.U16
import org.example.util.U8
import org.example.util.toU16
import org.example.util.toU8


class Cpu6502(
    val memory: Bus = Bus(size = 65535),
) {
    var programCounter: U16 = 0x0000u.toU16()
    var status: U8 = 0x00u.toU8()
    var stackPointer: U8 = 0x00u.toU8()
    var accumulator: U8 = 0x00u.toU8()
    var x: U8 = 0x00u.toU8()
    var y: U8 = 0x00u.toU8()

    var negativeFlag: Boolean
        get() = status.getBit(7)   //(status and NegativeFlagMask) > 0x00u
        set(value) {
            status = when(value) {
                true -> status.withSetBit(7)
                false -> status.withClearedBit(7)
            }
        }

    var overflowFlag: Boolean
        get() = status.getBit(6)
        set(value) {
            status = when(value) {
                true -> status.withSetBit(6)
                false -> status.withClearedBit(6)
            }
        }

    var decimalFlag: Boolean
        get() = status.getBit(3)
        set(value) {
            status = when(value) {
                true -> status.withSetBit(3)
                false -> status.withClearedBit(3)
            }
        }

    var interruptDisableFlag: Boolean
        get() = status.getBit(2)
        set(value) {
            status = when(value) {
                true -> status.withSetBit(2)
                false -> status.withClearedBit(2)
            }
        }

    var zeroFlag: Boolean
        get() = status.getBit(1)
        set(value) {
            status = when(value) {
                true -> status.withSetBit(1)
                false -> status.withClearedBit(1)
            }
        }

    var carryFlag: Boolean
        get() = status.getBit(0)
        set(value) {
            status = when(value) {
                true -> status.withSetBit(0)
                false -> status.withClearedBit(0)
            }
        }


    fun executeCycle() {
        // fetch opcode
        val opcode = memory[programCounter]
        programCounter++
        // execute opcode
    }

    private fun executeOpcode(opcode: U8) {
        when (opcode) {
            0x00u.toU8() -> Unit
            0x01u.toU8() -> Unit
            0x02u.toU8() -> Unit
            0x03u.toU8() -> Unit
            0x04u.toU8() -> Unit
            0x05u.toU8() -> Unit
            0x06u.toU8() -> Unit
            0x07u.toU8() -> Unit
            0x08u.toU8() -> Unit
            0x09u.toU8() -> Unit
            0x0Au.toU8() -> Unit
            0x0Bu.toU8() -> Unit
            0x0Cu.toU8() -> Unit
            0x0Du.toU8() -> Unit
            0x0Eu.toU8() -> Unit
            0x0Fu.toU8() -> Unit
            0x10u.toU8() -> Unit
            0x11u.toU8() -> Unit
            0x12u.toU8() -> Unit
            0x13u.toU8() -> Unit
            0x14u.toU8() -> Unit
            0x15u.toU8() -> Unit
            0x16u.toU8() -> Unit
            0x17u.toU8() -> Unit
            0x18u.toU8() -> Unit
            0x19u.toU8() -> Unit
            0x1Au.toU8() -> Unit
            0x1Bu.toU8() -> Unit
            0x1Cu.toU8() -> Unit
            0x1Du.toU8() -> Unit
            0x1Eu.toU8() -> Unit
            0x1Fu.toU8() -> Unit
            0x20u.toU8() -> Unit
            0x21u.toU8() -> Unit
            0x22u.toU8() -> Unit
            0x23u.toU8() -> Unit
            0x24u.toU8() -> Unit
            0x25u.toU8() -> Unit
            0x26u.toU8() -> Unit
            0x27u.toU8() -> Unit
            0x28u.toU8() -> Unit
            0x29u.toU8() -> Unit
            0x2Au.toU8() -> Unit
            0x2Bu.toU8() -> Unit
            0x2Cu.toU8() -> Unit
            0x2Du.toU8() -> Unit
            0x2Eu.toU8() -> Unit
            0x2Fu.toU8() -> Unit
            0x30u.toU8() -> Unit
            0x31u.toU8() -> Unit
            0x32u.toU8() -> Unit
            0x33u.toU8() -> Unit
            0x34u.toU8() -> Unit
            0x35u.toU8() -> Unit
            0x36u.toU8() -> Unit
            0x37u.toU8() -> Unit
            0x38u.toU8() -> Unit
            0x39u.toU8() -> Unit
            0x3Au.toU8() -> Unit
            0x3Bu.toU8() -> Unit
            0x3Cu.toU8() -> Unit
            0x3Du.toU8() -> Unit
            0x3Eu.toU8() -> Unit
            0x3Fu.toU8() -> Unit
            0x40u.toU8() -> Unit
            0x41u.toU8() -> Unit
            0x42u.toU8() -> Unit
            0x43u.toU8() -> Unit
            0x44u.toU8() -> Unit
            0x45u.toU8() -> Unit
            0x46u.toU8() -> Unit
            0x47u.toU8() -> Unit
            0x48u.toU8() -> Unit
            0x49u.toU8() -> Unit
            0x4Au.toU8() -> Unit
            0x4Bu.toU8() -> Unit
            0x4Cu.toU8() -> Unit
            0x4Du.toU8() -> Unit
            0x4Eu.toU8() -> Unit
            0x4Fu.toU8() -> Unit
            0x50u.toU8() -> Unit
            0x51u.toU8() -> Unit
            0x52u.toU8() -> Unit
            0x53u.toU8() -> Unit
            0x54u.toU8() -> Unit
            0x55u.toU8() -> Unit
            0x56u.toU8() -> Unit
            0x57u.toU8() -> Unit
            0x58u.toU8() -> Unit
            0x59u.toU8() -> Unit
            0x5Au.toU8() -> Unit
            0x5Bu.toU8() -> Unit
            0x5Cu.toU8() -> Unit
            0x5Du.toU8() -> Unit
            0x5Eu.toU8() -> Unit
            0x5Fu.toU8() -> Unit
            0x60u.toU8() -> Unit
            0x61u.toU8() -> Unit
            0x62u.toU8() -> Unit
            0x63u.toU8() -> Unit
            0x64u.toU8() -> Unit
            0x65u.toU8() -> Unit
            0x66u.toU8() -> Unit
            0x67u.toU8() -> Unit
            0x68u.toU8() -> Unit
            0x69u.toU8() -> Unit
            0x6Au.toU8() -> Unit
            0x6Bu.toU8() -> Unit
            0x6Cu.toU8() -> Unit
            0x6Du.toU8() -> Unit
            0x6Eu.toU8() -> Unit
            0x6Fu.toU8() -> Unit
            0x70u.toU8() -> Unit
            0x71u.toU8() -> Unit
            0x72u.toU8() -> Unit
            0x73u.toU8() -> Unit
            0x74u.toU8() -> Unit
            0x75u.toU8() -> Unit
            0x76u.toU8() -> Unit
            0x77u.toU8() -> Unit
            0x78u.toU8() -> Unit
            0x79u.toU8() -> Unit
            0x7Au.toU8() -> Unit
            0x7Bu.toU8() -> Unit
            0x7Cu.toU8() -> Unit
            0x7Du.toU8() -> Unit
            0x7Eu.toU8() -> Unit
            0x7Fu.toU8() -> Unit
            0x80u.toU8() -> Unit
            0x81u.toU8() -> Unit
            0x82u.toU8() -> Unit
            0x83u.toU8() -> Unit
            0x84u.toU8() -> Unit
            0x85u.toU8() -> Unit
            0x86u.toU8() -> Unit
            0x87u.toU8() -> Unit
            0x88u.toU8() -> Unit
            0x89u.toU8() -> Unit
            0x8Au.toU8() -> Unit
            0x8Bu.toU8() -> Unit
            0x8Cu.toU8() -> Unit
            0x8Du.toU8() -> Unit
            0x8Eu.toU8() -> Unit
            0x8Fu.toU8() -> Unit
            0x90u.toU8() -> Unit
            0x91u.toU8() -> Unit
            0x92u.toU8() -> Unit
            0x93u.toU8() -> Unit
            0x94u.toU8() -> Unit
            0x95u.toU8() -> Unit
            0x96u.toU8() -> Unit
            0x97u.toU8() -> Unit
            0x98u.toU8() -> Unit
            0x99u.toU8() -> Unit
            0x9Au.toU8() -> Unit
            0x9Bu.toU8() -> Unit
            0x9Cu.toU8() -> Unit
            0x9Du.toU8() -> Unit
            0x9Eu.toU8() -> Unit
            0x9Fu.toU8() -> Unit
            0xA0u.toU8() -> Unit
            0xA1u.toU8() -> Unit
            0xA2u.toU8() -> Unit
            0xA3u.toU8() -> Unit
            0xA4u.toU8() -> Unit
            0xA5u.toU8() -> Unit
            0xA6u.toU8() -> Unit
            0xA7u.toU8() -> Unit
            0xA8u.toU8() -> Unit
            0xA9u.toU8() -> Unit
            0xAAu.toU8() -> Unit
            0xABu.toU8() -> Unit
            0xACu.toU8() -> Unit
            0xADu.toU8() -> Unit
            0xAEu.toU8() -> Unit
            0xAFu.toU8() -> Unit
            0xB0u.toU8() -> Unit
            0xB1u.toU8() -> Unit
            0xB2u.toU8() -> Unit
            0xB3u.toU8() -> Unit
            0xB4u.toU8() -> Unit
            0xB5u.toU8() -> Unit
            0xB6u.toU8() -> Unit
            0xB7u.toU8() -> Unit
            0xB8u.toU8() -> Unit
            0xB9u.toU8() -> Unit
            0xBAu.toU8() -> Unit
            0xBBu.toU8() -> Unit
            0xBCu.toU8() -> Unit
            0xBDu.toU8() -> Unit
            0xBEu.toU8() -> Unit
            0xBFu.toU8() -> Unit
            0xC0u.toU8() -> Unit
            0xC1u.toU8() -> Unit
            0xC2u.toU8() -> Unit
            0xC3u.toU8() -> Unit
            0xC4u.toU8() -> Unit
            0xC5u.toU8() -> Unit
            0xC6u.toU8() -> Unit
            0xC7u.toU8() -> Unit
            0xC8u.toU8() -> Unit
            0xC9u.toU8() -> Unit
            0xCAu.toU8() -> Unit
            0xCBu.toU8() -> Unit
            0xCCu.toU8() -> Unit
            0xCDu.toU8() -> Unit
            0xCEu.toU8() -> Unit
            0xCFu.toU8() -> Unit
            0xD0u.toU8() -> Unit
            0xD1u.toU8() -> Unit
            0xD2u.toU8() -> Unit
            0xD3u.toU8() -> Unit
            0xD4u.toU8() -> Unit
            0xD5u.toU8() -> Unit
            0xD6u.toU8() -> Unit
            0xD7u.toU8() -> Unit
            0xD8u.toU8() -> Unit
            0xD9u.toU8() -> Unit
            0xDAu.toU8() -> Unit
            0xDBu.toU8() -> Unit
            0xDCu.toU8() -> Unit
            0xDDu.toU8() -> Unit
            0xDEu.toU8() -> Unit
            0xDFu.toU8() -> Unit
            0xE0u.toU8() -> Unit
            0xE1u.toU8() -> Unit
            0xE2u.toU8() -> Unit
            0xE3u.toU8() -> Unit
            0xE4u.toU8() -> Unit
            0xE5u.toU8() -> Unit
            0xE6u.toU8() -> Unit
            0xE7u.toU8() -> Unit
            0xE8u.toU8() -> Unit
            0xE9u.toU8() -> Unit
            0xEAu.toU8() -> Unit
            0xEBu.toU8() -> Unit
            0xECu.toU8() -> Unit
            0xEDu.toU8() -> Unit
            0xEEu.toU8() -> Unit
            0xEFu.toU8() -> Unit
            0xF0u.toU8() -> Unit
            0xF1u.toU8() -> Unit
            0xF2u.toU8() -> Unit
            0xF3u.toU8() -> Unit
            0xF4u.toU8() -> Unit
            0xF5u.toU8() -> Unit
            0xF6u.toU8() -> Unit
            0xF7u.toU8() -> Unit
            0xF8u.toU8() -> Unit
            0xF9u.toU8() -> Unit
            0xFAu.toU8() -> Unit
            0xFBu.toU8() -> Unit
            0xFCu.toU8() -> Unit
            0xFDu.toU8() -> Unit
            0xFEu.toU8() -> Unit
            0xFFu.toU8() -> Unit
        }
    }
}