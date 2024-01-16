package com.owch.owch.data

data class Token(
    val token_type: String,
    val access_token: String,
    val refresh_token: String
)

data class TokenRequestBody(
    val username: String,
    val password: String
)
