package com.tasha.screens.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tasha.MainCoroutineRule
import com.tasha.getOrAwaitValue
import com.tasha.screens.data.FakeDataSource
import com.tasha.screens.vehicles.data.VehicleViewModel
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleViewModelTest {
    lateinit var fakeRepository: FakeRepository
    lateinit var viewModel: VehicleViewModel

    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        fakeRepository = FakeRepository(FakeDataSource())
        viewModel = VehicleViewModel(fakeRepository)
    }

    @Test
    fun test_success() = runTest{
        viewModel.getData()
        advanceUntilIdle()
        val item = viewModel.liveData.getOrAwaitValue()
        Assert.assertEquals(item.data?.size, 4)

    }
}