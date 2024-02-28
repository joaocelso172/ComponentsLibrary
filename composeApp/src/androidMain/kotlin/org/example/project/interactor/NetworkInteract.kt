package org.example.project.interactor

import android.Manifest
import androidx.activity.ComponentActivity
import commons.domain.Callback
import entity.Friend
import feature.INetworkInteract
import feature.domain.INetworkRepository
import feature.presentation.INetworkView
import feature.presenter.INetworkPresenter
import org.example.project.domain.NetworkRepository
import org.example.project.presenter.NetworkPresenter
import org.example.project.utils.PermissionUtils

class NetworkInteract(view: INetworkView, private val context: ComponentActivity) : INetworkInteract {
    val presenter: INetworkPresenter = NetworkPresenter(view)
    private val repository: INetworkRepository = NetworkRepository(context.contentResolver)

    override fun retrieveContractList() {
        val callback: Callback<MutableList<Friend>> = object : Callback<MutableList<Friend>>{
            override fun onSuccess(result: MutableList<Friend>) {
                presenter.fillNetworkComponent(result)
            }

            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
                presenter.showErrorScreen()
            }

        }
        requestReadContactPermission(callback)
    }

    private fun requestReadContactPermission(callback: Callback<MutableList<Friend>>){
        PermissionUtils.requestPermissionLauncher(context, object : Callback<String>{
            override fun onSuccess(result: String) {
                requestContractList(callback)
            }

            override fun onError(throwable: Throwable) {
                presenter.showErrorScreen()
            }
        }).launch(Manifest.permission.READ_CONTACTS)
    }

    private fun requestContractList(callback: Callback<MutableList<Friend>>){
        runCatching {
            repository.sendContactQuery(callback)
        }.onFailure {
            callback.onError(it)
        }
    }
}