package com.gabriel.crudproducts.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gabriel.crudproducts.model.Item
import com.gabriel.crudproducts.viewmodel.ItemViewModel

@Composable
fun ItemScreen(modifier: Modifier = Modifier, viewModel: ItemViewModel = viewModel()) {
    val items by viewModel.items

    var title by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var description by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Item?>(null) }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Título") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Descrição") },
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                viewModel.addItem(Item(title = title.text, description = description.text))
                title = TextFieldValue("")
                description = TextFieldValue("")
            }, modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Adicionar")
        }

        LazyColumn {
            items(items.size) { index ->
                val item = items[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(Modifier.padding(8.dp)) {
                        Text(text = "Título: ${item.title}")
                        Text(text = "Descrição: ${item.description}")

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(onClick = { viewModel.deleteItem(item.id) }) {
                                Text(text = "Deletar")
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(onClick = {
                                selectedItem = item
                                showDialog = true
                            }) {
                                Text(text = "atualizar")
                            }
                        }


                    }

                }
            }
        }
    }

    if(showDialog) {
        UpdateItemDialog(
            item = selectedItem,
            onDismiss = { showDialog = false },
            onUpdate = { updateItem ->
                viewModel.updateItem(updateItem)
                showDialog = false
            }
        )
    }
}


@Composable
fun UpdateItemDialog(
    modifier: Modifier = Modifier,
    item: Item?,
    onDismiss: () -> Unit,
    onUpdate: (Item) -> Unit
) {
    if(item == null) return

    var title by remember { mutableStateOf(TextFieldValue(item.title)) }
    var description by remember { mutableStateOf(TextFieldValue(item.description)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Editar item") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Título") },
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onUpdate(item.copy(title = title.text, description = description.text))
            }) {
                Text(text = "Salvar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}