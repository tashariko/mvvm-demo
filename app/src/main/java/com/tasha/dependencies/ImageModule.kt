package com.tasha.dependencies

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ImageModule {
    @Provides
    @Singleton
    fun providesPicasso(@ApplicationContext context: Context): Picasso {
        return Picasso.Builder(context)
            .downloader(OkHttp3Downloader(context))
            .build()
    }
}