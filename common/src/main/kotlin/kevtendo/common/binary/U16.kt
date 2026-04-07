package kevtendo.common.binary

@JvmInline
value class U16(internal val data: UShort) : Comparable<U16> {
    operator fun compareTo(other: U8): Int = this.data.toInt().compareTo(other.data.toInt())
    override operator fun compareTo(other: U16): Int = this.data.toInt().compareTo(other.data.toInt())

    operator fun plus(other: U8): U16 = this.data.plus(other.data).toU16()
    operator fun plus(other: U16): U16 = this.data.plus(other.data).toU16()

    operator fun minus(other: U8): U16 = this.data.minus(other.data).toU16()
    operator fun minus(other: U16): U16 = this.data.minus(other.data).toU16()

    operator fun inc(): U16 = data.inc().toU16()
    operator fun dec(): U16 = data.dec().toU16()

    infix fun and(other: U8): U16 = (this.data and other.data.toUShort()).toU16()
    infix fun and(other: U16): U16 = (this.data and other.data).toU16()

    infix fun or(other: U8): U16 = (this.data or other.data.toUShort()).toU16()
    infix fun or(other: U16): U16 = (this.data or other.data).toU16()

    infix fun xor(other: U8): U16 = (this.data xor other.data.toUShort()).toU16()
    infix fun xor(other: U16): U16 = (this.data xor other.data).toU16()

    infix fun shl(bitCount: Int): U16 = data.toUInt().shl(bitCount).toU16()
    infix fun shr(bitCount: Int): U16 = data.toUInt().shr(bitCount).toU16()

    fun inv(): U16 = this.data.inv().toU16()

    fun toU8(): U8 = this.data.toU8()

    fun toUInt(): UInt = this.data.toUInt()
}

fun UInt.toU16(): U16 = U16(this.toUShort())
fun UShort.toU16(): U16 = U16(this)
fun UByte.toU16(): U16 = U16(this.toUShort())