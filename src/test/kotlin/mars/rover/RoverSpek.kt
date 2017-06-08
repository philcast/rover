package mars.rover

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object RoverSpek: Spek({
    given("a rover on a 3x3 tile") {
        val rover = Rover(3, 3)

        it("should position by default to 1 1 N") {
            val expected = rover.move("")
            expected.should.equal("1 1 N")
        }
    }

    given("a rover on a 4x4 tile") {
        val rover = Rover(4, 4)

        it("should position by default to 2 2 N") {
            val expected = rover.move("")
            expected.should.equal("2 2 N")
        }
    }

    given("a rover on a 5x5 tile") {
        val rover = Rover(5, 5)

        it("should position by default to 2 2 N") {
            val expected = rover.move("")
            expected.should.equal("2 2 N")
        }

        on("moving forward") {
            it("should position to 2 3 N") {
                val expected = rover.move("M")
                expected.should.equal("2 3 N")
            }
        }

        on("moving forward twice") {
            it("should position to 2 4 N") {
                val expected = rover.move("MM")
                expected.should.equal("2 4 N")
            }
        }

        on("turning left") {
            it("should position to 2 2 W") {
                val expected = rover.move("L")
                expected.should.equal("2 2 W")
            }
        }

        on("turning left twice") {
            it("should position to 2 2 S") {
                val expected = rover.move("LL")
                expected.should.equal("2 2 S")
            }
        }

        on("turning right") {
            it("should position to 2 2 E") {
                val expected = rover.move("R")
                expected.should.equal("2 2 E")
            }
        }

        on("turning right twice") {
            it("should position to 2 2 S") {
                val expected = rover.move("RR")
                expected.should.equal("2 2 S")
            }
        }

        on("moving forward twice, turning left, moving forward twice and turning right") {
            it("should position to 0 4 N") {
                val expected = rover.move("MMLMMR")
                expected.should.equal("0 4 N")
            }
        }

        on("moving beyond the top tile border") {
            it("should position on the bottom tile border") {
                val expected = rover.move("MMM")
                expected.should.equal("2 0 N")
            }
        }

        on("moving beyond the right tile border") {
            it("should position on the left tile border") {
                val expected = rover.move("RMMM")
                expected.should.equal("0 2 E")
            }
        }

        on("moving beyond the bottom tile border") {
            it("should position on the top tile border") {
                val expected = rover.move("RRMMM")
                expected.should.equal("2 4 S")
            }
        }

        on("moving beyond the left tile border") {
            it("should position on the right tile border") {
                val expected = rover.move("LMMM")
                expected.should.equal("4 2 W")
            }
        }
    }
})

