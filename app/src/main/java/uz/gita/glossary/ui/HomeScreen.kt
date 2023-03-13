package uz.gita.glossary.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import uz.gita.glossary.R
import uz.gita.glossary.adapter.WordRecyclerAdapter
import uz.gita.glossary.databinding.SearchScreenBinding
import uz.gita.glossary.model.Word
import uz.gita.glossary.ui.viewmodel.HomeViewModel
import uz.gita.glossary.utils.hideKeyboard
import uz.gita.vocabulary.ui.dialog.WordInfoDialog

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.search_screen) {
    private val binding: SearchScreenBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: WordRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
        searchWord()
        clickChangeLang()
    }

    private fun initRecycler() {
        Toast.makeText(requireContext(),binding.leftSideText.text.toString(),Toast.LENGTH_SHORT).show()
        adapter = WordRecyclerAdapter(binding.leftSideText.text.toString())
        viewModel.allWordsLiveData.observe(viewLifecycleOwner, Observer {
            wordsObserver(it)
        })
        clickWordItem(adapter!!)
    }


    private fun clickWordItem(adapter: WordRecyclerAdapter) {
        adapter?.onItemClick = {
            hideKeyboard()
            val dialog = WordInfoDialog()
            val bundle = Bundle().apply {
                putParcelable("key", it)
            }
            dialog.arguments = bundle
            dialog.isCancelable = false
            dialog.show(requireActivity().supportFragmentManager, "Dialog")
        }


    }

    private fun searchWord() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    findWord(newText)
                }
                return true
            }

        })


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun clickChangeLang() {
        binding.syncImg.setOnClickListener {
            if (binding.leftSideText.text == "English") {
                binding.leftSideIcon.setImageResource(R.drawable.uzbekistan)
                binding.leftSideText.text = "Uzbek"
                binding.rightSideIcon.setImageResource(R.drawable.united_kingdom)
                binding.rightSideText.text = "English"
            } else {
                binding.leftSideIcon.setImageResource(R.drawable.united_kingdom)
                binding.leftSideText.text = "English"
                binding.rightSideIcon.setImageResource(R.drawable.uzbekistan)
                binding.rightSideText.text = "Uzbek"
            }

            changeLanguage(binding.leftSideText.text.toString())

        }

    }

    private fun changeLanguage(lang: String) {

        val newAdapter = WordRecyclerAdapter(lang)
        viewModel.allWordsLiveData.observe(viewLifecycleOwner, Observer {
            newAdapter.differ.submitList(it)
        })
        clickWordItem(newAdapter)
        binding.recyclerView.swapAdapter(newAdapter, true)


    }


    private fun findWord(word: String) {
        if (binding.leftSideText.text.toString() == "English") {
            viewModel.findEngWord(word)
            viewModel.findWordLiveData.observe(viewLifecycleOwner, Observer {
                val newAdapter = WordRecyclerAdapter(binding.leftSideText.text.toString())
                newAdapter.differ.submitList(it)
                binding.recyclerView.swapAdapter(newAdapter, true)
                clickWordItem(newAdapter)
            })
        } else {
            viewModel.getUzbWord(word)
            viewModel.allWordsLiveData.observe(viewLifecycleOwner, Observer {
                val newAdapter = WordRecyclerAdapter(binding.leftSideText.text.toString())
                newAdapter.differ.submitList(it)
                binding.recyclerView.swapAdapter(newAdapter, true)
                clickWordItem(newAdapter)
            })

        }


    }


    private fun wordsObserver(list: List<Word>) {
        if (list.isNotEmpty()) {
            adapter!!.differ.submitList(list)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext())
        }
    }


}




