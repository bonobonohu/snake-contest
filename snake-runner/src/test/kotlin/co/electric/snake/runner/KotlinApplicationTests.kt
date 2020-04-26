package co.electric.snake.runner

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(loader = (HeadlessSpringBootContextLoader::class))
class KotlinApplicationTests {

    @Test
    fun contextLoads() {
    }

}
