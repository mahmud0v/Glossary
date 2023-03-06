package uz.gita.glossary.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.glossary.R
import uz.gita.glossary.adapter.WordRecyclerAdapter
import uz.gita.glossary.databinding.BookmarkScreenBinding
import uz.gita.glossary.ui.viewmodel.BookmarkViewModel
import uz.gita.vocabulary.ui.dialog.WordInfoDialog

@AndroidEntryPoint
class BookmarkScreen : Fragment(R.layout.bookmark_screen) {
    private val binding: BookmarkScreenBinding by viewBinding()
    private val viewModel: BookmarkViewModel by viewModels()
    private var adapter: WordRecyclerAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initBookmarkedWord()
        clickItem()
    }


    private fun initBookmarkedWord() {
        adapter = WordRecyclerAdapter("English")
        viewModel.bookmarkedWordLiveData.observe(viewLifecycleOwner, Observer {
            adapter!!.differ.submitList(it)
        })
        binding.bookmarkRvView.adapter = adapter
        binding.bookmarkRvView.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun clickItem() {
        adapter!!.onItemClick = {
            val dialog = WordInfoDialog()
            val bundle = Bundle().apply {
                putParcelable("key", it)
            }
            dialog.arguments = bundle
            dialog.isCancelable = false
            dialog.show(requireActivity().supportFragmentManager, "Dialog")
        }
    }


}