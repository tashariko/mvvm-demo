package com.tasha.dependencies

import com.tasha.screens.people.data.PeopleDataSource
import com.tasha.screens.people.data.PeopleLocalDataSource
import com.tasha.screens.people.data.PeopleRepository
import com.tasha.screens.planets.data.PlanetDataSource
import com.tasha.screens.planets.data.PlanetLocalDataSource
import com.tasha.screens.planets.data.PlanetRepository
import com.tasha.screens.vehicles.data.VehicleDataSource
import com.tasha.screens.vehicles.data.VehicleLocalDataSource
import com.tasha.screens.vehicles.data.VehicleRepository
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
    fun provideVehicleRepo(dataSource: VehicleDataSource, dataLocalSource: VehicleLocalDataSource) : com.tasha.screens.vehicles.base.BaseRepository {
        return VehicleRepository(dataSource,dataLocalSource)
    }

    @Provides
    @Singleton
    fun providePeopleRepo(dataSource: PeopleDataSource, dataLocalSource: PeopleLocalDataSource) : com.tasha.screens.people.base.BaseRepository {
        return PeopleRepository(dataSource,dataLocalSource)
    }

    @Provides
    @Singleton
    fun providePlanetRepo(dataSource: PlanetDataSource, dataLocalSource: PlanetLocalDataSource) : com.tasha.screens.planets.base.BaseRepository {
        return PlanetRepository(dataSource,dataLocalSource)
    }
}