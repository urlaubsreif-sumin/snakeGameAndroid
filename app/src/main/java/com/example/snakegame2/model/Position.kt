package com.example.snakegame2.model

class Position(val x: Int, val y: Int) {

    // ------------------------------------------------------------
    // methods
    //

    /**
     * 현재 위치와 인자로 입력 받은 위치가 겹치는지 확인한다.
     */
    fun isOverlapped(pos: Position): Boolean {
        if(this.x == pos.x && this.y == pos.y) return true
        return false
    }

    /**
     * 현재 위치가 화면을 벗어났는지 확인한다.
     */
    fun isValid(): Boolean {
        if(x < 0 || x >= 15 || y < 0 || y >= 20) return false
        return true
    }
}