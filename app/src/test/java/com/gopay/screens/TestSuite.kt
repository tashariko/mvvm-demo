package com.gopay.screens

import com.gopay.screens.data.VehicleDataSourceTest
import com.gopay.screens.data.VehicleLocalDataSourceTest
import com.gopay.screens.data.VehicleRepositoryTest
import com.gopay.screens.repo.VehicleViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    VehicleDataSourceTest::class,
    VehicleLocalDataSourceTest::class,
    VehicleRepositoryTest::class,
    VehicleViewModelTest::class
)
class TestSuite