package com.tasha.screens.data

import com.tasha.data.ApiResult
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleRepositoryTest {

    lateinit var repository: VehicleRepository

    @Before
    fun setup() {
        repository = VehicleRepository(FakeDataSource(), FakeDataSource())
        repository.showDataInAllState = true
    }

    @Test
    fun dataIsSuccess() = runBlocking{

        repository.getVehicleList().collectIndexed { index, value ->
            if(index == 0) {
                Assert.assertEquals(value.status, ApiResult.Status.LOADING)
                Assert.assertTrue(value.data != null)
            }else {
                Assert.assertEquals(value.status, ApiResult.Status.SUCCESS)
                Assert.assertTrue(value.data != null)
            }
        }
    }

}