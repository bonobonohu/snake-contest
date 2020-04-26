package co.electric.snake.framework.autoconfiguration

import co.electric.snake.framework.model.ModifiableArena
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SnakeFrameworkAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    fun modifiableArena(): ModifiableArena {
        return ModifiableArena()
    }

}
