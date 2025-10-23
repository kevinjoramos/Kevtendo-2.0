package org.example.util

@JvmInline
value class U8(internal val data: UByte) : Comparable<U8> {
    override operator fun compareTo(other: U8): Int = this.data.toInt().compareTo(other.data.toInt())
    operator fun compareTo(other: U16): Int = this.data.toInt().compareTo(other.data.toInt())

    operator fun plus(other: U8): U8 = this.data.plus(other.data).toU8()
    operator fun plus(other: U16): U16 = this.data.plus(other.data).toU16()

    operator fun minus(other: U8): U8 = this.data.minus(other.data).toU8()
    operator fun minus(other: U16): U16 = this.data.minus(other.data).toU16()

    operator fun inc(): U8 = data.inc().toU8()
    operator fun dec(): U8 = data.dec().toU8()

    infix fun and(other: U8): U8 = (this.data and other.data).toU8()
    infix fun and(other: U16): U8 = (this.data and other.data.toUByte()).toU8()

    infix fun or(other: U8): U8 = (this.data or other.data).toU8()
    infix fun or(other: U16): U8 = (this.data or other.data.toUByte()).toU8()

    infix fun xor(other: U8): U8 = (this.data xor other.data).toU8()
    infix fun xor(other: U16): U8 = (this.data xor other.data.toUByte()).toU8()

    infix fun shl(bitCount: Int): U8 = data.toUInt().shl(bitCount).toU8()
    infix fun shr(bitCount: Int): U8 = data.toUInt().shr(bitCount).toU8()

    fun inv(): U8 = this.data.inv().toU8()

    fun isZero(): Boolean = this.data == 0u.toUByte()

    fun getBit(bit: Int): Boolean {
        require(bit in 0..7) { "Bit index out of range: $bit" }
        return (this.data and 1u.shl(bit).toUByte()) > 0u
    }

    fun withClearedBit(bit: Int): U8 {
        require(bit in 0..7) { "Bit index out of range: $bit" }
        return (this.data and 1u.shl(bit).inv().toUByte()).toU8()
    }

    fun withSetBit(bit: Int): U8 {
        require(bit in 0..7) { "Bit index out of range: $bit" }
        return (this.data or 1u.shl(bit).toUByte()).toU8()
    }

    fun combineHigh(high: U8): U16 = (this or high.shl(8)).toU16()

    fun toU16(): U16 = this.data.toU16()

    fun toUInt(): UInt = this.data.toUInt()
}

fun UInt.toU8(): U8 = U8(this.toUByte())
fun UShort.toU8(): U8 = U8(this.toUByte())
fun UByte.toU8(): U8 = U8(this)