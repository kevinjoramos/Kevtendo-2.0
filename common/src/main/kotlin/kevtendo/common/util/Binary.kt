package kevtendo.common.util

fun Int.maskTo8Bits(): Int = this and 0xFF
fun Int.maskTo16Bits(): Int = this and 0xFFFF

fun Int.getBit(bit: Int): Boolean {
    require(bit in 0..15) { "Bit index out of range: $bit" }
    return (this and (1 shl bit)) > 0
}

fun Int.withBit(bit: Int, isSet: Boolean): Int {
    require(bit in 0..15) { "Bit index out of range: $bit" }
    if (isSet) return this or (1 shl bit)
    return this and (1 shl bit).inv()
}

fun formWord(low: Int, high: Int): Int = low or (high shl 8)