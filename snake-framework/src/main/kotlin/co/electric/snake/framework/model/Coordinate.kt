package co.electric.snake.framework.model

import kotlin.math.abs

class Coordinate(val x: Int, val y: Int) {

    fun nextCoordinate(direction: Direction): Coordinate {
        return Coordinate(x + direction.diffX, y + direction.diffY)
    }

    fun truncLimits(maxCoordinates: Coordinate): Coordinate {
        return Coordinate((x + maxCoordinates.x) % maxCoordinates.x, (y + maxCoordinates.y) % maxCoordinates.y)
    }

    fun standardDistance(otherCoordinate: Coordinate): Int {
        return abs(x - otherCoordinate.x) + abs(y - otherCoordinate.y)
    }

    fun minDistance(otherCoordinate: Coordinate, maxCoordinate: Coordinate): Int {
        val normalDistanceX = abs(x - otherCoordinate.x)
        val normalDistanceY = abs(y - otherCoordinate.y)
        return (normalDistanceX.coerceAtMost(maxCoordinate.x - normalDistanceX)
                + normalDistanceY.coerceAtMost(maxCoordinate.y - normalDistanceY))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null) {
            return false
        }
        if (javaClass != other.javaClass) {
            return false
        }
        val otherAsCoordinate = other as Coordinate
        if (x != otherAsCoordinate.x) {
            return false
        }
        return y == otherAsCoordinate.y
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + x
        result = prime * result + y
        return result
    }

    override fun toString(): String {
        return "Coordinate [x=$x, y=$y]"
    }

}
