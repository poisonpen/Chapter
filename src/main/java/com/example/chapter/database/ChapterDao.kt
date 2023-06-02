package com.example.chapter.database

import androidx.room.Dao;
import androidx.room.Query;
import com.example.chapter.Chapter;
import java.util.*;

@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapter")
    suspend fun getChapters(): List<Chapter>

    @Query("SELECT * FROM chapter WHERE id=(:id)")
    suspend fun getChapter(id: UUID): Chapter
}
