package com.ifsp.shoppinglist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// We tell kotlin compiler that the main purpose of this class is to hold the data.
// Entity is just a table inside Database.
// So it is annotated with Entity.
// https://developer.android.com/training/data-storage/room/defining-data
@Entity(tableName = "shopping_items")
data class ShoppingItem(
    // each of data accepted in the constructor are the name of the columns in database
    // and name of the column will be same as that of the variable names.

    // but if we want to choose the name of the column we need to manually add the annotation @ColumnInfo.

    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int
) {
    // Without the PrimaryKey the Room will throw error.

    // PrimaryKey is also another column, that holds the unique id for every entry in the database.

    // Here also if we define any variable it becomes column in the database.

    // So we define the primary key inside the body of class as we are autogenerating the PrimaryKey
    // for each entry. If we define the PrimaryKey in the constructor then we need to manually pass,
    // unique PrimaryKey for each and evey entry we add in table. So it is better to autogenerate.
    // Room will generate unique primary ID for all the entries in the table name.

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}