package com.example.mycountrylistapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycountrylistapp.MainRepository
import com.example.mycountrylistapp.data.Countries
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val countryList = MutableLiveData<List<Countries>>()
    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getAllCountries() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countryList.postValue(response.body())
                    loading.value = false
                    Log.i(TAG, " response.isSuccessful ")
                } else {
                    onError("Error is : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}

