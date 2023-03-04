package uz.gita.glossary.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.glossary.R
import uz.gita.glossary.adapter.WordRecyclerAdapter
import uz.gita.glossary.databinding.SearchScreenBinding
import uz.gita.glossary.model.Word
import uz.gita.glossary.ui.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.search_screen) {
    private val binding: SearchScreenBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: WordRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
    }

    private fun initRecycler() {
        adapter = WordRecyclerAdapter()
        viewModel.allWordsLiveData.observe(viewLifecycleOwner, Observer {
            wordsObserver(it)
        })
    }

    private fun wordsObserver(list: List<Word>) {
        Toast.makeText(requireContext(),"${list.size}",Toast.LENGTH_SHORT).show()
        if (list.isNotEmpty()) {
            adapter!!.differ.submitList(list)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }


}