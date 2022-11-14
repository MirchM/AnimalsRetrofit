
# AnimalsRetrofit

AnimalsRetrofit is an android app created using an MVVM pattern.

The path of the data is very simple, the UI layer only observes and calls functions in the ViewModel. ->
The ViewModel has a repository instance that is used to call functions from it. -> 
The Repository has an instance of the retrofit api service and uses it to get data from the api.

The app displays a list of animals with pictures retrieved from the https://zoo-animal-api.herokuapp.com/.




## Tech Stack

- Kotlin and jetpack Compose
- Dagger Hilt for dependency Injection
- compose-destinations by raamcosta for navigation
- Retrofit for the Api Call
- Glide for the images


## Authors

- [@MirchM](https://github.com/MirchM)
