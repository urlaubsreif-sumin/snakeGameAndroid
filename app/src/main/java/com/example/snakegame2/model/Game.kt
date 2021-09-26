package com.example.snakegame2.model

object Game {

    var playState = false // 현재 game의 진행 상태를 나타내며, surfaceView에서 thread 실행을 조절한다.
    var score = 0
    var snake: Snake = Snake
    var food: Food = Food


    // ------------------------------------------------------------
    // methods
    //

    /**
     * game이 처음 시작될 때 호출되며 각종 멤버 변수를 초기화 한다.
     */
    fun start() {
        snake.init()
        food.generateNewFood()
        score = 0
        playState = true
    }

    /**
     * thread에서 500ms마다 호출되어 현재 게임 상태를 업데이트한다.
     */
    fun step() {
        // snake가 움직일 수 있는 상태이면,
        if(snake.move()) {

            // 움직인 snake가 음식을 먹었는지 확인하고, 점수와 음식 위치를 업데이트한다.
            if(snake.checkFoodEat(food.position)) {
                score++
                food.generateNewFood()
            }

        } else {
            // snake가 움직일 수 없는 상태이면, 게임을 종료한다.
            stop()
        }
    }

    /**
     * 상하좌우 화살표 버튼에서 click 이벤트가 발생하면 호출되어 snake의 진행 방향을 변경한다.
     */
    fun turnSnake(direction: Direction) {
        Snake.turnDirection(direction)
    }

    /**
     * 게임을 멈춘다.
     */
    fun stop() {
        playState = false
    }
}