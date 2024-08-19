package com.ifsp.shoppinglist.viewmodels

import androidx.lifecycle.ViewModel
import com.ifsp.shoppinglist.data.ShoppingItem
import com.ifsp.shoppinglist.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// To instantiate this ViewModel, I need to have a factory which ViewModelProviders can use to create its instance.
// ViewModelProviders Utility can not create instance of a ViewModel with argument constructor because
// it does not know how and what objects to pass in the constructor.
class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {
    // Here in the ViewModel we want to call all the functions in the ShoppingRepository

    // As upsert is the coroutine function that needs to be run under the CoroutineScope(globally).
    // Dispatchers.Main tells us that, for what context we want to run the the functions like "upsert"
    // Instead of Main, we can perform Database operations in IO, but Room provides Main safety. And
    // also there is Default which is used for long running operations.

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}