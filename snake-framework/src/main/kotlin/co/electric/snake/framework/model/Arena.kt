package co.electric.snake.framework.model

import java.util.*

open class Arena {

    companion object {

        private val MAX_COORDINATE = Coordinate(50, 50)

    }

    val maxCoordinate = MAX_COORDINATE

    protected val snakes: MutableList<ModifiableSnake> = ArrayList()

    private val food: MutableList<Food> = ArrayList()

    init {
        generateNewFood()
    }

    fun nextCoordinate(coordinate: Coordinate, direction: Direction): Coordinate {
        return coordinate.nextCoordinate(direction).truncLimits(maxCoordinate)
    }

    fun generateRandomFreeCoordinate(): Coordinate {
        var coordinate: Coordinate
        val random = Random()
        do {
            val x = random.nextInt(MAX_COORDINATE.x)
            val y = random.nextInt(MAX_COORDINATE.y)
            coordinate = Coordinate(x, y)
        } while (isOccupied(coordinate) && !isFood(coordinate))
        return coordinate
    }

    protected fun generateNewFood() {
        val coordinate = generateRandomFreeCoordinate()
        food.add(Food(coordinate))
    }

    protected fun removeFoodFromCollection(coordinate: Coordinate) {
        val foodIterator = food.iterator()
        while (foodIterator.hasNext()) {
            val food = foodIterator.next()
            val foodCoordinate = food.coordinate
            if (foodCoordinate == coordinate) {
                foodIterator.remove()
            }
        }
    }

    fun isOccupied(coordinate: Coordinate): Boolean {
        return occupies(coordinate, snakes)
    }

    fun isFood(coordinate: Coordinate): Boolean {
        return occupies(coordinate, food)
    }

    fun getSnakesInNewList(): List<Snake> {
        return ArrayList(snakes)
    }

    fun getFoodInNewList(): List<Food> {
        return ArrayList(food)
    }

    private fun occupies(coordinate: Coordinate, members: List<Member>): Boolean {
        var isOccupied = false
        var i = 0
        while (i < members.size && !isOccupied) {
            if (members[i].occupies(coordinate)) {
                isOccupied = true
            }
            i++
        }
        return isOccupied
    }

    override fun toString(): String {
        return "Arena [snakes=$snakes, food=$food, maxCoordinate=$maxCoordinate]"
    }

}
