package com.gopay.screens.data

import com.gopay.MainCoroutineRule
import kotlinx.coroutines.flow.collect
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
        repository = VehicleRepository(FakeDataSource())
    }

    @Test
    fun dataIsSuccess() = runTest{
        repository.getVehicleList().collect{
            Assert.assertEquals(it.data?.get(0)?.name, IsEqual("Vehicle1"))
        }
    }

}