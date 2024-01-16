package com.owch.owch.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owch.owch.data.DefectRequestBody
import com.owch.owch.data.InspectCard
import com.owch.owch.data.InspectForm
import com.owch.owch.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FormViewModel : ViewModel() {
    private val _forms = MutableStateFlow<List<InspectForm>>(emptyList())
    private val _cards = MutableStateFlow<List<InspectCard>>(emptyList())
    val forms: StateFlow<List<InspectForm>> = _forms
    val cards: StateFlow<List<InspectCard>> = _cards

    fun fetchForms(token: String, vehicleId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getFormList(token, vehicleId)
                if (response.isSuccessful) {
                    _forms.value = response.body()?.result ?: emptyList()
                    Log.d("FormViewModel", "response: ${response.body()}")
                } else {
                    Log.e("FormViewModel", "Unsuccessful response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("FormViewModel", "Unsuccessful response: ${e.message}")
            }
        }
    }

    fun fetchCardsByFormId(token: String, formId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getCards(token, formId)
                if (response.isSuccessful) {
                    _cards.value = response.body()?.result?.cards ?: emptyList()
                    Log.d("FormViewModel", "cards response: ${response.body()}")
                } else {
                    Log.e("FormViewModel", "Unsuccessful response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("FormViewModel", "Unsuccessful response: ${e.suppressed}")
            }
        }
    }

    fun createDefect(token: String) {
        val requestBody = DefectRequestBody(
            vehicle_id = 60492L,
            driver_comment = "test",
            attachment_image = "test",
            severity = 1,
            priority = 1
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.createDefect(token, requestBody)
                if (response.isSuccessful) {
                    Log.d("FormViewModel", "createDefect response: ${response.body()}")
                } else {
                    Log.e("FormViewModel", "createDefect unsuccessful response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("FormViewModel", "Unsuccessful response: ${e.message}")
            }
        }
    }


}