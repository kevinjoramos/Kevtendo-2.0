package kevtendo.common.binary

@JvmInline
value class U8Array
@PublishedApi
internal constructor(@PublishedApi internal val storage: UByteArray) : Collection<U8> {

    constructor(size: Int) : this(UByteArray(size))

    operator fun get(index: Int): U8 = storage[index].toU8()

    operator fun set(index: Int, value: U8) {
        storage[index] = value.toUInt().toUByte()
    }

    override val size: Int get() = storage.size

    override fun isEmpty(): Boolean = storage.isEmpty()

    override fun contains(element: U8): Boolean {
        return storage.contains(element.toUInt().toUByte())
    }

    override fun iterator(): kotlin.collections.Iterator<U8> = Iterator(storage)

    private class Iterator(private val array: UByteArray) : kotlin.collections.Iterator<U8> {
        private var index = 0
        override fun hasNext(): Boolean = index < array.size
        override fun next(): U8 = if (index < array.size) array[index++].toU8() else throw NoSuchElementException(index.toString())
    }

    override fun containsAll(elements: Collection<U8>): Boolean {
        return (elements as Collection<*>).all { it is U8 && storage.contains(it.toUInt().toUByte()) }
    }
}

inline fun U8Array(size: Int, init: (Int) -> U8) : U8Array {
    return U8Array(UByteArray(size) { index -> init(index).toUInt().toUByte() })
}