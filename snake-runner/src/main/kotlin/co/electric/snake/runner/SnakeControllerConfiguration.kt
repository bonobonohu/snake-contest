package co.electric.snake.runner

import co.electric.snake.framework.controller.SnakeController
import co.electric.snake.framework.model.ModifiableArena
import co.electric.snake.framework.model.ModifiableSnake
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(SnakeConfigurationProperties::class)
class SnakeControllerConfiguration(private val snakeConfigurationProperties: SnakeConfigurationProperties) {

    @Bean
    fun snakeController(modifiableArena: ModifiableArena, modifiableSnakes: Set<ModifiableSnake>): SnakeController {
        modifiableSnakes.forEach { modifiableSnake ->
            modifiableArena.addSnake(modifiableSnake)
        }
        return SnakeController(modifiableArena, snakeConfigurationProperties.maxRound)
    }

}
