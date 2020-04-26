package co.electric.snake.strategy.defaultstrategy

import co.electric.snake.framework.model.Arena
import co.electric.snake.framework.model.Direction
import co.electric.snake.framework.model.Snake
import co.electric.snake.framework.strategy.SnakeStrategy

class DefaultStrategy : SnakeStrategy {

    override fun nextMove(snake: Snake, arena: Arena): Direction {
        val startCoordinate = snake.getHeadCoordinate()
        val foodCoordinate = arena.getFoodInNewList()[0].coordinate
        var minDistance = Int.MAX_VALUE
        var bestDirection: Direction = Direction.WEST
        Direction.values().forEach { direction ->
            val nextCoordinate = arena.nextCoordinate(startCoordinate, direction)
            val actualDistance = nextCoordinate.minDistance(foodCoordinate, arena.maxCoordinate)
            if (minDistance > actualDistance && !arena.isOccupied(nextCoordinate)) {
                minDistance = actualDistance
                bestDirection = direction
            }
        }
        return bestDirection
    }

}
