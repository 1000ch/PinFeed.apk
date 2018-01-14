package com.shogosensui.pinfeed

import android.app.Application
import android.util.Log
import com.shogosensui.pinfeed.payload.*
import com.shogosensui.pinfeed.service.PinboardApiService
import com.shogosensui.pinfeed.service.PinboardFeedService
import com.shogosensui.pinfeed.service.ServiceClientProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainApplication : Application() {
    lateinit var apiClient: PinboardApiService
    lateinit var feedClient: PinboardFeedService
    lateinit var preference: Preference

    override fun onCreate() {
        super.onCreate()

        apiClient = ServiceClientProvider.provideApiService()
        feedClient = ServiceClientProvider.provideFeedService()
        preference = Preference(this)

        apiClient.apiToken(PinboardApiService.credentials).enqueue(object : Callback<ApiTokenPayload> {
            override fun onResponse(call: Call<ApiTokenPayload>, response: Response<ApiTokenPayload>) {
                response.body()?.let {
                    preference.apiToken = it.result
                }
            }

            override fun onFailure(call: Call<ApiTokenPayload>, t: Throwable?) {
                Log.e("onFailure", "Failed to get api token")
            }
        })

        apiClient.secretToken(PinboardApiService.credentials).enqueue(object : Callback<SecretTokenPayload> {
            override fun onResponse(call: Call<SecretTokenPayload>, response: Response<SecretTokenPayload>) {
                response.body()?.let {
                    preference.secretToken = it.result

                    feedClient.bookmark(preference.secretToken, "1000ch").enqueue(object : Callback<BookmarkPayload> {
                        override fun onResponse(call: Call<BookmarkPayload>, response: Response<BookmarkPayload>) {
                            response.body()?.let {
                                Log.d("count", it.count().toString())
                            }
                        }

                        override fun onFailure(call: Call<BookmarkPayload>, t: Throwable?) {
                            Log.e("onFailure", "Failed to get bookmark")
                        }
                    })

                    feedClient.network(preference.secretToken, "1000ch").enqueue(object : Callback<BookmarkPayload> {
                        override fun onResponse(call: Call<BookmarkPayload>, response: Response<BookmarkPayload>) {
                            response.body()?.let {
                                Log.d("count", it.count().toString())
                            }
                        }

                        override fun onFailure(call: Call<BookmarkPayload>, t: Throwable?) {
                            Log.e("onFailure", "Failed to get network")
                        }
                    })
                }
            }

            override fun onFailure(call: Call<SecretTokenPayload>, t: Throwable?) {
                Log.e("onFailure", "Failed to get secret token")
            }
        })

        apiClient.getPost(PinboardApiService.credentials, "json", null, null, "https://github.com", null).enqueue(object : Callback<GetPostPayload> {
            override fun onResponse(call: Call<GetPostPayload>, response: Response<GetPostPayload>) {
                response.body()?.let {
                    Log.d("count", it.posts.count().toString())
                }
            }

            override fun onFailure(call: Call<GetPostPayload>, t: Throwable?) {
                Log.e("onFailure", "Failed to get post")
            }
        })

        apiClient.addPost(PinboardApiService.credentials, "json", "https://github.com", "GitHub", null, null, null, null, null, null).enqueue(object : Callback<AddPostPayload> {
            override fun onResponse(call: Call<AddPostPayload>, response: Response<AddPostPayload>) {
                response.body()?.let {
                    Log.d("result_code", it.result_code)
                }
            }

            override fun onFailure(call: Call<AddPostPayload>, t: Throwable?) {
                Log.e("onFailure", "Failed to add post")
            }
        })

        apiClient.deletePost(PinboardApiService.credentials, "json", "https://github.com").enqueue(object : Callback<DeletePostPayload> {
            override fun onResponse(call: Call<DeletePostPayload>, response: Response<DeletePostPayload>) {
                response.body()?.let {
                    Log.d("result_code", it.result_code)
                }
            }

            override fun onFailure(call: Call<DeletePostPayload>, t: Throwable?) {
                Log.e("onFailure", "Failed to delete post")
            }
        })
    }
}