package kevtendo.common.mediator

interface Bus {
    fun read(address: Int): Int
    fun write(address: Int, data: Int)
}
