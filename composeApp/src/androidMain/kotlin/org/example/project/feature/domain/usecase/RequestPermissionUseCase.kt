package org.example.project.feature.domain.usecase

import feature.domain.repository.IPermissionRepository

class RequestPermissionUseCase(private val repository: IPermissionRepository) {
    operator fun invoke() {
        return repository.requestPermission()
    }
}