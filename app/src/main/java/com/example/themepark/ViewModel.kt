package com.example.themepark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themepark.models.LoginRequest
import com.example.themepark.models.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _loginResponse = MutableLiveData<LoginResponse?>()
    val loginResponse: LiveData<LoginResponse?> = _loginResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequest(username, password))
                if (response.isSuccessful && response.body() != null) {
                    _loginResponse.postValue(response.body())
                } else {
                    _error.postValue("Invalid credentials")
                }
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}
