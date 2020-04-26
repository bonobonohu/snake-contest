package co.electric.snake.strategy.defaultstrategy.autoconfiguration

import co.electric.snake.framework.model.ModifiableArena
import co.electric.snake.framework.model.ModifiableSnake
import co.electric.snake.strategy.defaultstrategy.DefaultStrategy
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DefaultStrategyAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    fun defaultStrategy(): DefaultStrategy {
        return DefaultStrategy()
    }

    @ConditionalOnBean(name = ["modifiableArena"])
    @Bean
    fun stoneJack(modifiableArena: ModifiableArena, defaultStrategy: DefaultStrategy): ModifiableSnake {
        return ModifiableSnake(modifiableArena, defaultStrategy, "stoneJack")
    }

    @ConditionalOnBean(name = ["modifiableArena"])
    @Bean
    fun stoneJill(modifiableArena: ModifiableArena, defaultStrategy: DefaultStrategy): ModifiableSnake {
        return ModifiableSnake(modifiableArena, defaultStrategy, "stoneJill")
    }

}
