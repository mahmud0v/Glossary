package uz.gita.glossary.repository

import uz.gita.glossary.database.WordDao
import uz.gita.glossary.model.Word
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val wordDao: WordDao
) {

    fun getAllWords() = wordDao.getAllWord()

    fun getEngWord(engWord: String) = wordDao.getEngWord(engWord)

    suspend fun updateWord(word: Word) = wordDao.updateWord(word)

    fun getHistoryWord() = wordDao.getHistoryWord()

    fun getBookmarkedWord() = wordDao.getBookmarkedWord()

    fun getUzbWord(uzbWord: String) = wordDao.getUzWord(uzbWord)


}