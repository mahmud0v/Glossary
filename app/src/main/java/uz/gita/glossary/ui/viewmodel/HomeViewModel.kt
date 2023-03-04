package uz.gita.glossary.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.gita.glossary.model.Word
import uz.gita.glossary.repository.DatabaseRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private var allWordsMutableLiveData = MutableLiveData<List<Word>>()
    val allWordsLiveData = allWordsMutableLiveData

    private var engWordMutableLiveData = MutableLiveData<Word>()
    val engWordLiveData = engWordMutableLiveData

    init {
        getAllWords()
    }

    private fun getAllWords() = viewModelScope.launch {
        databaseRepository.getAllWords().collect {
            allWordsMutableLiveData.value = it
            Log.d("TTT","${it.size}")
        }
    }

    fun getEngWord(engWord: String) = viewModelScope.launch {
        databaseRepository.getEngWord(engWord).collect {
            engWordMutableLiveData.value = it
        }
    }



}