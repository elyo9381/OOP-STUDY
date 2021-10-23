package com.ddoeri.framework

open abstract class ItemCreator {

    fun create() : Item? {

        var item : Item? = null

        requeestItemInfo()
        item = createItem()
        createItemLog()

        return item
    }

    protected abstract fun requeestItemInfo() : Nothing
    protected abstract fun createItemLog() : Nothing
    protected abstract fun createItem() : Item?
}