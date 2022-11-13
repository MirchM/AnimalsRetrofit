package com.mmisoft.animalsretrofit.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mmisoft.animalsretrofit.data.api.model.Animal
import com.mmisoft.animalsretrofit.ui.theme.Shapes

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnimalListItem(
    modifier: Modifier,
    animal: Animal,
    onCLick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onCLick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // The image of the animal loaded with glide
        GlideImage(
            modifier = Modifier
                .weight(1f)
                .clip(CircleShape),
            contentScale = ContentScale.Inside,
            model = animal.imageLink,
            contentDescription = "${animal.name} photo"
        )
        // The animal's name
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(3f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = animal.name)
            Text(text = animal.latinName)
        }
    }
}