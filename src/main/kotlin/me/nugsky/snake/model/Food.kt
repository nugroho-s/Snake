package me.nugsky.snake.model

import java.util.concurrent.ThreadLocalRandom

class Food(
    bottomLeft: Point = Point(
        ThreadLocalRandom.current().nextFloat(-1.0f, 1.0f),
        ThreadLocalRandom.current().nextFloat(-1.0f, 1.0f)
    )
) : Square(bottomLeft, Point(bottomLeft.x + 0.05f, bottomLeft.y + 0.05f)) {
    override fun draw() {
        if (System.currentTimeMillis() % 500 > 250) {
            super.draw()
        }
    }
}