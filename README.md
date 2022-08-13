# About
This project implements a single screen that's similar to the Baraka app's insights screen. There are 3 components of the screen: an insights section that implements a stock ticker, a horizontal news section that contains the news' image and headline, and the last section contains a vertical news section that includes more details regarding the news which are the author, date, and description.

# Architecture

The architecture used after refactoring is the MVVM architecture. [Google's guide to app architecture](https://developer.android.com/topic/architecture) was also respected in the refactoring, with the two layers(UI and Data) present.

## Data Layer

### Repository
A repository was created to hold the app's data. The repository contains an interface and an implementation; this was done to follow clean architecture guidelines(separation of concerns) and make the app much more testable.

### CSV Parsing

To parse the CSV data, the OpenCSV library was implemented for its simplicity and the ability to easily read the data and assign it to the data classes with a few lines of code.

### Data Classes

Data classes for the news and stocks were also implemented.

### Json Parsing

To parse json data, the Gson library was implemented.

## DI Layer

The DI layer contains the AppModule; this class contains the methods needed by Dagger-Hilt for dependency injection.

### Dependency Injection

For dependency injection, Dagger-Hilt was introduced. Dagger-Hilt allows for an easier setup and easier integration with other Jetpack libraries, it is also runtime-safe.


## UI

Three adapters were introduced for the three recyclerviews of the screen. These adapters gets the data from the viewmodel and assigns it to the right item in the recyclerview.

A viewmodel was created to hold the app's business logic and expose the data to the UI to be observed. Three livedata values were introduced: `horizontalNews` , `verticalNews` and `networkState`.

* The `horizontalNews` and `verticalNews` values hold the two lists of news fetched from the json file and are then observed in the activity.

* The `stocksList` value holds the list of stocks from the CSV file.

* The `networkState` data was introduced for the json file to be treated like a real life api. When dealing with a real life api, an enum class is introduced to deal with the three different network states: Done, Loading, and Error.

A progress bar was also introduced. This bar is shown when the network status is `LOADING`

## Testing

Two unit tests were introduced. One checks if the livedata is properly being observed, and another checks that if a list is passed, then the network state presented is `DONE`.

A fake repository was introduced as well. This fake repository holds some data used for testing.

The fake repository is then used in the `ViewModelTest` class as an argument for the viewmodel.




