package com.gabriel.postapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabriel.postapp.navigation.PostAppNavGraph
import com.gabriel.postapp.navigation.PostAppScreen
import com.gabriel.postapp.ui.theme.Blue
import com.gabriel.postapp.ui.theme.PostAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostAppTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.mobile_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(end = 8.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Mobile posts")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Blue,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(PostAppScreen.User.route) }

    Scaffold(
        topBar = { MainScreenTopBar() },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == PostAppScreen.User.route,
                    onClick = { selectedTab = PostAppScreen.User.route },
                    label = { Text("Usuários") },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuários") }
                )
                NavigationBarItem(
                    selected = selectedTab == PostAppScreen.Post.route,
                    onClick = { selectedTab = PostAppScreen.Post.route },
                    label = { Text("Posts") },
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Posts") }
                )
            }
        }
    ) { innerPadding ->
        PostAppNavGraph(
            modifier = Modifier.padding(innerPadding),
            startDestination = selectedTab
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}