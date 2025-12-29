package com.example.aprenderpalabras.data

object LearnedWordsRepository {
    private val learnedWords = mutableListOf<Word>()

    fun addLearnedWord(word: Word) {
        if (!learnedWords.contains(word)) {
            learnedWords.add(word)
        }
    }

    fun getLearnedWords(): List<Word> {
        return learnedWords
    }
}