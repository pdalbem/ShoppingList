package com.ifsp.shoppinglist.repositories

import com.ifsp.shoppinglist.data.ShoppingDatabase
import com.ifsp.shoppinglist.data.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    // inside the repository class we implement all the database methods which are defined inside
    // the ShoppingDao object and call these following and provide these methods for the ViewModel
    // so that ViewModel can call these methods.

    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}