package com.tasha.dependencies

import com.tasha.screens.base.BaseRepository
import com.tasha.screens.data.VehicleDataSource
import com.tasha.screens.data.VehicleLocalDataSource
import com.tasha.screens.data.VehicleRepository
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