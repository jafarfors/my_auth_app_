package com.example.myauthapp.di

import android.content.Context
import androidx.room.Room
import com.example.myauthapp.data.AuthDatabase
import com.example.myauthapp.data.UserDao
import com.example.myauthapp.data.provideAuthDataBse
import com.example.myauthapp.data.provideUserDao
import com.example.myauthapp.domain.GetLatestUserUseCase
import com.example.myauthapp.domain.SaveUserUseCase
import com.example.myauthapp.presentation.LoginViewModel
import com.example.myauthapp.presentation.ProfileViewModel
import com.example.myauthapp.repository.UserRepository
import com.example.myauthapp.repository.UserRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    single<AuthDatabase> {
        provideAuthDataBse(androidContext().applicationContext as android.app.Application)
    }

    single<UserDao> { provideUserDao(get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }

    singleOf(::SaveUserUseCase)
    singleOf(::GetLatestUserUseCase)

    viewModelOf(::LoginViewModel)
    viewModelOf(::ProfileViewModel)
}