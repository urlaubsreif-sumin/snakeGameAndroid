package com.example.snakegame2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.snakegame2.model.Game

class GameSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback, Runnable{

    private lateinit var thread: Thread
    private val game = Game
    private var canvas: Canvas? = null


    init {
        holder.addCallback(this)
    }



    // ------------------------------------------------------------
    // SurfaceHolder.Callback Override methods
    //

    override fun surfaceCreated(holder: SurfaceHolder) {
        // surfaceView가 활성화되면 game을 시작한다.
        game.start()
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        game.stop()
        joinThread()
    }



    // ------------------------------------------------------------
    // Runnable method
    //

    override fun run() {
        while(Game.playState) {
            // step에 맞게 snake와 food를 움직인다.
            game.step()

            // 업데이트 된 snake와 food를 surfaceView에 그린다.
            updateSurfaceView()

            Thread.sleep(500)
        }

        updateSurfaceViewAfterGameOver()
    }



    // ------------------------------------------------------------
    // private methods
    //

    /**
     * step마다 호출되어 surfaceView에 snake와 food, score를 시점에 맞게 업데이트한다.
     */
    private fun updateSurfaceView() {
        canvas = null

        try{
            // holder로부터 canvas를 받아와서
            canvas = holder.lockCanvas(null)
            synchronized(holder) {
                // snake, food를 그린 후
                drawCurrentStep()

                // 점수를 업데이트한다.
                updateScore()
            }
        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            if(canvas != null) {
                holder.unlockCanvasAndPost(canvas)
            }
        }
    }


    /**
     * 게임이 종료된 후, canvas를 지우고 gameOver 글자를 출력한다.
     */
    private fun updateSurfaceViewAfterGameOver() {
        canvas = null

        try{
            canvas = holder.lockCanvas(null)
            synchronized(holder) {
                // canvas를 지우고
                clearCanvas()

                // game over를 출력한다.
                printGameOver()
            }
        } catch(e: Exception) {
            e.printStackTrace()
        } finally {
            if(canvas != null) {
                holder.unlockCanvasAndPost(canvas)
            }
        }
    }



    /**
     * step에 맞게 canvas에 snake와 food를 그린다.
     */
    private fun drawCurrentStep() {
        clearCanvas()
        drawSnake()
        drawFood()
    }

    /**
     * canvas를 업데이트하기 전에 이전 그림을 지운다.
     */
    private fun clearCanvas() {
        canvas!!.drawColor(Color.BLACK)
    }

    /**
     * game으로부터 snake의 위치를 받아와 canvas에 그린다.
     */
    private fun drawSnake() {
        val snakePositions = game.snake.positions
        val snakeDrawable = SnakeDrawable(snakePositions)
        snakeDrawable.draw(canvas!!)
    }

    /**
     * game으로부터 food의 위치를 받아와 canvas에 그린다.
     */
    private fun drawFood() {
        val foodPosition = game.food.position
        val foodDrawable = FoodDrawable(foodPosition)
        foodDrawable.draw(canvas!!)
    }

    /**
     * canvas에 현재 점수를 업데이트한다.
     */
    private fun updateScore() {
        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 50f
        canvas?.drawText("${game.score}", 50f, 50f, paint)
    }

    /**
     * canvas에 game over를 출력한다.
     */
    private fun printGameOver() {
        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 50f
        canvas?.drawText("game over", canvas!!.width / 2f - 100f, canvas!!.height / 2f - 50f, paint)
    }

    /**
     * surface가 destroyed 된 후, 진행 중이던 thread를 main thread와 합치면서 멈춘다.
     */
    private fun joinThread() {
        var retry = true
        while(retry) {
            thread.join()
            retry = false
        }
    }

}