package com.tasha.screens

import com.tasha.screens.data.VehicleDataSourceTest
import com.tasha.screens.data.VehicleLocalDataSourceTest
import com.tasha.screens.data.VehicleRepositoryTest
import com.tasha.screens.repo.VehicleViewModelTest
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