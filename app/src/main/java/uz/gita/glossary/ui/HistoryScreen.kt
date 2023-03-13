package uz.gita.glossary.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.glossary.R
import uz.gita.glossary.adapter.WordRecyclerAdapter
import uz.gita.glossary.databinding.HistoryScreenBinding
import uz.gita.glossary.ui.viewmodel.HistoryViewModel
import uz.gita.glossary.utils.hideKeyboard
import uz.gita.vocabulary.ui.dialog.WordInfoDialog

@AndroidEntryPoint
class HistoryScreen : Fragment(R.layout.history_screen) {
    private val binding: HistoryScreenBinding by viewBinding()
    private val viewModel: HistoryViewModel by viewModels()
    private var adapter: WordRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadData()
        clickItem()
    }


    private fun loadData() {
        adapter = WordRecyclerAdapter("English")
        viewModel.historyWordLiveData.observe(viewLifecycleOwner, Observer {
            adapter!!.differ.submitList(it)
        })
        binding.historyRvView.adapter = adapter
        binding.historyRvView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun clickItem() {
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


}