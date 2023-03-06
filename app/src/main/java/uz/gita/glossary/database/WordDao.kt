package uz.gita.glossary.database

import android.provider.UserDictionary
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.gita.glossary.model.Word

@Dao
interface WordDao {

    @Query("SELECT *FROM eng_uz")
    fun getAllWord(): Flow<List<Word>>

    @Query("SELECT *FROM eng_uz WHERE en_word like :engWord||'%'")
    fun getEngWord(engWord: String): Flow<List<Word>>

    @Query("SELECT *FROM eng_uz WHERE translation like :uzWord||'%'")
    fun getUzWord(uzWord: String): Flow<List<Word>>

    @Update
    suspend fun updateWord(word: Word)

    @Query("SELECT *FROM eng_uz WHERE countable = \'1\' ")
    fun getHistoryWord(): Flow<List<Word>>

    @Query("SELECT *FROM eng_uz WHERE favourite = 1")
    fun getBookmarkedWord(): Flow<List<Word>>


}