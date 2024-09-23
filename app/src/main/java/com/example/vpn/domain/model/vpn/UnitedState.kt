package com.example.vpn.domain.model.vpn


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnitedState(
    @SerialName("ip")
    val ip: String,
    @SerialName("location")
    val location: Location,
    @SerialName("network")
    val network: Network,
    @SerialName("security")
    val security: Security
)