package com.gabriel.carapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gabriel.carapp.models.Car

@ExperimentalMaterial3Api
@Composable
fun CarScreen(car: Car) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("${car.brand} ${car.model}") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Car Image
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = "${car.brand} ${car.model} Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Car Brand and Model
            Text(
                text = car.brand,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = car.model,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Car Description
            Text(
                text = car.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Car Features
            Text(
                text = "Destaques: ${car.features}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
