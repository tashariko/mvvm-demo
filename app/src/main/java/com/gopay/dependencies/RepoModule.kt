package com.gopay.dependencies

import com.gopay.data.local.dao.VehicleDao
import com.gopay.screens.base.BaseDataSource
import com.gopay.screens.base.BaseRepository
import com.gopay.screens.data.VehicleDataSource
import com.gopay.screens.data.VehicleLocalDataSource
import com.gopay.screens.data.VehicleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideVehicleRepo(dataSource: VehicleDataSource, dataLocalSource: VehicleLocalDataSource) : BaseRepository{
        return VehicleRepository(dataSource,dataLocalSource)
    }
}