package com.ifsp.shoppinglist.di

import com.ifsp.shoppinglist.data.ShoppingDatabase
import com.ifsp.shoppinglist.repositories.ShoppingRepository
import com.ifsp.shoppinglist.viewmodels.ShoppingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ShoppingModule: Module = module {
    single { ShoppingDatabase(get()) }
    single { ShoppingRepository(get()) }
    viewModel { ShoppingViewModel(get()) }
}