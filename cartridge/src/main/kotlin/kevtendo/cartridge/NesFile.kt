package kevtendo.cartridge

import kevtendo.common.binary.U8
import kevtendo.common.binary.U8Array
import kevtendo.common.binary.getBit
import kevtendo.common.binary.toU8
import kevtendo.common.binary.toU8Array
import java.io.File

object NesFileFactory {
    @OptIn(ExperimentalUnsignedTypes::class)
    fun parse(file: File): NesFile {
        val bytes = runCatching { file.readBytes() }
            .getOrElse { e -> throw e }
        if (!bytes.sliceArray(0..3).contentEquals((HeaderConstant))) {
            println("File $file header does not contain proper constant.")
        }
        val flag6 = bytes[6].toU8()
        val flag7 = bytes[7].toU8()

        val mapperId = ((flag7 and 0xF0u.toU8()) or flag6.shr(4))

        val isNes2Format = flag7.getBit(3) && !flag7.getBit(2)

        val nametableArrangement = when {
            flag6.getBit(3) -> NametableArrangment.Custom
            flag6.getBit(0) -> NametableArrangment.Horizontal
            else -> NametableArrangment.Vertical
        }
        val hasTrainer = flag6.getBit(2)
        val prgRomStart = if (hasTrainer) 528 else 16
        val prgRomEnd = prgRomStart + 16384 * bytes[4].toInt()
        val chrRomStart = prgRomEnd + 1
        val chrRomEnd = chrRomStart + 8192 * bytes[5].toInt()
        return NesFile(
            mapper = mapperId,
            prgRom = bytes.sliceArray(prgRomStart..prgRomEnd).toU8Array(),
            chrRom = bytes.sliceArray(chrRomStart..chrRomEnd).toU8Array(),
            nametableArrangement = nametableArrangement
        )
    }

    private val HeaderConstant = byteArrayOf(0x4e, 0x45, 0x53, 0x1a)
}

data class NesFile(
    val mapper: U8,
    val prgRom: U8Array,
    val chrRom: U8Array,
    val nametableArrangement: NametableArrangment,
)

enum class NametableArrangment {
    Vertical,
    Horizontal,
    Custom
}