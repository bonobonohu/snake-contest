package co.electric.snake.framework.model

import co.electric.snake.framework.strategy.SnakeStrategy
import java.util.*

open class Snake(modifiableArena: ModifiableArena, private val snakeStrategy: SnakeStrategy, val name: String) : Member {

    protected val bodyItems: Deque<Coordinate> = LinkedList()

    init {
        bodyItems.add(modifiableArena.generateRandomFreeCoordinate())
    }

    override fun occupies(coordinate: Coordinate): Boolean {
        return bodyItems.contains(coordinate)
    }

    fun getBodyItemsInNewList(): List<Coordinate> {
        return ArrayList(bodyItems)
    }

    fun getHeadCoordinate(): Coordinate {
        return bodyItems.first
    }

    fun getTailCoordinate(): Coordinate {
        return bodyItems.last
    }

    fun getLength(): Int {
        return bodyItems.size
    }

    override fun toString(): String {
        return "Snake [bodyItems=$bodyItems, snakeStrategy=$snakeStrategy]"
    }

}
