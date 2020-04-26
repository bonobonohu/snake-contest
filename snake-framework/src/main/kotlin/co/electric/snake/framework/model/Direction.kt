package co.electric.snake.framework.model

enum class Direction(val diffX: Int, val diffY: Int) {

    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

    companion object {

        private fun check(diff: Int, directionDiff: Int, maxCoordinate: Int): Boolean {
            return diff == directionDiff
                    || diff - maxCoordinate == directionDiff
                    || maxCoordinate + diff == directionDiff
        }

        private fun checkY(maxCoordinate: Coordinate, yDiff: Int, direction: Direction): Boolean {
            return check(yDiff, direction.diffY, maxCoordinate.y)
        }

        private fun checkX(maxCoordinate: Coordinate, xDiff: Int, direction: Direction): Boolean {
            return check(xDiff, direction.diffX, maxCoordinate.x)
        }

        fun getDirection(start: Coordinate, end: Coordinate, maxCoordinate: Coordinate): Direction {
            var result: Direction? = null
            var i = 0
            while (i < values().size && result == null) {
                val direction = values()[i]
                if (checkX(maxCoordinate, end.x - start.x, direction)
                        && checkY(maxCoordinate, end.y - start.y, direction)) {
                    result = direction
                }
                i++
            }
            return requireNotNull(result) { "Not a neighbouring cell." }
        }

    }

}
