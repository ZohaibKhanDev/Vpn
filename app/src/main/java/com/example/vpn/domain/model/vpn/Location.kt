package com.example.vpn.domain.model.vpn


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("city")
    val city: String,
    @SerialName("continent")
    val continent: String,
    @SerialName("continent_code")
    val continentCode: String,
    @SerialName("country")
    val country: String,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("is_in_european_union")
    val isInEuropeanUnion: Boolean,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("locale_code")
    val localeCode: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("metro_code")
    val metroCode: String,
    @SerialName("region")
    val region: String,
    @SerialName("region_code")
    val regionCode: String,
    @SerialName("time_zone")
    val timeZone: String
)