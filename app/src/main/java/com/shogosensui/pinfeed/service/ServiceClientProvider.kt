package com.shogosensui.pinfeed.service

import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceClientProvider {
    companion object {
        fun provideApiService(): PinboardApiService {
            return Retrofit.Builder()
                    .baseUrl(PinboardApiService.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build()
                    .create(PinboardApiService::class.java)
        }

        fun provideFeedService(): PinboardFeedService {
            return Retrofit.Builder()
                    .baseUrl(PinboardFeedService.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build()
                    .create(PinboardFeedService::class.java)
        }
    }
}