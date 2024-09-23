package com.example.vpn.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vpn.domain.model.vpn.UnitedState
import com.example.vpn.domain.repository.Repository
import com.example.vpn.domain.usecase.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _allVpn = MutableStateFlow<ResultState<UnitedState>>(ResultState.Loading)
    val allVpn: StateFlow<ResultState<UnitedState>> = _allVpn.asStateFlow()

    fun getUnitedVon() {
        viewModelScope.launch {
            _allVpn.value = ResultState.Loading
            try {
                val response = repository.getUnitedVpn()
                _allVpn.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allVpn.value = ResultState.Error(e)
            }
        }
    }
}