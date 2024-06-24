package org.example.project.feature.domain.usecase

import feature.domain.repository.IPermissionRepository

class CheckPermissionUseCase(private val repository: IPermissionRepository) {
    operator fun invoke() : Boolean{
        return repository.hasPermission()
    }
}