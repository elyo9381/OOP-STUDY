package com.ddoeri.Shape

class Shape : Cloneable {

    override fun clone(): Any {
        return super.clone()
    }

    private var id : String
        get() = this.id
        set(value) {
            this.id = value
        }


}