package com.example.snakegame2

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import com.example.snakegame2.model.Position


/**
 * Canvas에 snake를 그리기 위한 drawable.
 * Position 형태로 snake의 자취를 저장하고 있는 Deque를 인자로 입력받아 해당 위치에 초록색 사각형을 그린다.
 */

class SnakeDrawable(private val snakePosition: ArrayDeque<Position>): Drawable() {
    private val paint: Paint = Paint().apply { setARGB(255, 150, 150, 50) }

    override fun draw(canvas: Canvas) {
        val width = canvas.width / 15f
        for(pos in snakePosition) {
            val x = pos.x * width
            val y = pos.y * width
            canvas.drawRect(x, y, x + width, y + width, paint)
        }
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}