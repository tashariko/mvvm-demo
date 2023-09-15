package com.tasha.screens.people.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasha.data.ApiResult
import com.tasha.data.local.entity.People
import com.tasha.data.local.entity.Vehicle
import com.tasha.screens.people.base.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(val repository: BaseRepository) : ViewModel() {


    var liveData: MutableLiveData<ApiResult<List<People>>> = MutableLiveData()

    fun getData() {
        viewModelScope.launch {
            repository.getPeopleList()
                .catch {
                    it.printStackTrace()
                    liveData.value = ApiResult.error<List<People>>(errorMessage = it.localizedMessage)
                }.collect {
                    liveData.value = it
                }
        }
    }

}