package com.example.vpn.domain.model.vpn


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Security(
    @SerialName("proxy")
    val proxy: Boolean,
    @SerialName("relay")
    val relay: Boolean,
    @SerialName("tor")
    val tor: Boolean,
    @SerialName("vpn")
    val vpn: Boolean
)