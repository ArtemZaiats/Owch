package com.owch.owch.data

data class InspectVehicle(
    val id: Long,
    val vehicle_id: Long,
    val vehicle_name: String,
    val vehicle_type: Long,
    val form_id: Long,
    val is_mandatory: Long,
    val inspected: Long,
    val last_inspection_date: String
)

data class VehicleResponse(
    val result: List<InspectVehicle>,
    val total: Long
)
