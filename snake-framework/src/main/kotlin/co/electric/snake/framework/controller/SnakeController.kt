package co.electric.snake.framework.controller

import co.electric.snake.framework.model.Arena
import co.electric.snake.framework.model.MaxRoundReachedException
import co.electric.snake.framework.model.ModifiableArena
import co.electric.snake.framework.view.ArenaView
import org.slf4j.LoggerFactory
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.Timer

class SnakeController(private val modifiableArena: ModifiableArena, private val maxRound: Int) {

    companion object {

        private val LOG = LoggerFactory.getLogger(SnakeController::class.java)

        private const val MAX_ROUND_REACHED_LOG_MESSAGE = "Max round reached: {}"

        private const val WIDTH = 550
        private const val HEIGHT = 570
        private const val TITLE = "Snake"
        private const val DELAY_BETWEEN_STEPS = 1

        private val FRAME_SIZE = Dimension(WIDTH, HEIGHT)

    }

    private val frame = JFrame(TITLE)
    private val timer = Timer(DELAY_BETWEEN_STEPS, TimerAction(this))

    private var round = 0

    private lateinit var arenaView: ArenaView

    init {
        initArenaView(modifiableArena)
        frame.isVisible = true
    }

    fun start() {
        timer.start()
    }

    fun stop() {
        timer.stop()
        arenaView.repaint()
    }

    fun step() {
        checkIfMaxRoundReached()
        modifiableArena.move()
        round++
        arenaView.repaint()
    }

    private fun initArenaView(arena: Arena) {
        arenaView = ArenaView(arena)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(arenaView)
        frame.size = FRAME_SIZE
        frame.setLocationRelativeTo(null)
    }

    private fun checkIfMaxRoundReached() {
        if (round == maxRound) {
            LOG.info(MAX_ROUND_REACHED_LOG_MESSAGE, maxRound)
            modifiableArena.getSnakesInNewList().forEach { snake ->
                LOG.info(snake.name + ": " + snake.getLength())
            }
            throw MaxRoundReachedException()
        }
    }

}
