package com.bangkit.githubprofiledicoding.ui.detailprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is detailprofile Fragment"
    }
    val text: LiveData<String> = _text
}