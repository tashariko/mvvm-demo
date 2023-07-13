package com.gopay.screens

import com.gopay.screens.data.VehicleRepositoryTest
import com.gopay.screens.repo.VehicleViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    VehicleViewModelTest::class,
    VehicleRepositoryTest::class
)
class TestSuite