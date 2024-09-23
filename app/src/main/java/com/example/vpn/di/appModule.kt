package com.example.vpn.di

import com.example.vpn.domain.repository.Repository
import com.example.vpn.presentation.viewmodel.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single { Repository() }
    single { MainViewModel(get()) }
}