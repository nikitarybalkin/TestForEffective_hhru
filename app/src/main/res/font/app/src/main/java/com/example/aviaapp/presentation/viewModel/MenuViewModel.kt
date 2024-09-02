package com.example.aviaapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aviaapp.domain.model.CityModel
import com.example.aviaapp.domain.usecases.GetDataUseCase
import com.example.aviaapp.domain.usecases.OffersUseCase
import com.example.aviaapp.domain.usecases.SaveDataUseCase
import com.example.aviaapp.domain.model.OffersModel
import com.example.aviaapp.domain.usecases.CityUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val saveDataUseCase: com.example.aviaapp.domain.usecases.SaveDataUseCase,
    private val getDataUseCase: com.example.aviaapp.domain.usecases.GetDataUseCase,
    private val offersUseCase: com.example.aviaapp.domain.usecases.OffersUseCase,
    private val cityUseCase: com.example.aviaapp.domain.usecases.CityUseCase
) : ViewModel() {

    var offers: MutableStateFlow<com.example.aviaapp.domain.model.OffersModel?> = MutableStateFlow(null)
    var city: Flow<List<com.example.aviaapp.domain.model.CityModel?>> = MutableStateFlow(emptyList())
    fun saveData(key: String, value: String) {
        saveDataUseCase.saveData(key = key, value = value)
    }

    fun getData(key: String): String? {
        return getDataUseCase.getData(key = key)
    }

    fun insertCity(city: com.example.aviaapp.domain.model.CityModel) {
        viewModelScope.launch {
            cityUseCase.insert(city)
        }
    }

    fun getCity() {
        //Так как, в ТЗ не было указано где использовать БД и на hh.ru мне не ответили на этот вопрос, я реализовал БД, но нигде не использовал
        viewModelScope.launch {
            city = cityUseCase.getAll()
        }
    }

    fun getOffers() {

        viewModelScope.launch {
            offers.value = offersUseCase.getOffers()
            /*Log.d("LOL", "перед трай робит")
            try {

                Log.d("LOL", "трай робит")
            }
            catch (e: Exception) {
                Log.d("LOL", "ошибка в кэтч")
            }

             */
        }



    }
}