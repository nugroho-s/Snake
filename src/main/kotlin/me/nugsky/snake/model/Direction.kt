package me.nugsky.snake.model

import org.lwjgl.glfw.GLFW

enum class Direction (val keyBinding: Int) {
    LEFT(GLFW.GLFW_KEY_A), RIGHT(GLFW.GLFW_KEY_D), UP(GLFW.GLFW_KEY_W), DOWN(GLFW.GLFW_KEY_S);

    companion object {
        fun getByKeyBind(keyBinding: Int) : Direction? {
            return when (keyBinding) {
                GLFW.GLFW_KEY_A -> LEFT
                GLFW.GLFW_KEY_D -> RIGHT
                GLFW.GLFW_KEY_W -> UP
                GLFW.GLFW_KEY_S -> DOWN
                else -> null
            }
        }
    }
}