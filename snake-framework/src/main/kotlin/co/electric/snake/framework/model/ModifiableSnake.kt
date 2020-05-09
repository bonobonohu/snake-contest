package co.electric.snake.framework.model

import co.electric.snake.framework.strategy.SnakeStrategy
import org.slf4j.LoggerFactory

class ModifiableSnake(private val modifiableArena: ModifiableArena, private val snakeStrategy: SnakeStrategy, name: String) : Snake(modifiableArena, snakeStrategy, name) {

    private var isAlive: Boolean = true

    companion object {
        private val LOG = LoggerFactory.getLogger(ModifiableSnake::class.java)

        private const val SNAKE_DIED_LOG_MESSAGE = "Snake died: {}"
    }

    internal fun move(stopWhenASnakeDies: Boolean) {
        if (isAlive) {
            val nextCoordinate = decideNextCoordinate()
            moveTo(nextCoordinate, stopWhenASnakeDies)
        }
    }

    internal fun isDead(): Boolean {
        return !isAlive
    }

    private fun decideNextCoordinate(): Coordinate {
        val direction = snakeStrategy.nextMove(this, modifiableArena)
        return modifiableArena.nextCoordinate(bodyItems.first, direction)
    }

    private fun moveTo(coordinate: Coordinate, stopWhenASnakeDies: Boolean) {
        if (modifiableArena.isOccupied(coordinate)) {
            die(stopWhenASnakeDies)
        } else {
            live(coordinate)
        }
    }

    private fun live(coordinate: Coordinate) {
        if (modifiableArena.isFood(coordinate)) {
            modifiableArena.removeFood(coordinate)
        } else {
            bodyItems.removeLast()
        }
        bodyItems.addFirst(coordinate)
    }

    private fun die(stopWhenASnakeDies: Boolean) {
        LOG.info(SNAKE_DIED_LOG_MESSAGE, name)
        if (stopWhenASnakeDies) {
            modifiableArena.logSnakeLengths()
            throw SnakeIsDeadException()
        } else {
            isAlive = false
        }
    }

}
