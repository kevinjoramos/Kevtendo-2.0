package kevtendo.app

import kevtendo.common.binary.U16
import kevtendo.common.binary.U8
import kevtendo.common.binary.U8Array

typealias Bus = U8Array
operator fun Bus.get(address: U8): U8 = this[address.toUInt().toInt()]
operator fun Bus.get(address: U16): U8 = this[address.toUInt().toInt()]

operator fun Bus.set(address: U16, value: U8) {
    this[address.toUInt().toInt()] = value
}