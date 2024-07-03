package org.example.project.feature.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.example.project.feature.ui.screens.ComponentsCatalogScreen
import org.example.project.feature.viewmodel.ComponentsCatalogViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComponentsCatalogActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val componentViewModel: ComponentsCatalogViewModel by viewModel()


        setContent {
            ComponentsCatalogScreen(componentViewModel)
        }
    }

}