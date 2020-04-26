package co.electric.snake.framework.controller

import co.electric.snake.framework.model.SnakeException
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class TimerAction(private val snakeController: SnakeController) : ActionListener {

    private var stop = false

    override fun actionPerformed(event: ActionEvent) {
        try {
            if (!stop) {
                snakeController.step()
            }
        } catch (exception: SnakeException) {
            stop = true
            snakeController.stop()
        }
    }

}
