package me.nugsky.snake.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.lwjgl.glfw.GLFW
import java.util.concurrent.ConcurrentLinkedQueue

class Snake(
    private val window: Long,
    private val segments: ConcurrentLinkedQueue<Square> = ConcurrentLinkedQueue<Square>()
) {
    private var direction = Direction.LEFT
    private val speed = 0.05f
    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(200)
                val head = segments.last()
                if (head.bottomLeft.x <= -1f || head.upperRight.x >= 1 ||
                    head.bottomLeft.y <= -1 || head.upperRight.y >= 1)  {
                    break
                }

                if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_UP) == GLFW.GLFW_PRESS)
                    direction = Direction.UP
                if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_DOWN) == GLFW.GLFW_PRESS)
                    direction = Direction.DOWN
                if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS)
                    direction = Direction.RIGHT
                if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT) == GLFW.GLFW_PRESS)
                    direction = Direction.LEFT


                var newHead : Square
                when (direction) {
                    Direction.LEFT -> {
                        newHead = Square(
                            Point(head.bottomLeft.x - speed, head.bottomLeft.y),
                            Point(head.upperRight.x - speed, head.upperRight.y)
                        )
                    }
                    Direction.RIGHT -> {
                        newHead = Square(
                            Point(head.bottomLeft.x + speed, head.bottomLeft.y),
                            Point(head.upperRight.x + speed, head.upperRight.y)
                        )
                    }
                    Direction.UP -> {
                        newHead = Square(
                            Point(head.bottomLeft.x, head.bottomLeft.y + speed),
                            Point(head.upperRight.x, head.upperRight.y + speed)
                        )
                    }

                    else -> {
                        newHead = Square(
                            Point(head.bottomLeft.x, head.bottomLeft.y - speed),
                            Point(head.upperRight.x, head.upperRight.y - speed)
                        )
                    }
                }
                segments.add(newHead)
                segments.remove()
            }
        }
    }

    fun draw() {
        segments.forEach {
            it.draw()
        }
    }
}