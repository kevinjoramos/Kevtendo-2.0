package kevtendo.cartridge

import kevtendo.common.binary.U16
import kevtendo.common.binary.U8
import java.io.File

class Cartridge {

    private val file: File? = null

    fun read(address: U16): U8 {
        TODO()
    }

    fun write(address: U16, data: U8) {

    }

    fun load(file: File) {

    }
}