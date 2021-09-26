package com.example.snakegame2.model

import java.security.SecureRandom

object Food {
    lateinit var position: Position // food의 현재 위치

    init{
        generateNewFood()
    }

    /**
     * 게임을 시작하거나, snake가 food를 먹은 경우, 새로운 위치에 음식을 생성한다.
     */
    fun generateNewFood() {
        position = Position(SecureRandom().nextInt(15), SecureRandom().nextInt(20))
    }
}