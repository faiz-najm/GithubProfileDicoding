package com.bangkit.githubprofiledicoding.data.remote.retrofit

import com.bangkit.githubprofiledicoding.data.remote.response.GithubResponse
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    suspend fun getListUsers(
        @Query("q") q: String
    ): GithubResponse

}