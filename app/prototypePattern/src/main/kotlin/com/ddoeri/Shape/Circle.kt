package com.ddoeri.Shape

/**
 *
 * 자바에서는 깊은 복사 , 얇은 복사가 존재하기 때문에 이를 고려한 프로토 타입 패턴을 진행해야한다.
 */
class Circle  {

    private var x : Int
        get() = this.x
        set(value) { this.x = value}

    private var y : Int
        get() = this.y
        set(value) { this.y = value}
    private var r : Int
        get() = this.r
        set(value) { this.r = value}


}