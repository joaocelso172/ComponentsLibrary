package org.example.project.feature.domain.usecase

import feature.domain.repository.IContactsRepository
import org.example.project.feature.data.ContactsRepository

class GetContactsUseCase(private val repository: IContactsRepository) {
    operator fun invoke() = repository.getContacts()

}