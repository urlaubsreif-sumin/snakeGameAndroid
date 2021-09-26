package com.example.snakegame2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.snakegame2.model.Direction
import com.example.snakegame2.model.Game
import com.example.snakegame2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // ------------------------------------------------------------
    // view binding
    //

    private lateinit var binding: ActivityMainBinding



    // ------------------------------------------------------------
    // life cycle
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        addGameSurfaceView()

    }



    // ------------------------------------------------------------
    // OnClickListener
    //

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.upButton.id -> Game.turnSnake(Direction.UP)
            binding.downButton.id -> Game.turnSnake(Direction.DOWN)
            binding.rightButton.id -> Game.turnSnake(Direction.RIGHT)
            binding.leftButton.id -> Game.turnSnake(Direction.LEFT)
        }
    }



    // ------------------------------------------------------------
    // private methods
    //

    /**
     * 상하좌우 화살표 버튼에 OnClickListener를 달아준다.
     */
    private fun addOnClickListener() {
        binding.upButton.setOnClickListener(this)
        binding.downButton.setOnClickListener(this)
        binding.leftButton.setOnClickListener(this)
        binding.rightButton.setOnClickListener(this)
    }

    /**
     * customSurfaceView를 만든다.
     */
    private fun addGameSurfaceView() {
        val surfaceViewLayoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, 0)
        surfaceViewLayoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        surfaceViewLayoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        surfaceViewLayoutParams.topToTop = R.id.container
        surfaceViewLayoutParams.bottomToTop = R.id.upButton
        binding.container.addView(GameSurfaceView(applicationContext), surfaceViewLayoutParams)
    }
}