package com.owch.owch.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owch.owch.data.Token
import com.owch.owch.data.TokenRequestBody
import com.owch.owch.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TokenViewModel: ViewModel() {

    companion object {
        private const val TAG = "TokenViewModel"
    }

    private val _response = MutableStateFlow<Token?>(null)
    val response: StateFlow<Token?> = _response

    fun getUserToken(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getUserToken(login, password)

                if (response.isSuccessful) {
                    _response.value = response.body()
                    Log.d(TAG, "response: ${response.body()}")
                } else {
                    Log.e(TAG, "Unsuccessful response: $response")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}", e)
            }
        }
    }
}