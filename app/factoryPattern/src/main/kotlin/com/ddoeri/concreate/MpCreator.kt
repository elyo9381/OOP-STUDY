package com.ddoeri.concreate

import com.ddoeri.framework.Item
import com.ddoeri.framework.ItemCreator

class MpCreator : ItemCreator() {
    override fun requeestItemInfo() {
        System.out.println("데이터베이스에서 마력 회복 물약의 정보를 가져옵니다.")

    }

    override fun createItemLog() {
        System.out.println("마력 회복 물약 정보를 생성 정보를 기록합니다. ")
    }

    override fun createItem(): Item? {
        System.out.println("마력 회복 물약 정보를 생성 헙나다. ")
        return null
    }
}