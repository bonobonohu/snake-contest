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
    fun modifiableArena(): ModifiableArena {
        return ModifiableArena(snakeConfigurationProperties.stopWhenASnakeDies)
    }

    @Bean
    fun snakeController(modifiableArena: ModifiableArena, modifiableSnakes: Set<ModifiableSnake>): SnakeController {
        modifiableSnakes.forEach {
            modifiableArena.addSnake(it)
        }
        return SnakeController(modifiableArena, snakeConfigurationProperties.maxRound, snakeConfigurationProperties.stopWhenReachedMaxRound)
    }

}
