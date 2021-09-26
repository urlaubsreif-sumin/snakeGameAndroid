package com.example.snakegame2

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import com.example.snakegame2.model.Position

/**
 * Canvas에 음식을 그리기 위한 Drawable.
 * Position 형태로 Food의 위치를 인자로 입력받아 해당 위치에 주황색 사각형을 그린다.
 */

class FoodDrawable(private val foodPosition: Position): Drawable() {
    private val paint: Paint = Paint().apply { setARGB(255, 255, 150, 50) }

    override fun draw(canvas: Canvas) {
        val width = canvas.width / 15f
        val x: Float = foodPosition.x * width
        val y: Float = foodPosition.y * width
        canvas.drawRect(x, y, x + width, y + width, paint)
    }

    override fun setAlpha(alpha: Int) {

    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
}