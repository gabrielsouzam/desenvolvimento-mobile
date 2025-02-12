package com.gabriel.authapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gabriel.authapp.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(viewModel: AuthViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Recuperar Senha",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Blue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            label = { Text("Digite seu email", color = Color.Blue) },
            isError = !isEmailValid,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isEmailValid) Color.Blue else Color.Red,
                unfocusedBorderColor = if (isEmailValid) Color.Gray else Color.Red,
                errorBorderColor = Color.Red,
                errorLabelColor = Color.Red,
                cursorColor = Color.Blue
            )
        )

        if (!isEmailValid) {
            Text(
                text = "Por favor, insira um email válido.",
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isEmailValid && email.isNotEmpty()) {
                    viewModel.resetPassword(email) { success ->
                        if (success) {
                            Toast.makeText(context, "Email de recuperação enviado!", Toast.LENGTH_LONG).show()
                            navController.navigate("login")
                        } else {
                            Toast.makeText(context, "Erro ao enviar email", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Insira um email válido", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Enviar Email de Recuperação", fontSize = 16.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Voltar ao Login", fontSize = 16.sp, color = Color.Blue)
        }
    }
}
