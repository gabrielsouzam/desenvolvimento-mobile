package com.gabriel.postapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.gabriel.postapp.viewmodel.PostViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gabriel.postapp.data.models.Post
import com.gabriel.postapp.ui.theme.Blue

@Composable
fun PostScreen(modifier: Modifier = Modifier, viewModel: PostViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var editingPost by remember { mutableStateOf<Post?>(null) }
    var context = LocalContext.current

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.fetchPosts()
        isLoading = false
    }

    Column(modifier = modifier.padding(16.dp).fillMaxSize()) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blue,
                focusedLabelColor = Color(0xFF00369A1)
            ),

        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Conteúdo") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Blue,
                focusedLabelColor = Blue
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                isLoading = true
                viewModel.createPost(title, content, onSuccess = {
                    Toast.makeText(context, "Post criado com sucesso", Toast.LENGTH_SHORT).show()
                    isLoading = false
                }, onError = {
                    Toast.makeText(context, "Erro ao criar Post!", Toast.LENGTH_SHORT).show()
                    isLoading = false
                })
                title = " "
                content = " "
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Color.White
            )
        ) {
            Text("Criar Post")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn {
                items(viewModel.posts) { post ->
                    PostItem(
                        post = post,
                        onDelete = { viewModel.deletePost(it) },
                        onEdit = { editingPost = it }
                    )
                }
            }
        }
        if (editingPost != null) {
            AlertDialog(
                onDismissRequest = { editingPost = null },
                title = { Text(text = "Editar Post") },
                text = {
                    Column {
                        TextField(
                            value = editingPost!!.title,
                            onValueChange = { newTitle ->
                                editingPost =
                                    editingPost!!.copy(title = newTitle)
                            },
                            label = { Text("Título") },
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = editingPost!!.content,
                            onValueChange = { newContent ->
                                editingPost =
                                    editingPost!!.copy(content = newContent)
                            },
                            label = { Text(text = "Conteúdo") }
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.updatePost(
                                editingPost!!.id,
                                editingPost!!.title,
                                editingPost!!.content
                            )
                            editingPost = null
                        }
                    ) {
                        Text(text = "Confirmar")
                    }
                },

                dismissButton = {
                    TextButton(onClick = { editingPost = null }) {
                        Text(text = "cancelar")
                    }
                }
            )


        }
    }
}