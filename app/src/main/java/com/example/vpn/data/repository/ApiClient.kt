package com.example.vpn.data.repository

import com.example.vpn.domain.model.vpn.UnitedState

interface ApiClient {
    suspend fun getUnitedVpn(): UnitedState
}