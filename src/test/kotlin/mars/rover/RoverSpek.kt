package mars.rover

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object RoverSpek: Spek({
    given("a rover on a 3x3 tile") {
        val rover = Rover(3, 3)

        it("should create the tile") {
            rover.cols.should.equal(3)
            rover.rows.should.equal(3)
        }

        it("should position by default to 1 1 N") {
            rover.x.should.equal(1)
            rover.y.should.equal(1)
            rover.orientation.should.equal(Orientation.North)
        }
    }
    given("a rover on a 5x5 tile") {
        val rover = Rover(5, 5)

        it("should create the tile") {
            rover.cols.should.equal(5)
            rover.rows.should.equal(5)
        }

        it("should position by default to 2 2 N") {
            rover.x.should.equal(2)
            rover.y.should.equal(2)
            rover.orientation.should.equal(Orientation.North)
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
    }
})

