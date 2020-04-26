package co.electric.snake.framework.model

import co.electric.snake.framework.strategy.SnakeStrategy
import org.slf4j.LoggerFactory

class ModifiableSnake(private val modifiableArena: ModifiableArena, private val snakeStrategy: SnakeStrategy, name: String) : Snake(modifiableArena, snakeStrategy, name) {

    companion object {
        private val LOG = LoggerFactory.getLogger(Snake::class.java)

        private const val SNAKE_DIED_LOG_MESSAGE = "Snake died: {}"
    }

    fun move() {
        val nextCoordinate = decideNextCoordinate()
        moveTo(nextCoordinate)
    }

    private fun decideNextCoordinate(): Coordinate {
        val direction = snakeStrategy.nextMove(this, modifiableArena)
        return modifiableArena.nextCoordinate(bodyItems.first, direction)
    }

    private fun moveTo(coordinate: Coordinate) {
        if (!modifiableArena.isFood(coordinate)) {
            bodyItems.removeLast()
        } else {
            modifiableArena.removeFood(coordinate)
        }
        if (modifiableArena.isOccupied(coordinate)) {
            LOG.info(SNAKE_DIED_LOG_MESSAGE, name)
            throw SnakeIsDeadException()
        }
        bodyItems.addFirst(coordinate)
    }

}
