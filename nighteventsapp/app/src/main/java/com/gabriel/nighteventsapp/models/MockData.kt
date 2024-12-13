package com.gabriel.nighteventsapp.models

import androidx.compose.runtime.mutableStateOf
import com.gabriel.nighteventsapp.R

val eventList = listOf(
    Event(
        id = 1,
        title = "Conferência de Tecnologia 2024",
        description = "Tendências em tecnologia.",
        date = "2024-12-15",
        location = "Parque Tecnológico",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img1
    ),
    Event(
        id = 2,
        title = "Hackathon Global",
        description = "Desafios e inovação em desenvolvimento de software.",
        date = "2024-11-20",
        location = "Centro de Convenções Digital",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img2
    ),

    Event(
        id = 3,
        title = "Expo IA 2024",
        description = "O futuro da Inteligência Artificial e suas aplicações.",
        date = "2024-10-30",
        location = "Auditório Futurista",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img3
    ),

    Event(
        id = 4,
        title = "Workshop de Design UX",
        description = "Aprenda as melhores práticas em design de experiência do usuário.",
        date = "2024-09-15",
        location = "Laboratório de Inovação",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img4
    ),

    Event(
        id = 5,
        title = "Encontro de Desenvolvedores Kotlin",
        description = "Explorando recursos avançados e novas tendências no Kotlin.",
        date = "2024-08-10",
        location = "Universidade Tecnológica",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img5
    ),
)
