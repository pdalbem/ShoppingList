package com.ifsp.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*

// Dao basically is implemented to inform the Room Database that how we want to access the database
// we define the functions in Dao like insert, update, delete, create. By creating these functions
// we access the database.
// https://developer.android.com/training/data-storage/room/accessing-data
@Dao
interface ShoppingDao {

    // upsert is mixture of update and insert.
    // If the ID of the "item" passed is not present in the database, then it inserts the new entry.
    // If the ID is already available then it will just update.
    // But we have to tell this mechanism to Room database by annotating it with
    // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    // To perform delete operation add the following.
    @Delete
    suspend fun delete(item: ShoppingItem)

    // NOTE: In SQL it doesn't allow write to the database using main thread. We have to call these
    // functions asynchronously. We use AsyncTask in Java and in Kotlin we use Kotlin coroutines.

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}