package co.electric.snake.framework.model

import org.slf4j.LoggerFactory

class ModifiableArena(private val stopWhenASnakeDies: Boolean) : Arena() {

    companion object {

        private val LOG = LoggerFactory.getLogger(ModifiableArena::class.java)

        private const val ALL_SNAKES_ARE_DEAD_LOG_MESSAGE = "All Snakes are dead!"

    }

    fun addSnake(modifiableSnake: ModifiableSnake) {
        snakes.add(modifiableSnake)
    }

    fun move() {
        snakes.forEach {
            it.move(stopWhenASnakeDies)
        }
        if (snakes.stream().allMatch(ModifiableSnake::isDead)) {
            LOG.info(ALL_SNAKES_ARE_DEAD_LOG_MESSAGE)
            logSnakeLengths()
            throw AllSnakesAreDeadException()
        }
    }

    fun removeFood(coordinate: Coordinate) {
        removeFoodFromCollection(coordinate)
        generateNewFood()
    }

    fun logSnakeLengths() {
        getSnakesInNewList().forEach {
            LOG.info("${it.name}: ${it.getLength()}")
        }
    }

}
