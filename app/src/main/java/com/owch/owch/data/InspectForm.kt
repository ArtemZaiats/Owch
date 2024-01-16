package com.owch.owch.data

import com.google.gson.annotations.SerializedName

data class InspectForm(
    @SerializedName("id") val id: Long,
    @SerializedName("carrier_id") val carrierId: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("schedule_type") val scheduleType: Int,
    @SerializedName("specific_date") val specificDate: String,
    @SerializedName("is_mandatory") val isMandatory: Boolean,
    @SerializedName("deleted") val deleted: Boolean,
    @SerializedName("creator_name") val creatorName: String,
    @SerializedName("creator_last") val creatorLast: String,
    @SerializedName("card_count") val cardCount: Int,
    @SerializedName("vehicle_count") val vehicleCount: Int,
    @SerializedName("last_inspection_date") val lastInspectionDate: String
)

data class FormResponse(
    @SerializedName("result") val result: List<InspectForm>,
    @SerializedName("total") val total: Int
)
