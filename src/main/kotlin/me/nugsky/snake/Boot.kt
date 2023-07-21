package me.nugsky.snake

import me.nugsky.snake.model.*
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import java.util.concurrent.ConcurrentLinkedQueue


class Boot {
    private var window: Long = 0
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            Boot().run()
        }
    }

    fun run() {
        init()
        loop()

        Callbacks.glfwFreeCallbacks(window)
        GLFW.glfwDestroyWindow(window)

        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)?.free()
    }

    private fun init() {
        GLFWErrorCallback.createPrint(System.err).set()

        check(GLFW.glfwInit()) { "Unable to initialize GLFW" }

        GLFW.glfwDefaultWindowHints()
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE)

        window = GLFW.glfwCreateWindow(640, 480, "LWJGL Bootcamp", NULL, NULL)
        check(window !== NULL) { "Unable to create GLFW Window" }

        GLFW.glfwSetKeyCallback(window) { window: Long, key: Int, scancode: Int, action: Int, mods: Int -> }

        stackPush().use { stack ->
            val pWidth = stack.mallocInt(1)
            val pHeight = stack.mallocInt(1)
            GLFW.glfwGetWindowSize(window, pWidth, pHeight)
            val vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor())
            GLFW.glfwSetWindowPos(window, (vidmode!!.width() - pWidth[0]) / 2, (vidmode.height() - pHeight[0]) / 2)
            GLFW.glfwMakeContextCurrent(window)
            GLFW.glfwSwapInterval(1)
            GLFW.glfwShowWindow(window)
        }
    }

    private fun loop() {
        GL.createCapabilities()

        var food = Food()
        val snakeSegments = ConcurrentLinkedQueue<Square>()
        var bottomLeftX = 0.20f
        while (bottomLeftX > 0) {
            snakeSegments.add(Square(Point(bottomLeftX,0.1f), Point(bottomLeftX + 0.05f,0.15f)))
            bottomLeftX -= 0.05f
        }
        val snake = Snake(snakeSegments)
        GLFW.glfwSetKeyCallback(window) { window, key, scancode, action, mod ->
            run {
                if (action == GLFW.GLFW_PRESS) {
                    snake.changeDirection(Direction.getByKeyBind(key))
                }
            }
        }

        while (!GLFW.glfwWindowShouldClose(window)) {

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)

            snake.draw()
            food.draw()

            if (snake.getHead().isOverlapping(food)) {
                food = Food()
                snake.grow()
            }

            calculateFps()

            GLFW.glfwSwapBuffers(window)
            GLFW.glfwPollEvents()
        }
    }
}