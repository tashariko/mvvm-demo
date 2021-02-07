package com.gopay.dependencies

import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
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