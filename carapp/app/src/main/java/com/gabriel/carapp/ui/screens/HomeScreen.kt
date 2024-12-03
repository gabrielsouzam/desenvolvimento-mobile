package com.gabriel.carapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.gabriel.carapp.models.Car
import com.gabriel.carapp.models.carList
import com.gabriel.carapp.ui.components.CarListItem

@Composable
fun HomeScreen(onCarSelected: (Car) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredCars = remember(searchQuery) {
        carList.filter { it.model.contains(searchQuery, ignoreCase = true) }
    }
    Column {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pesquisar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(filteredCars) { car ->
                CarListItem(car, onCarSelected)
            }
        }
    }
}
