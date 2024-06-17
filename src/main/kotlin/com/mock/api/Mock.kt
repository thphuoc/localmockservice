package com.mock.api

data class Mock(
    val host: String = "localhost",
    val httpPort: Int = 80,
    val sslPort: Int = 443,
    val endpoints: List<Endpoint>
)

data class Endpoint (
    val name: String,
    val method: String,
    val responseType: String,
    val response: Any,
    val httpCode: Int
)