package org.example.util

fun U16.toHighByte(): U8 = this.and(0xFF00u.toU16()).shr(8).toU8()
fun U16.toLowByte(): U8 = this.toU8()

fun Boolean.toU8() = if (this) 1u.toU8() else 0u.toU8()
fun Boolean.toU16() = if (this) 1u.toU16() else 0u.toU16()