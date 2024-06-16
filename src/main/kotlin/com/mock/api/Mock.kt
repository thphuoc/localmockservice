package com.mock.api

data class Mock(
    val host: String,
    val port: Int,
    val endpoints: List<Endpoint>
)

data class Endpoint (
    val name: String,
    val method: String,
    val responseType: String,
    val response: Any,
    val httpCode: Int
)