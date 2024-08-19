package com.ifsp.shoppinglist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class], //Class References: The most basic reflection feature is getting the runtime reference to a Kotlin class
    //Note that a Kotlin class reference is not the same as a Java class reference. To obtain a Java class reference, use the .java property on a KClass instance.

    version = 1 // version is the version of the Database schema. It basically tells the Room Database
    // if there we want to change the database schema then we need to change the version of the database.
)
abstract class ShoppingDatabase : RoomDatabase() {

    // For each DAO class that is associated with the database, the database class must define an
    // abstract method that has zero arguments and returns an instance of the DAO class.
    // With this function we make sure that we can access our database operations, inside the Database Class
    abstract fun getShoppingDao(): ShoppingDao

    // static keyword in Java is companion object in Kotlin
    // The instance of the Database need to be created once. If create multiple instance of Database
    // there is huge amount of memory consumption.
    companion object {
        @Volatile // Rights to "instance" will be made instantly visible to other threads.
        // We need to also make sure that, only one thread is accessing to that "instance".
        // Otherwise, if there are two threads creating the instance of Database then there will be
        // two instances of the Database. Basically we want to make the instance of the Database singleton
        private var instance: ShoppingDatabase? = null

        // When you specify an invoke operator on a class, it can be called on any instances of the
        // class without a method name!. This trick seems especially useful for classes that really
        // only have one method to be used.
        // In Below line invoke function is called whenever there is need of Database instance from any
        // class. If the "instance" is not null simply it returns the "instance" of database or else
        // it executes the synchronized block.
        private val LOCK: Any = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {

            // we return the instance
            // if the instance is null then we call the createDatabase
            instance ?: createDataBase(context).also {
                instance = it
            } // here the "it" is the result returned by the createDataBase Function, which is the
            // instance of the Database
        }
        // the above block is called everytime, when we need or create instance of Database.


        private fun createDataBase(context: Context) =
            Room.databaseBuilder(context, ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }

}