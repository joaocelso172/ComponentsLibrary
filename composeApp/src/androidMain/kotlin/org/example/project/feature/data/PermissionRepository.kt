package org.example.project.feature.data

import feature.domain.repository.IPermissionRepository

class PermissionRepository : IPermissionRepository {
    override fun hasPermission(): Boolean = true


    override fun requestPermission() {
    }
}