package co.electric.snake.framework.model

class ModifiableArena : Arena() {

    fun addSnake(modifiableSnake: ModifiableSnake) {
        snakes.add(modifiableSnake)
    }

    fun move() {
        snakes.forEach(ModifiableSnake::move)
    }

    fun removeFood(coordinate: Coordinate) {
        removeFoodFromCollection(coordinate)
        generateNewFood()
    }

}
