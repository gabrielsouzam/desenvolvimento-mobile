package com.gabriel.investorapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gabriel.investorapp.R
import com.gabriel.investorapp.model.Investimento
import com.gabriel.investorapp.ui.theme.Blue
import com.gabriel.investorapp.viewmodel.InvestimentoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvestidorScreen(modifier: Modifier = Modifier, viewModel: InvestimentoViewModel) {
    val investimentos by viewModel.investimentos.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text("Investor App")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Blue,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) {paddingValues ->
        Column(
            modifier.fillMaxSize().padding(paddingValues)
        ) {
            ListaInvestimentos(investimentos)

            snackbarMessage?.let {message ->
                LaunchedEffect(message) {
                    snackbarHostState.showSnackbar(message)
                    snackbarMessage = null
                }
            }
        }
    }

}

@Composable
fun ListaInvestimentos(investimentos: List<Investimento>, modifier: Modifier = Modifier) {
    if (investimentos.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Nenhum investimento encontrado.")
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ){
            items(investimentos) {investimento ->
                InvestimentoItem(investimento)
            }
        }
    }
}

@Composable
fun InvestimentoItem(investimento: Investimento, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)


    ){
        Row (
            modifier = modifier.fillMaxWidth().padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Ícone",
                Modifier.size(40.dp)
            )

            Spacer(Modifier.width(16.dp))

            Column (
                Modifier.weight(1f)
            ){
                Text(text = investimento.nome)
                Text(text = "Valor: R$ ${investimento.valor}")

            }
        }
    }
}