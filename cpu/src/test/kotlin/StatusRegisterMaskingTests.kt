import kevtendo.common.binary.toU8
import kevtendo.cpu.Cpu6502
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StatusRegisterMaskingTests {

    @Test
    fun testNegativeGetter() {
        val cpu = Cpu6502()

        // Test when bit 7 is set (negative flag should be true)
        cpu.status = 0b1000_0000.toUByte().toU8()
        assertTrue(cpu.negativeFlag, "Negative flag should be true when bit 7 is 1")

        // Test when bit 7 is cleared (negative flag should be false)
        cpu.status = 0b0111_1111.toUByte().toU8()
        assertFalse(cpu.negativeFlag, "Negative flag should be false when bit 7 is 0")
    }

    @Test
    fun testNegativeSetter() {
        val cpu = Cpu6502()

        // Test turning bit 7 on (negative flag should be true)
        cpu.status = 0b0011_0011.toUByte().toU8()
        cpu.negativeFlag = true
        assertEquals(0b1011_0011.toUByte().toU8(), cpu.status)

        // Test clearing bit 7 (negative flag should be false)
        cpu.status = 0b1111_1011.toUByte().toU8()
        cpu.negativeFlag = false
        assertEquals(0b0111_1011.toUByte().toU8(), cpu.status)
    }

    @Test
    fun testOverflowGetter() {
        val cpu = Cpu6502()

        // Test when bit 6 is set (negative flag should be true)
        cpu.status = 0b0100_0000.toUByte().toU8()
        assertTrue(cpu.overflowFlag, "Negative flag should be set when bit 7 is 1")

        // Test when bit 6 is cleared (negative flag should be false)
        cpu.status = 0b1011_1111.toUByte().toU8()
        assertFalse(cpu.overflowFlag, "Negative flag should be cleared when bit 7 is 0")
    }

    @Test
    fun testOverflowSetter() {
        val cpu = Cpu6502()

        // Test turning bit 6 on (negative flag should be true)
        cpu.status = 0b0001_0011.toUByte().toU8()
        cpu.overflowFlag = true
        assertEquals(0b0101_0011.toUByte().toU8(), cpu.status)

        // Test clearing bit 6 (negative flag should be false)
        cpu.status = 0b0111_1011.toUByte().toU8()
        cpu.overflowFlag = false
        assertEquals(0b0011_1011.toUByte().toU8(), cpu.status)
    }

    @Test
    fun testDecimalGetter() {
        val cpu = Cpu6502()

        // Test when bit 3 is set (decimal flag should be true)
        cpu.status = 0b0000_1000.toUByte().toU8()
        assertTrue(cpu.decimalFlag, "Decimal flag should be true when bit 3 is 1")

        // Test when bit 3 is cleared (decimal flag should be false)
        cpu.status = 0b1111_0111.toUByte().toU8()
        assertFalse(cpu.decimalFlag, "Decimal flag should be false when bit 3 is 0")
    }

    @Test
    fun testDecimalSetter() {
        val cpu = Cpu6502()

        // Test turning bit 3 on (decimal flag should be true)
        cpu.status = 0b1111_0111.toUByte().toU8()
        cpu.decimalFlag = true
        assertEquals(0b1111_1111.toUByte().toU8(), cpu.status)

        // Test clearing bit 3 (decimal flag should be false)
        cpu.status = 0b1111_1111.toUByte().toU8()
        cpu.decimalFlag = false
        assertEquals(0b1111_0111.toUByte().toU8(), cpu.status)
    }

    @Test
    fun testInterruptDisableGetter() {
        val cpu = Cpu6502()

        // Test when bit 2 is set (interruptDisable flag should be true)
        cpu.status = 0b0000_0100.toUByte().toU8()
        assertTrue(cpu.interruptDisableFlag, "InterruptDisable flag should be true when bit 2 is 1")

        // Test when bit 2 is cleared (interruptDisable flag should be false)
        cpu.status = 0b1111_1011.toUByte().toU8()
        assertFalse(cpu.interruptDisableFlag, "InterruptDisable flag should be false when bit 2 is 0")
    }

    @Test
    fun testInterruptDisableSetter() {
        val cpu = Cpu6502()

        // Test turning bit 2 on (interruptDisable flag should be true)
        cpu.status = 0b1111_1011.toUByte().toU8()
        cpu.interruptDisableFlag = true
        assertEquals(0b1111_1111.toUByte().toU8(), cpu.status)

        // Test clearing bit 2 (interruptDisable flag should be false)
        cpu.status = 0b1111_1111.toUByte().toU8()
        cpu.interruptDisableFlag = false
        assertEquals(0b1111_1011.toUByte().toU8(), cpu.status)
    }

    @Test
    fun testZeroGetter() {
        val cpu = Cpu6502()

        // Test when bit 1 is set (zero flag should be true)
        cpu.status = 0b0000_0010.toUByte().toU8()
        assertTrue(cpu.zeroFlag, "Zero flag should be true when bit 1 is 1")

        // Test when bit 1 is cleared (zero flag should be false)
        cpu.status = 0b1111_1101.toUByte().toU8()
        assertFalse(cpu.zeroFlag, "Zero flag should be false when bit 1 is 0")
    }

    @Test
    fun testZeroSetter() {
        val cpu = Cpu6502()

        // Test turning bit 1 on (zero flag should be true)
        cpu.status = 0b1111_1101.toUByte().toU8()
        cpu.zeroFlag = true
        assertEquals(0b1111_1111.toUByte().toU8(), cpu.status)

        // Test clearing bit 1 (zero flag should be false)
        cpu.status = 0b1111_1111.toUByte().toU8()
        cpu.zeroFlag = false
        assertEquals(0b1111_1101.toUByte().toU8(), cpu.status)
    }

    @Test
    fun testCarryGetter() {
        val cpu = Cpu6502()

        // Test when bit 0 is set (carry flag should be true)
        cpu.status = 0b0000_0001.toUByte().toU8()
        assertTrue(cpu.carryFlag, "Carry flag should be true when bit 0 is 1")

        // Test when bit 0 is cleared (carry flag should be false)
        cpu.status = 0b1111_1110.toUByte().toU8()
        assertFalse(cpu.carryFlag, "Carry flag should be false when bit 0 is 0")
    }

    @Test
    fun testCarrySetter() {
        val cpu = Cpu6502()

        // Test turning bit 0 on (carry flag should be true)
        cpu.status = 0b1111_1100.toUByte().toU8()
        cpu.carryFlag = true
        assertEquals(0b1111_1101.toUByte().toU8(), cpu.status)

        // Test clearing bit 0 (carry flag should be false)
        cpu.status = 0b1111_1101.toUByte().toU8()
        cpu.carryFlag = false
        assertEquals(0b1111_1100.toUByte().toU8(), cpu.status)
    }

}