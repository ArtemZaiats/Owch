package com.owch.owch.data

data class Defect(
    val id: Long = 0L,
    val user_id: Long = 0L,
    val carrier_id: Long = 0L,
    val defect_status: Long = 0L,
    val vehicle_id: Long = 0L,
    val driver_comment: String? = null,
    val severity: Int = 0,
    val priority: Int = 0,
    val assigned_mechanic: Long = 0L,
    val work_order: String? = null,
    val date_create: String? = null,
    val last_update: String? = null,
    val attachment_image: String? = null,
    val creator_name: String? = null,
    val creator_last: String? = null,
    val vehicle_name: String? = null,
    val vehicle_type: Long = 0L,
    val form_title: String? = null,
    val card_title: String? = null,
    val card_icon: String? = null,
    val type_title: String? = null,
    val mechanic_name: String? = null,
    val mechanic_last: String? = null,
)

data class DefectResponse(
    val result: Defect
)

data class DefectRequestBody(
    val vehicle_id: Long = 0L,
    val driver_comment: String = "",
    val attachment_image: String = "",
    val severity: Int = 0,
    val priority: Int = 0,
)