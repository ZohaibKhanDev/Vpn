package com.example.vpn.domain.repository

import com.example.vpn.data.remote.VpnApiClient
import com.example.vpn.data.repository.ApiClient
import com.example.vpn.domain.model.vpn.UnitedState

class Repository : ApiClient {
    override suspend fun getUnitedVpn(): UnitedState {
        return VpnApiClient.getUnitedState()
    }
}