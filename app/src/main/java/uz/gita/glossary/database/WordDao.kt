package uz.gita.glossary.database

import android.provider.UserDictionary
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.gita.glossary.model.Word

@Dao
interface WordDao {

    @Query("SELECT *FROM eng_uz")
    fun getAllWord(): Flow<List<Word>>

    @Query("SELECT *FROM eng_uz WHERE en_word =:engWord")
    fun getEngWord(engWord: String): Flow<Word>



}