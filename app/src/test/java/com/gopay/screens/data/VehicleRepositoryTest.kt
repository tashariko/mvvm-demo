package com.gopay.screens.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gopay.MainCoroutineRule
import com.gopay.data.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleRepositoryTest {

    lateinit var repository: VehicleRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setup() {
        repository = VehicleRepository(FakeDataSource(), FakeDataSource())
        repository.showDataInAllState = false
    }

    @Test
    fun dataIsSuccess() = runTest{
        repository.getVehicleList().collectIndexed { index, value ->
            if(index == 0)
                Assert.assertEquals(value.status, ApiResult.Status.LOADING)
            else
                Assert.assertEquals(value.status, ApiResult.Status.SUCCESS)
        }
    }

}