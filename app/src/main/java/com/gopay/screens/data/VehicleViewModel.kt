package com.gopay.screens.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gopay.data.ApiResult
import com.gopay.data.local.entity.Vehicle
import com.gopay.screens.base.BaseRepository
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
                }.collect {
                    liveData.value = it
                }
        }
    }

}