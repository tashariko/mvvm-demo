package com.tasha.screens.vehicles.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Vehicle
import com.tasha.screens.vehicles.base.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(val repository: BaseRepository) : ViewModel() {


    var liveData: MutableLiveData<ApiResult<List<Vehicle>>> = MutableLiveData()

    fun getData() {
        viewModelScope.launch {
            repository.getVehicleList()
                .catch {
                    it.printStackTrace()
                    liveData.value = ApiResult.error<List<Vehicle>>(errorMessage = it.localizedMessage)
                }.collect {
                    liveData.value = it
                }
        }
    }

}