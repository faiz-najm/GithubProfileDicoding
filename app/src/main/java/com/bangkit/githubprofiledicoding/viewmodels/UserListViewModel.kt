package com.bangkit.githubprofiledicoding.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.githubprofiledicoding.data.remote.response.User
import com.bangkit.githubprofiledicoding.data.remote.retrofit.ApiConfig
import com.bangkit.githubprofiledicoding.data.remote.retrofit.ApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<User>>()
    val listUser: LiveData<List<User>> = _listUser

    private val _apiSatus = MutableLiveData<ApiStatus>()
    val apiSatus: LiveData<ApiStatus> = _apiSatus

    companion object {
        private const val TAG = "UserListViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    init {
        searchUser("faiz")
    }

    fun searchUser(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _apiSatus.postValue(ApiStatus.LOADING)

            try {
                val response = ApiConfig.getApiService().getListUsers(q)
                _listUser.postValue(response.items)
                _apiSatus.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d(TAG, "Failure: ${e.message}")
                _apiSatus.postValue(ApiStatus.FAILED)
            }
        }
    }

    /*fun postReview(review: String) {
        _apiSatus.value = ApiStatus.LOADING
        val client = ApiConfig.getApiService().postReview(RESTAURANT_ID, "Dicoding", review)
        client.enqueue(object : Callback<PostReviewResponse> {
            override fun onResponse(
                call: Call<PostReviewResponse>,
                response: GithubResponse<PostReviewResponse>
            ) {
                _apiSatus.value = ApiStatus.SUCCESS
                if (response.isSuccessful) {
                    _listUser.value = response.body()?.customerReviews
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                _apiSatus.value = ApiStatus.FAILED
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }*/
}