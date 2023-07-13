package com.gopay.screens.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gopay.MainCoroutineRule
import com.gopay.getOrAwaitValue
import com.gopay.screens.data.FakeDataSource
import com.gopay.screens.data.VehicleViewModel
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