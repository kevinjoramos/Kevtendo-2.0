package kevtendo.cpu.operations

import kevtendo.cpu.Cpu6502
import kevtendo.common.util.formWord
import kevtendo.common.util.maskTo16Bits
import kevtendo.common.util.maskTo8Bits


/**
 * Protocals for determining the operand
 *
 * TODO: (absolute x & t absoluted indexed, y-indirect, relative, and ) CPU cycles are variable depending on specific conditions.
 */
    fun Cpu6502.implied() { programCounter++ }

    fun Cpu6502.accumulator() { programCounter++ }

    fun Cpu6502.immediate(): Int = programCounter++

    fun Cpu6502.absolute(): Int {
        val low = memory.read(programCounter++)
        val high = memory.read(programCounter++)
        return formWord(low, high)
    }

    // TODO page cross penalty
    fun Cpu6502.absoluteX(): Int {
        val low = memory.read(programCounter++)
        val high = memory.read(programCounter++)
        return formWord(low, high).plus(x).maskTo16Bits()
    }

    // TODO page cross penalty
    fun Cpu6502.absoluteY(): Int {
        val low = memory.read(programCounter++)
        val high = memory.read(programCounter++)
        return formWord(low, high).plus(y).maskTo16Bits()
    }

    fun Cpu6502.zeroPage(): Int = memory.read(programCounter++)

    fun Cpu6502.zeroPageX(): Int = memory.read(programCounter++).plus(x).maskTo8Bits()

    fun Cpu6502.zeroPageY(): Int = memory.read(programCounter++).plus(y).maskTo8Bits()

    // TODO famous hardware bug
    fun Cpu6502.indirect(): Int {
        val lookUpLow = memory.read(programCounter++)
        val lookUpHigh = memory.read(programCounter++)
        val lookUpAddress = formWord(lookUpLow, lookUpHigh)
        val dataLow = memory.read(lookUpAddress)
        val dataHigh = memory.read(lookUpAddress.inc().maskTo16Bits())
        return formWord(dataLow, dataHigh)
    }


    fun Cpu6502.indirectX(): Int {
        val lookUpAddress = memory.read(programCounter++.plus(x).maskTo8Bits())
        return formWord(
            memory.read(lookUpAddress),
            memory.read(lookUpAddress.inc().maskTo8Bits()),
        )
    }

    fun Cpu6502.indirectY(): Int {
        val lookUpAddress = memory.read(programCounter++)
        val low = memory.read(lookUpAddress)
        val high = memory.read(lookUpAddress.inc().maskTo8Bits())
        return formWord(low, high).plus(y).maskTo16Bits()
    }

    fun Cpu6502.relative(): Int {
        val offset = memory.read(programCounter++).toByte().toInt()
        return programCounter.plus(offset).maskTo16Bits()
    }