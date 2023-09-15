package com.tasha.screens.planets.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Planet
import com.tasha.data.local.entity.Vehicle
import com.tasha.screens.planets.base.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(val repository: BaseRepository) : ViewModel() {

    var liveData: MutableLiveData<ApiResult<List<Planet>>> = MutableLiveData()

    fun getData() {
        viewModelScope.launch {
            repository.getPlanetList()
                .catch {
                    it.printStackTrace()
                    liveData.value = ApiResult.error<List<Planet>>(errorMessage = it.localizedMessage)
                }.collect {
                    liveData.value = it
                }
        }
    }

}