package uz.gita.glossary.repository

import uz.gita.glossary.database.WordDao
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val wordDao: WordDao
) {

    suspend fun getAllWords() = wordDao.getAllWord()

    suspend fun getEngWord(engWord: String) = wordDao.getEngWord(engWord)


}