package com.ddoeri.concreate

import com.ddoeri.framework.Item

class HpPotion : Item{
    override fun use() {
        System.out.println("체력회복")
    }

}