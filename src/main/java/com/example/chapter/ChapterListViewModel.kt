package com.example.chapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class ChapterListViewModel: ViewModel() {
    val chapters = mutableListOf<Chapter>()
    init {
        viewModelScope.launch {
           // delay(5000)
            chapters += loadChapters()
        }
    }
    suspend fun loadChapters(): List<Chapter> {
        val result = mutableListOf<Chapter>()
        // delay(5000)

        for (i in 0 until 100) {
            val chapter = Chapter (
                id = UUID.randomUUID(),
                strangeText = "Entry #$i",
                translationText = "",
                language = "",
                comment = "",
                lastUpdated = Date()
            )
            result += chapter
        }
        return result
    }
}