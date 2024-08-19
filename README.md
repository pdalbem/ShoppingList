# Shopping List
**Shopping List** is item lising application based on local database application performing CRUD operations.


## Features
* User can create and save shopping item persistently.
* User can update shopping item.
* User can delete bought shopping item.
* User can interact with the app with one hand as it is a single activity application, as all the operations can be done by user with single click.

## Project structure (MVVM)
* adapters
* db
    * entities
* di
* models
* repositories
* viewmodels

<img src="https://developer.android.com/static/topic/libraries/architecture/images/final-architecture.png?hl=pt-br" />

## Android :heart: Koltin
* Generic Recycler View is used for listing the of items.
* Kotlin Coroutines are implemented to peroform the long running tasks of the IO operations like inserting, updating, deleting etc., the items from the Room Database.
* Koin framework is used for dependency injection.

## Tech stack used
- [Room DB](https://developer.android.com/training/data-storage/room) - Local Persistant Database for Application.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - For asynchronous operations.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Lifecycle aware library to manage data observing the lifecycle of licecycle owner.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - State holder class to hold observable data.
- [Koin DI](https://insert-koin.io/docs/reference/koin-android/start) - Lightweight Dependency Injection Framework for Android.
