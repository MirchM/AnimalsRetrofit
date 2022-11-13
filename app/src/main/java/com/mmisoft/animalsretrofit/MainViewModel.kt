package com.mmisoft.animalsretrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmisoft.animalsretrofit.data.api.model.Animal
import com.mmisoft.animalsretrofit.data.repository.AnimalRetrofitRepository
import com.mmisoft.animalsretrofit.util.InternetHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val animalRetrofitRepo: AnimalRetrofitRepository,
) : ViewModel() {
   // private list of animals
    private val _animalsState = MutableStateFlow(emptyList<Animal>())

    // the public list of animals observed by the implementing member of the ViewModel
    val animalsState: StateFlow<List<Animal>>
        get() = _animalsState

    // private boolean state of the internet connection
    // openDialog is used to tell the implementing member of the ViewModel
    // when the "check you internet connection" dialog should be open
    private val _openDialog = MutableStateFlow(false)

    // public boolean for the internet connection dialog observed
    // by the implementing member of this ViewModel
    val openDialog: StateFlow<Boolean>
        get() = _openDialog

    // loads an initial list of Animal objects
    init {
        viewModelScope.launch(Dispatchers.IO) {
            if((InternetHelper.isInternetAvailable())){
                val animalResponse = animalRetrofitRepo.getAnimals(Random.nextInt(5, 10))
                _animalsState.value = animalResponse
            }else{
                _openDialog.value = true
            }
        }
    }

    /**
     * Fetches another random list of objects of type Animal from the api.
     * Updates the state of the _animalstate based on the response.
     */
    fun refreshAnimals() {
        //_animalsState.value = emptyList<Animal>()
        viewModelScope.launch(Dispatchers.IO) {
            println("HERE-A")
            val animalResponse = animalRetrofitRepo.getAnimals(Random.nextInt(5, 10))
            println("HERE-B")
            _animalsState.value = animalResponse
            println("HERE-C")
        }
    }

    /**
     * Checks the internet connection. Updates the state of the openDialog
     * based on the internet connection status
     */
    fun checkInternetConnection() {
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _openDialog.value =
                !(InternetHelper.isInternetAvailable())
        }
    }
}