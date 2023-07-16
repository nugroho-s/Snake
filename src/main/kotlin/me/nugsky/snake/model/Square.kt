package me.nugsky.snake.model

import me.nugsky.snake.createMesh
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30


open class Square(
    var bottomLeft: Point,
    val upperRight: Point
) {
    open fun draw() {
        val vertices = floatArrayOf(
            bottomLeft.x, bottomLeft.y,
            upperRight.x, bottomLeft.y,
            upperRight.x, upperRight.y,
            bottomLeft.x, upperRight.y,
        )
        val indices = intArrayOf(0, 1, 2, 2, 0, 3)
        val (vao, vertices1) = createMesh(vertices, indices) //Kudos if you got that reference

        GL30.glBindVertexArray(vao)
        GL20.glEnableVertexAttribArray(0)
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertices1, GL11.GL_UNSIGNED_INT,0)
        GL20.glDisableVertexAttribArray(0)
        GL30.glBindVertexArray(0)
    }

    fun isOverlapping(other: Square): Boolean {
        if (upperRight.y < other.bottomLeft.y
            || bottomLeft.y > other.upperRight.y) {
            return false
        }
        return !(upperRight.x < other.bottomLeft.x
                || bottomLeft.x > other.bottomLeft.x)
    }
}