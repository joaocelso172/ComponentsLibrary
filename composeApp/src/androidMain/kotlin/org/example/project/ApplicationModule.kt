package org.example.project

import feature.domain.IContactsRepository
import org.example.project.domain.ContactsRepository
import org.example.project.feature.viewmodel.ContactsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(): Module{
    return module {
        single<IContactsRepository> { ContactsRepository(androidContext().contentResolver) }
        viewModel { ContactsViewModel(get()) }
    }
}