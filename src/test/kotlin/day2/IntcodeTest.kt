package day2

import Intcode
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object IntcodeTest : Spek({

    val intcodeExampleBig by memoized {
        Intcode(
            listOf(
                1, 9, 10, 3,
                2, 3, 11, 0,
                99, 30, 40, 50
            )
        )
    }

    val intcodeExample1 by memoized {
        Intcode(listOf(1, 0, 0, 0, 99))
    }

    val intcodeExample2 by memoized {
        Intcode(listOf(2, 3, 0, 3, 99))
    }

    val intcodeExample3 by memoized {
        Intcode(listOf(2, 4, 4, 5, 99, 0))
    }

    val intcodeExample4 by memoized {
        Intcode(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99))
    }

    describe("new") {

        it("creates an Intcode with a program containing all the input codes") {
            assertEquals(12, intcodeExampleBig.codes.size)
            assertEquals(5, intcodeExample1.codes.size)
            assertEquals(5, intcodeExample2.codes.size)
            assertEquals(6, intcodeExample3.codes.size)
            assertEquals(9, intcodeExample4.codes.size)
        }
    }

    describe("varargs constructor") {
        it("works the same as passing a list") {
            assertEquals(
                Intcode(listOf(1, 0, 0, 0, 99)).codes,
                Intcode(1, 0, 0, 0, 99).codes
            )
        }
    }

    describe("execute") {

        it("runs the program correctly") {
            intcodeExample1.execute()
            assertEquals(listOf(2, 0, 0, 0, 99), intcodeExample1.codes)
        }

        it("runs the program correctly") {
            intcodeExample2.execute()
            assertEquals(listOf(2, 3, 0, 6, 99), intcodeExample2.codes)
        }

        it("runs the program correctly") {
            intcodeExample3.execute()
            assertEquals(listOf(2, 4, 4, 5, 99, 9801), intcodeExample3.codes)
        }

        it("runs the program correctly") {
            intcodeExample4.execute()
            assertEquals(listOf(30, 1, 1, 4, 2, 5, 6, 0, 99), intcodeExample4.codes)
        }

        it("runs the program correctly") {
            intcodeExampleBig.execute()
            assertEquals(listOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), intcodeExampleBig.codes)
        }
    }
})
