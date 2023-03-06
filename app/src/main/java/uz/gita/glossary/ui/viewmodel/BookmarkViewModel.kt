package uz.gita.glossary.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.glossary.model.Word
import uz.gita.glossary.repository.DatabaseRepository
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val bookmarkedWordMutableLiveData = MutableLiveData<List<Word>>()
    val bookmarkedWordLiveData: LiveData<List<Word>> = bookmarkedWordMutableLiveData

    init {
        getBookmarkedWord()
    }

    private fun getBookmarkedWord() = viewModelScope.launch {
        databaseRepository.getBookmarkedWord().collect {
            bookmarkedWordMutableLiveData.value = it
        }
    }


}