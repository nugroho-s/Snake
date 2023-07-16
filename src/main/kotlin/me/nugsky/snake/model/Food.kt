package me.nugsky.snake.model

class Food(
    bottomLeft: Point,
    upperRight: Point
) : Square(bottomLeft, upperRight) {
    companion object {
        private var shouldDraw = true
    }

    override fun draw() {
        if (System.currentTimeMillis() % 500 > 250) {
            super.draw()
        }
    }
}