package com.gabriel.lifecycledemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gabriel.lifecycledemo.ui.theme.LifecycleDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifecycleDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        Log.d("LifecycleDemo", "onCreate foi chamado")
        Toast.makeText(this, "onCreate foi chamado", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()

        Log.d("LifecycleDemo", "onStart foi chamado")
        Toast.makeText(this, "onStart foi chamado", Toast.LENGTH_SHORT).show()

    }

    override fun onResume() {
        super.onResume()

        Log.d("LifecycleDemo", "onResume foi chamado")
        Toast.makeText(this, "onResume foi chamado", Toast.LENGTH_SHORT).show()

    }

    override fun onPause() {
        super.onPause()

        Log.d("LifecycleDemo", "onPause foi chamado")
        Toast.makeText(this, "onPause foi chamado", Toast.LENGTH_SHORT).show()

    }

    override fun onStop() {
        super.onStop()

        Log.d("LifecycleDemo", "onStop foi chamado")
        Toast.makeText(this, "onStop foi chamado", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("LifecycleDemo", "onDestroy foi chamado")
        Toast.makeText(this, "onDestroy foi chamado", Toast.LENGTH_SHORT).show()

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LifecycleDemoTheme {
        Greeting("Android")
    }
}