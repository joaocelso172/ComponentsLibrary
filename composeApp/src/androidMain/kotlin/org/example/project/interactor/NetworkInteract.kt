package org.example.project.interactor

import android.Manifest
import androidx.activity.ComponentActivity
import commons.domain.Callback
import entity.Contacts
import feature.INetworkInteract
import feature.domain.IContactsRepository
import feature.presentation.INetworkView
import feature.presenter.INetworkPresenter
import org.example.project.domain.ContactsRepository
import org.example.project.feature.viewmodel.ContactsViewModel
import org.example.project.presenter.NetworkPresenter
import org.example.project.utils.PermissionUtils

@Deprecated( message = "The currently pattern is MVVM",
    replaceWith = ReplaceWith("Use ContactsViewModel instead"),
    level = DeprecationLevel.WARNING)
class NetworkInteract(view: INetworkView, private val context: ComponentActivity) : INetworkInteract {
    val presenter: INetworkPresenter = NetworkPresenter(view)
    private val repository: IContactsRepository = ContactsRepository(context.contentResolver)

    override fun retrieveContractList() {
        val callback: Callback<List<Contacts>> = object : Callback<List<Contacts>>{
            override fun onSuccess(result: List<Contacts>) {
                presenter.fillNetworkComponent(result)
            }

            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
                presenter.showErrorScreen()
            }

        }
        requestReadContactPermission(callback)
    }

    private fun requestReadContactPermission(callback: Callback<List<Contacts>>){
        PermissionUtils.requestPermissionLauncher(context, object : Callback<String>{
            override fun onSuccess(result: String) {
                requestContractList(callback)
            }

            override fun onError(throwable: Throwable) {
                presenter.showErrorScreen()
            }
        }).launch(Manifest.permission.READ_CONTACTS)
    }

    private fun requestContractList(callback: Callback<List<Contacts>>){
        runCatching {
            repository.sendContactQuery(callback)
        }.onFailure {
            callback.onError(it)
        }
    }
}