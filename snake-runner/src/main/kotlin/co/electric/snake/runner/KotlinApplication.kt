package co.electric.snake.runner

import co.electric.snake.framework.controller.SnakeController
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class KotlinApplication(private val snakeController: SnakeController) : CommandLineRunner {

    override fun run(vararg args: String?) {
        snakeController.start()
    }

}

fun main(args: Array<String>) {
    SpringApplicationBuilder(KotlinApplication::class.java)
            .headless(false)
            .run(*args)
}
