package co.electric.snake.framework.strategy

import co.electric.snake.framework.model.Arena
import co.electric.snake.framework.model.Direction
import co.electric.snake.framework.model.Snake

interface SnakeStrategy {

    fun nextMove(snake: Snake, arena: Arena): Direction

}
