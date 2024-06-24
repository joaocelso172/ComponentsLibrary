package feature.domain.repository

interface IPermissionRepository {
    fun hasPermission(): Boolean
    fun requestPermission()
}