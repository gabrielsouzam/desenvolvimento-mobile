package com.gabriel.startservice

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : ComponentActivity() {
    private val controlChannelId = "Service_control_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ServiceControlScreen(
                onStartClick = {},
                onStopClick = {}
            )
        }
    }

    private fun startBackgroundService() {
        val intent = Intent(this, BackgroundService::class.java)
        startService(intent)

        Toast.makeText(this, "Serviço iniciado", Toast.LENGTH_SHORT).show()

        showControlNotification("Servico iniciado")
    }

    private fun stopBackgroundService() {
        val intent = Intent(this, BackgroundService::class.java)
        startService(intent)

        Toast.makeText(this, "Solicitação para parar o serviço", Toast.LENGTH_SHORT).show()

        showControlNotification("Servico parado")
    }

    private fun showControlNotification(message: String) {
        val notification = NotificationCompat.Builder(this, controlChannelId)
            .setContentTitle("Controle do serviço")
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        NotificationManagerCompat.from(this)
            .notify(System.currentTimeMillis().toInt(), notification)
    }

    private fun createControlNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                controlChannelId,
                "Controle de serviço",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)

            manager?.createNotificationChannel(channel)
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (!isGranted) {
                        Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show()
                    }
                }

            requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }


    }

