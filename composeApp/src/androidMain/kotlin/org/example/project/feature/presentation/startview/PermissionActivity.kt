package org.example.project.feature.presentation.startview

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import org.example.project.feature.presentation.componentview.NetworkActivity
import org.example.project.feature.viewmodel.PermissionViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PermissionActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val permissionViewModel: PermissionViewModel by viewModel()

        setContent {
            PermissionScreen(permissionViewModel)

            Button(content = {
                Text("Component Activity")
            }, onClick = {
                startActivity(Intent(this, NetworkActivity::class.java))
            })
        }
    }
}