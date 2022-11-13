package com.mmisoft.animalsretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mmisoft.animalsretrofit.ui.NavGraphs
import com.mmisoft.animalsretrofit.ui.theme.AnimalsRetrofitTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalsRetrofitTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}