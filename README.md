# MoreMovies

Android application that shows a list of most popular movies and their details

Min API Level 23

##### Branches:
*  main (Jetpack  compose)
*  xml_views 


## Tech-stack
* Retrofit
* Flow and coroutines for asynchronous operations
* Hilt for dependency injection
* Coil
* Espresso UI Testing
* Mockito
* Android Jetpack

    - Navigation
    - ViewModel
    - Room
    - Test

## Architecture
It implements a Clean Architecture:

* Presentation  
  MVVM pattern
    - Screens(Activity/Fragments/Views)
    - ViewModels

* Data  
  Repository Pattern  
  Define data models and mappers  
  Datasources:
    - API: get data from The Movie Database API when data are not on cache
    - Room DB: save and get data from here

