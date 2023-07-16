package me.nugsky.snake.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.lwjgl.glfw.GLFW

class Snake(
    private val window: Long,
    private var bottomLeft: Point,
    private var upperRight: Point
) : Square(bottomLeft, upperRight) {
    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if (bottomLeft.x <= -1f || upperRight.x >= 1 || bottomLeft.y <= -1 || upperRight.y >= 1)  {
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


                when (direction) {
                    Direction.LEFT -> {
                        bottomLeft.x = bottomLeft.x - 0.01f
                        upperRight.x = upperRight.x - 0.01f
                    }
                    Direction.RIGHT -> {
                        bottomLeft.x = bottomLeft.x + 0.01f
                        upperRight.x = upperRight.x + 0.01f
                    }
                    Direction.UP -> {
                        bottomLeft.y = bottomLeft.y + 0.01f
                        upperRight.y = upperRight.y + 0.01f
                    }

                    else -> {
                        bottomLeft.y = bottomLeft.y - 0.01f
                        upperRight.y = upperRight.y - 0.01f
                    }
                }
                delay(50)
            }
        }
    }
    private var direction = Direction.LEFT
}