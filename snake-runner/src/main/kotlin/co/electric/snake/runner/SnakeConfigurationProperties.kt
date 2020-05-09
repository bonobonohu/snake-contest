package co.electric.snake.runner

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "snake")
class SnakeConfigurationProperties {

    var maxRound: Int = 10000
    var stopWhenReachedMaxRound: Boolean = true
    var stopWhenASnakeDies: Boolean = true

}
