package com.example.aprenderpalabras.data

object WordRepository {
    val italianWords = listOf(
        Word("Felicità", "Stato di piena soddisfazione.", "Estado de plena satisfacción."),
        Word("Speranza", "Attesa fiduciosa di un bene futuro.", "Espera confiada de un bien futuro."),
        Word("Amore", "Sentimento di vivo affetto verso una persona.", "Sentimiento de vivo afecto hacia una persona.")
    )

    val portugueseWords = listOf(
        Word("Saudade", "Sentimento melancólico devido ao afastamento de uma pessoa, uma coisa ou um lugar.", "Sentimiento melancólico debido al alejamiento de una persona, una cosa o un lugar."),
        Word("Alegria", "Estado de viva satisfação, de vivo contentamento.", "Estado de viva satisfacción, de vivo contentamiento."),
        Word("Amizade", "Sentimento de grande afeição, simpatia, apreço entre pessoas.", "Sentimiento de gran afecto, simpatía, aprecio entre personas.")
    )

    val frenchWords = listOf(
        Word("Joie", "Sentiment de bonheur, de satisfaction vive.", "Sentimiento de felicidad, de satisfacción viva."),
        Word("Espoir", "Attente confiante en l'avenir.", "Espera confiada en el futuro."),
        Word("Amour", "Sentiment d'affection et d'attachement envers quelqu'un.", "Sentimiento de afecto y apego hacia alguien.")
    )
}