package uz.gita.glossary.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.glossary.model.Word
import uz.gita.glossary.repository.DatabaseRepository
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val historyWordMutableLiveData = MutableLiveData<List<Word>>()
    val historyWordLiveData = historyWordMutableLiveData

    init {
        getHistoryWord()
    }

    private fun getHistoryWord() = viewModelScope.launch {
        databaseRepository.getHistoryWord().collect {
            historyWordMutableLiveData.value = it
        }
    }

}