package com.ddoeri

import com.ddoeri.concreate.HpCreator
import com.ddoeri.concreate.MpCreator
import com.ddoeri.framework.Item
import com.ddoeri.framework.ItemCreator
import java.util.*

fun main() {
    var creator : ItemCreator
    var item : Item?


    creator = HpCreator()
    item = creator.create()

    item?.use()

    creator = MpCreator()
    item = creator.create()

    item?.use()
}