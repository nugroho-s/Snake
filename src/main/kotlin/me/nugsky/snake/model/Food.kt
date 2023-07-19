package me.nugsky.snake.model

import java.util.concurrent.ThreadLocalRandom

class Food(
    bottomLeft: Point = Point(
        ThreadLocalRandom.current().nextFloat(-0.95f, .95f),
        ThreadLocalRandom.current().nextFloat(-0.95f, .95f)
    )
) : Square(bottomLeft, Point(bottomLeft.x + 0.05f, bottomLeft.y + 0.05f)) {
    override fun draw() {
        if (System.currentTimeMillis() % 500 > 250) {
            super.draw()
        }
    }
}