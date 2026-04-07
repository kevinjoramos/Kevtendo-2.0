package kevtendo.common.binary

fun Boolean.toU8() = if (this) 1u.toU8() else 0u.toU8()
fun Boolean.toU16() = if (this) 1u.toU16() else 0u.toU16()

// Utility
fun U8.isZero(): Boolean = this == 0u.toU8()
fun U8.combineHigh(high: U8): U16 = (this or high.shl(8)).toU16()

fun U16.isZero(): Boolean = this == 0u.toU16()
fun U16.toHighByte(): U8 = this.and(0xFF00u.toU16()).shr(8).toU8()
fun U16.toLowByte(): U8 = this.toU8()


// Bit masking
fun U8.getBit(bit: Int): Boolean {
    require(bit in 0..7) { "Bit index out of range: $bit" }
    return this.and(1u.shl(bit).toU8()).isZero().not()
}

fun U8.withBit(bit: Int, to: Boolean): U8 {
    require(bit in 0..7) { "Bit index out of range: $bit" }
    return if (to) {
        this or 1u.shl(bit).toU8()
    } else {
        this and 1u.shl(bit).inv().toU8()
    }
}

fun U16.getBit(bit: Int): Boolean {
    require(bit in 0..15) { "Bit index out of range: $bit" }
    //return (this.data and 1u.shl(bit).toUShort()) > 0u
    return (this and 1u.shl(bit).toU16()).isZero().not()
}

fun U16.withBit(bit: Int, to: Boolean): U16 {
    require(bit in 0..15) { "Bit index out of range: $bit" }
    return if (to) {
        //(this.data or 1u.shl(bit).toUShort()).toU16()
        this or 1u.shl(bit).toU16()
    } else {
        this and 1u.shl(bit).inv().toU16()
    }
}