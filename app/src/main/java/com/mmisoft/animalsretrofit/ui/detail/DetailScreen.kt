package com.mmisoft.animalsretrofit.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mmisoft.animalsretrofit.data.api.model.Animal
import com.mmisoft.animalsretrofit.ui.components.DetailRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalGlideComposeApi::class)
@Destination
@Composable
fun DetailScreen(
    // mandatory for navigation
    id: Int,
    animal: Animal,
    navigator: DestinationsNavigator
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = animal.name)
        },
        navigationIcon = {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Navigate back Icon")
            }
        })
    }) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = animal.imageLink,
                contentDescription = "${animal.name} photo",
                contentScale = ContentScale.Inside
            )
            DetailRow(modifier = Modifier, text1 = "Latin Name:", text2 = animal.latinName)
            DetailRow(modifier = Modifier, text1 = "Type:", text2 = animal.animalType)
            DetailRow(modifier = Modifier, text1 = "Diet:", text2 = animal.diet)
            DetailRow(modifier = Modifier, text1 = "Habitat:", text2 = animal.habitat)
            DetailRow(modifier = Modifier, text1 = "Lifespan:", text2 = "${animal.lifespan} years")
        }
    }
}