package com.example.snakegame2.model

import java.security.SecureRandom


object Snake {

    // ------------------------------------------------------------
    // values
    //

    private val dx = listOf(0, 0, -1, 1)
    private val dy = listOf(-1, 1, 0, 0)



    // ------------------------------------------------------------
    // variables
    //

    var positions = ArrayDeque<Position>() // snake의 자취
    private var direction = Direction.INIT // snake의 현재 진행 방향



    // ------------------------------------------------------------
    // initialization
    //

    init{
        init()
    }


    // ------------------------------------------------------------
    // methods
    //

    /**
     * game을 처음 시작할 때 호출되어 snake를 초기화 시킨다.
     * 현재까지의 자취를 모두 지우고, 랜덤한 위치에서 한 칸 부터 새로 시작한다.
     */
    fun init() {
        positions.clear()
        positions.addFirst(Position(SecureRandom().nextInt(15), SecureRandom().nextInt(20)))
        direction = Direction.INIT
    }

    /**
     * 상하좌우 화살표로부터 click 이벤트가 발생한 경우 호출되며 snake의 현재 진행 방향을 변경한다.
     * 단 상<->하 , 좌<->우 사이의 변경은 무시되며, 초기 상태(정지 상태)에서는 모든 방향으로 변경 가능하다.
     */
    fun turnDirection(to: Direction) {
        direction = if(to == Direction.UP || to == Direction.DOWN) {
            if(direction == Direction.UP || direction == Direction.DOWN) return
            to
        } else if(to == Direction.LEFT || to == Direction.RIGHT) {
            if (direction == Direction.LEFT || direction == Direction.RIGHT) return
            to
        } else {
            to
        }
    }

    /**
     * game에서 step마다 호출되며 현재 진행 방향으로 snake를 한 칸 씩 움직인다.
     * 만약 움직인 위치가 화면을 벗어나거나, 자신의 몸에 부딪히는 경우 false를 리턴하며,
     * 그렇지 않은 경우에는 true를 리턴한다.
     */
    fun move(): Boolean {
        if(direction == Direction.INIT) return true
        val head = getHead()

        val nextPosition = Position(head.x + dx[direction.i], head.y + dy[direction.i])

        if(nextPosition.isValid() && checkBodyOverlap(nextPosition)) {
            positions.addFirst(nextPosition)
            return true
        }
        return false
    }



    // ------------------------------------------------------------
    // private methods
    //

    /**
     * 새로 움직인 위치가 자신의 몸에 부딪히는지 확인한다.
     */
    private fun checkBodyOverlap(nextPosition: Position): Boolean {
        for(pos in positions) {
            if(pos.isOverlapped(nextPosition)) {
                return false
            }
        }
        return true
    }

    /**
     * snake의 머리를 반환한다.
     */
    private fun getHead(): Position {
        return positions.first()
    }


    /**
     * snake가 음식을 먹었는지 확인한다.
     * (snake의 머리가 food의 위치와 겹치면 먹은걸로 간주한다.)
     */
    fun checkFoodEat(foodPosition:Position): Boolean {
        if(direction == Direction.INIT) return false

        val head = getHead()
        return if(head.isOverlapped(foodPosition)) {
            true
        } else{
            positions.removeLast()
            false
        }
    }

}