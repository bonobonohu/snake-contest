package co.electric.snake.framework.view

import co.electric.snake.framework.model.Arena
import co.electric.snake.framework.model.Food
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.util.*
import javax.swing.JPanel

class ArenaView(private val arena: Arena) : JPanel() {

    companion object {

        const val PADDING = 10
        const val POINT_SIZE = 10

        private const val serialVersionUID = 1L

        private const val FONT = "SansSerif"
        private const val FONT_SIZE = 20
        private const val SNAKE_NAME_X_COORDINATE = 30
        private const val SNAKE_NAME_Y_COORDINATE = 30
        private const val SNAKE_NAME_Y_OFFSET = 20
        private val AVAILABLE_COLORS = arrayOf(Color.BLUE, Color.MAGENTA, Color.YELLOW)

    }

    private val snakeViews: MutableList<SnakeView> = ArrayList()

    init {
        generateSnakeViews()
    }

    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        graphics.font = Font(FONT, Font.PLAIN, FONT_SIZE)
        graphics.color = Color.BLACK
        val maxCoordinate = arena.maxCoordinate
        graphics.drawRect(PADDING, PADDING, POINT_SIZE * (maxCoordinate.x + 1), POINT_SIZE * (maxCoordinate.y + 1))
        for ((i, snake) in snakeViews.withIndex()) {
            snake.draw(graphics)
            graphics.drawString(snake.snake.name + ": " + snake.snake.getBodyItemsInNewList().size, SNAKE_NAME_X_COORDINATE, SNAKE_NAME_Y_COORDINATE + SNAKE_NAME_Y_OFFSET * i)
        }
        arena.getFoodInNewList().forEach { food ->
            drawFood(food, graphics)
        }
        repaint()
    }

    private fun generateSnakeViews() {
        var colorIndex = 0
        arena.getSnakesInNewList().forEach { snake ->
            snakeViews.add(SnakeView(snake, AVAILABLE_COLORS[colorIndex]))
            colorIndex = (colorIndex + 1) % AVAILABLE_COLORS.size
        }
    }

    private fun drawFood(food: Food, graphics: Graphics) {
        graphics.color = Color.RED
        val coordinate = food.coordinate
        val x = PADDING + 1 + POINT_SIZE * coordinate.x
        val y = PADDING + 1 + POINT_SIZE * coordinate.y
        graphics.fillRect(x, y, POINT_SIZE, POINT_SIZE)
    }

}
