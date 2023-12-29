package com.nohchiyn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmTranslation

class EntriesAdapter(private val dataList: List<RealmEntry>) :
    RecyclerView.Adapter<EntriesAdapter.EntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.phrases_exp_group, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataList.size

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvContent: TextView = itemView.findViewById(R.id.tvPhrase)
        private val translationsLayout: LinearLayout = itemView.findViewById(R.id.translationsLayout)

        fun bind(item: RealmEntry) {
            tvContent.text = item.Content

            translationsLayout.removeAllViews()
            val inflater = LayoutInflater.from(itemView.context)
            item.Translations.forEach { translation ->
                val translationView = inflater.inflate(R.layout.phrases_exp_child, translationsLayout, false)
                val tvTranslation: TextView = translationView.findViewById(R.id.tvTranslation)
                val tvTranslationNotes: TextView = translationView.findViewById(R.id.tvTranslationNotes)

                tvTranslation.text = translation.Content
                tvTranslationNotes.text = translation.Notes
                translationsLayout.addView(translationView)
            }
        }
    }
}
