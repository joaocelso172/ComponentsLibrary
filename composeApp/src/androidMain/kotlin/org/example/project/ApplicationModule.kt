package org.example.project

import feature.domain.repository.IContactsRepository
import feature.domain.repository.IPermissionRepository
import org.example.project.feature.data.ContactsRepository
import org.example.project.feature.data.PermissionRepository
import org.example.project.feature.domain.usecase.CheckPermissionUseCase
import org.example.project.feature.domain.usecase.GetContactsUseCase
import org.example.project.feature.domain.usecase.RequestPermissionUseCase
import org.example.project.feature.viewmodel.ContactsViewModel
import org.example.project.feature.viewmodel.PermissionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(): Module{
    return module {
        //Component Activity
        single<IContactsRepository> { ContactsRepository(androidContext().contentResolver) }
        single<GetContactsUseCase> { GetContactsUseCase(get()) }
        single<IPermissionRepository> { PermissionRepository() }
        viewModel { ContactsViewModel(get()) }
        //PermissionActivity
        single<CheckPermissionUseCase> { CheckPermissionUseCase(get()) }
        single<RequestPermissionUseCase> { RequestPermissionUseCase(get()) }
        single<RequestPermissionUseCase> { RequestPermissionUseCase(get()) }
        viewModel { PermissionViewModel(get(), get()) }
    }
}