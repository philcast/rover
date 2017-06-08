package mars.rover

class Rover(val rows: Int, val cols: Int) {
    private var orientation = Orientation.North
    private var x: Int = 0
    private var y: Int = 0

    fun move(input: String): String {
        reset()

        input
            .map { Command.valueOf(it.toString()) }
            .forEach { handleCommand(it) }

        return "$x $y ${orientation.code}"
    }

    private fun reset() {
        orientation = Orientation.North
        x = cols / 2
        y = rows / 2
    }

    private fun handleCommand(command: Command) {
        when (command) {
            Command.M -> moveForward()
            Command.L -> turnLeft()
            Command.R -> turnRight()
        }
    }

    private fun turnRight() {
        orientation = when (orientation) {
            Orientation.North -> Orientation.East
            Orientation.East -> Orientation.South
            Orientation.South -> Orientation.West
            Orientation.West -> Orientation.North
        }
    }

    private fun turnLeft() {
        orientation = when (orientation) {
            Orientation.North -> Orientation.West
            Orientation.West -> Orientation.South
            Orientation.South -> Orientation.East
            Orientation.East -> Orientation.North
        }
    }

    private fun moveForward() {
        when (orientation) {
            Orientation.North -> y += 1
            Orientation.East -> x += 1
            Orientation.South -> y -= 1
            Orientation.West -> x -= 1
        }
        fixBounds()
    }

    private fun fixBounds() {
        y = (y + rows) % rows
        x = (x + cols) % cols
    }
}

fun main(args: Array<String>) {
    val rover = Rover(5, 5)
    print(rover.move("MMLMMR"))
}