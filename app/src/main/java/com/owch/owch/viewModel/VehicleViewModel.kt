package com.owch.owch.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.owch.owch.data.InspectVehicle
import com.owch.owch.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VehicleViewModel : ViewModel() {
    private val _vehicles = MutableStateFlow<List<InspectVehicle>>(emptyList())
    val vehicles: StateFlow<List<InspectVehicle>> = _vehicles

    fun fetchVehicles(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getFormVehicles(token)
                if (response.isSuccessful) {
                    _vehicles.value = response.body()?.result ?: emptyList()
                    Log.d("VehicleViewModel", "response: ${response.body()}")
                } else {
                    Log.e("VehicleViewModel", "Unsuccessful response: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("VehicleViewModel", "Error: ${e.message}", e)
            }
        }
    }
}