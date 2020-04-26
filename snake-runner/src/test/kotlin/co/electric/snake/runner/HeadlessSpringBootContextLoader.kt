package co.electric.snake.runner

import org.springframework.boot.SpringApplication
import org.springframework.boot.test.context.SpringBootContextLoader


class HeadlessSpringBootContextLoader : SpringBootContextLoader() {

    override fun getSpringApplication(): SpringApplication {
        val application = super.getSpringApplication()
        application.setHeadless(false)
        return application
    }

}
