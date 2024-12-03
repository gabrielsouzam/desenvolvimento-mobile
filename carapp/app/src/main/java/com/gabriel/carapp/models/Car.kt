package com.gabriel.carapp.models

import com.gabriel.carapp.R

data class Car(
    val id: Int,
    val brand: String,
    val model: String,
    val imageRes: Int,
    val description: String,
    val features: String,
    var isFavorite: Boolean = false
)

val carList = listOf(
    Car(
        id = 1,
        brand = "Toyota",
        model = "Corolla",
        imageRes = R.drawable.toyota_corolla,
        description = "O Toyota Corolla é um dos sedãs mais vendidos no mundo, conhecido por sua confiabilidade.",
        features = "Oferece ótima economia de combustível e diversas tecnologias de segurança."
    ),
    Car(
        id = 2,
        brand = "Tesla",
        model = "Model 3",
        imageRes = R.drawable.tesla_model3,
        description = "O Tesla Model 3 é um sedã elétrico de alto desempenho e design moderno.",
        features = "Autonomia de até 500 km e recursos avançados de direção autônoma."
    ),
    Car(
        id = 3,
        brand = "Ford",
        model = "Mustang",
        imageRes = R.drawable.ford_mustang,
        description = "O Ford Mustang é um ícone entre os muscle cars americanos.",
        features = "Potência impressionante com opções de motor V8 e design esportivo clássico."
    ),
    Car(
        id = 4,
        brand = "Volkswagen",
        model = "Golf GTI",
        imageRes = R.drawable.volkswagen_golf_gti,
        description = "O Golf GTI combina desempenho esportivo com o conforto de um hatch premium.",
        features = "Motor turbo eficiente e suspensão ajustada para alta performance."
    ),
    Car(
        id = 5,
        brand = "BMW",
        model = "X5",
        imageRes = R.drawable.bmw_x5,
        description = "O BMW X5 é um SUV de luxo que oferece potência e conforto.",
        features = "Tecnologias avançadas e uma experiência de direção refinada."
    )
)
