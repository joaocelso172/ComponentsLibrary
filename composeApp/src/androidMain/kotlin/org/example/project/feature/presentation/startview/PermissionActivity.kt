package org.example.project.feature.presentation.startview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.example.project.feature.viewmodel.PermissionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PermissionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val permissionViewModel: PermissionViewModel by viewModel()

        setContent {
            PermissionScreen(permissionViewModel)
        }
    }
}