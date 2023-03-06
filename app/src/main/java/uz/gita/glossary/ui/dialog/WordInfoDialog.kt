package uz.gita.vocabulary.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.glossary.R
import uz.gita.glossary.databinding.WordInfoDialogBinding
import uz.gita.glossary.model.Word
import uz.gita.glossary.ui.viewmodel.HomeViewModel

@AndroidEntryPoint
class WordInfoDialog : DialogFragment() {
    private var _binding: WordInfoDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = WordInfoDialogBinding.inflate(layoutInflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.word_dialog_bg)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showData()

    }


    private fun showData() {
        val data: Word? = requireArguments().getParcelable("key")
        binding.word.text = data!!.engWord
        binding.wordTranscript.text = data!!.transcript
        binding.wordType.text = data!!.type
        binding.wordTranslation.text = data!!.translation

        binding.star.setOnClickListener {
            if (data.favourite == null) {
                data.favourite = 1

            } else {
                data.favourite = null
            }
            showStar(data.favourite)

        }

        binding.closeBtn.setOnClickListener {
            dialog?.dismiss()
            saveBookmark(data)
        }
        saveHistory(data)
        showStar(data.favourite)


    }



    private fun saveBookmark(word: Word) {
        viewModel.updateWord(word)

    }

    private fun showStar(favourite: Int?) {
        if (favourite == 1) {
            binding.star.setImageResource(R.drawable.full_star)
        } else {
            binding.star.setImageResource(R.drawable.hole_star)
        }

    }


    private fun saveHistory(word: Word) {
        word.countable = "1"
        viewModel.updateWord(word)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}