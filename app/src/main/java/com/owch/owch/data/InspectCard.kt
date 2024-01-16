package com.owch.owch.data

import com.google.gson.annotations.SerializedName

data class InspectCard(
    @SerializedName("sort") val sort: Int,
    @SerializedName("card_id") val cardId: Long,
    @SerializedName("card_title") val cardTitle: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("card_type_id") val cardTypeId: Int,
    @SerializedName("type_title") val typeTitle: String,
    @SerializedName("titles") val titles: List<Title>
)

data class Title(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("defect_id") val defectId: Long,
    @SerializedName("card_type_value") val cardTypeValue: String,
    @SerializedName("card_type_description") val cardTypeDescription: String
)

data class Result(
    @SerializedName("cards") val cards: List<InspectCard>
)

data class CardResponse(
    @SerializedName("result") val result: Result
)