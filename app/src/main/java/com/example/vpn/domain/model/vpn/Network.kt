package com.example.vpn.domain.model.vpn


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Network(
    @SerialName("autonomous_system_number")
    val autonomousSystemNumber: String,
    @SerialName("autonomous_system_organization")
    val autonomousSystemOrganization: String,
    @SerialName("network")
    val network: String
)