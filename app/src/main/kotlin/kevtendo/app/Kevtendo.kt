package kevtendo.app

import kevtendo.cartridge.Cartridge
import kevtendo.cartridge.NesFile
import kevtendo.cartridge.NesFileFactory
import kevtendo.common.binary.U16
import kevtendo.common.binary.U8
import kevtendo.common.binary.U8Array
import kevtendo.common.binary.get
import kevtendo.common.binary.set
import kevtendo.common.binary.toU16
import kevtendo.common.mediator.Bus
import kevtendo.cpu.Cpu6502
import java.io.File
import java.nio.file.Path

fun main(args: Array<String>) {
    val file = File("/home/kevin/Downloads/nestest.nes")
    val nesFile = NesFileFactory.parse(file)
    println(nesFile)
}

class Kevtendo : Bus {
    val cpu = Cpu6502(this)
    val cartridge = Cartridge()
    
    private val internalRAM = U8Array(2048)

    override fun read(address: U16): U8 {
        // internal ram
        if (address in 0x0000u.toU16()..0x1FFFu.toU16()) {
            return internalRAM[address.mod(0x0800u.toU16())]
        }
        // ppu registers
        if (address in 0x2000u.toU16()..0x3FFFu.toU16()) {
            when (address.mod(0x0008u.toU16())) {
                0u.toU16() -> 0x00u.toU16()
                1u.toU16() -> 0x00u.toU16()
                2u.toU16() -> 0x00u.toU16()
                3u.toU16() -> 0x00u.toU16()
                4u.toU16() -> 0x00u.toU16()
                5u.toU16() -> 0x00u.toU16()
                6u.toU16() -> 0x00u.toU16()
                7u.toU16() -> 0x00u.toU16()
            }
        }
        // apu and io registers
        if (address in 0x4000u.toU16()..0x4017u.toU16()) {
            0x00u.toU16()
        }
        // apu and io functionality that is normally disabled.
        if (address in 0x4018u.toU16()..0x401Fu.toU16()) {
            0x00u.toU16()
        }
        // cartridge
        if (address in 0x4020u.toU16()..0xFFFFu.toU16()) {
            cartridge.read(address)
        }
        throw IllegalArgumentException("Undefined behavior at address: $address")
    }

    override fun write(address: U16, data: U8) {
        if (address in 0x0000u.toU16()..0x1FFFu.toU16()) {
            internalRAM[address.mod(0x0800u.toU16())] = data
        }
        // ppu registers
        if (address in 0x2000u.toU16()..0x3FFFu.toU16()) {
            when (address.mod(0x0008u.toU16())) {
                0u.toU16() -> 0x00u.toU16()
                1u.toU16() -> 0x00u.toU16()
                2u.toU16() -> 0x00u.toU16()
                3u.toU16() -> 0x00u.toU16()
                4u.toU16() -> 0x00u.toU16()
                5u.toU16() -> 0x00u.toU16()
                6u.toU16() -> 0x00u.toU16()
                7u.toU16() -> 0x00u.toU16()
            }
        }
        // apu and io registers
        if (address in 0x4000u.toU16()..0x4017u.toU16()) {
            0x00u.toU16()
        }
        // apu and io functionality that is normally disabled.
        if (address in 0x4018u.toU16()..0x401Fu.toU16()) {
            0x00u.toU16()
        }
        // cartridge
        if (address in 0x4020u.toU16()..0xFFFFu.toU16()) {
            cartridge.write(address, data)
        }
        throw IllegalArgumentException("Undefined behavior at address: $address")
    }

    fun loadCartridge(file: File) {
        cartridge.load(file)
    }
}