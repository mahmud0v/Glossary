package uz.gita.glossary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.gita.glossary.R
import uz.gita.glossary.model.Word

class WordRecyclerAdapter(private val firstLanguage: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: ((Word) -> Unit)? = null
    private val diffItemCallback = object : DiffUtil.ItemCallback<Word>() {


        override fun areItemsTheSame(oldItem: Word, newItem: Word) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Word, newItem: Word) =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, diffItemCallback)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        if (firstLanguage == "English") {
            val textView1: TextView = holder.itemView.findViewById(R.id.rv_item_text1)
            val textView2: TextView = holder.itemView.findViewById(R.id.rv_item_text2)
            textView1.text = data.engWord
            textView2.text = data.transcript
        } else {
            val textView1: TextView = holder.itemView.findViewById(R.id.rv_item_text1)
            val textView2: TextView = holder.itemView.findViewById(R.id.rv_item_text2)
            textView1.text = data.translation
            textView2.text = data.engWord
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(data)
        }


    }

    override fun getItemCount() = differ.currentList.size


}