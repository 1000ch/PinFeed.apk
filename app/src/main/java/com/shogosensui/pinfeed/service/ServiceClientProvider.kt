package com.shogosensui.pinfeed.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceClientProvider {
    companion object {
        fun provideApiService(): PinboardApiService {
            return Retrofit.Builder()
                    .baseUrl(PinboardApiService.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PinboardApiService::class.java)
        }

        fun provideFeedService(): PinboardFeedService {
            return Retrofit.Builder()
                    .baseUrl(PinboardFeedService.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PinboardFeedService::class.java)
        }
    }
}