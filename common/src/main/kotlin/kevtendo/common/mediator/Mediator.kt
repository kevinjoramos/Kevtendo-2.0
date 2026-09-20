package kevtendo.common.mediator

import kevtendo.common.binary.U16
import kevtendo.common.binary.U8

interface Bus {
    fun read(address: U16): U8
    fun write(address: U16, data: U8)

    fun read(address: U8): U8 = read(address.toU16())
    fun write(address: U8, data: U8) = write(address.toU16(), data)
}
