import android.text.SpannableString
import android.text.style.LeadingMarginSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nohchiyn.R
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmTranslation

sealed class EntryItem {
    data class Entry(
        val entryContent: String,
        val entrySource: String,
        val entryForms: String,
        val translations: List<Translation>
    ) : EntryItem()

    data class Translation(
        val translationContent: String,
        val translationLanguageCode: String,
        val translationNotes: String
    ) : EntryItem()
}

class EntriesAdapter(private val dataList: List<EntryItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ENTRY = 0
        private const val TYPE_TRANSLATION = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is EntryItem.Entry -> TYPE_ENTRY
            is EntryItem.Translation -> TYPE_TRANSLATION
            else -> throw AssertionError()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ENTRY -> EntryViewHolder(
                inflater.inflate(
                    R.layout.phrases_exp_group,
                    parent,
                    false
                )
            )

            TYPE_TRANSLATION -> TranslationViewHolder(
                inflater.inflate(
                    R.layout.phrases_exp_child,
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = dataList[position]) {
            is EntryItem.Entry -> (holder as EntryViewHolder).bind(item)
            is EntryItem.Translation -> (holder as TranslationViewHolder).bind(item)
            else -> throw AssertionError()
        }
    }

    override fun getItemCount(): Int = dataList.size

    class EntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvSource: TextView = view.findViewById(R.id.tvSource)
        private val tvPhrase: TextView = view.findViewById(R.id.tvPhrase)
        private val tvForms: TextView = view.findViewById(R.id.tvForms)

        fun bind(item: EntryItem.Entry) {
            tvSource.text = item.entrySource
            tvPhrase.text = item.entryContent

            if (item.entryForms.length > 0) {
                tvForms.text = item.entryForms
            } else {
                tvForms.visibility = View.GONE
            }
        }
    }

    class TranslationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTranslation: TextView = view.findViewById(R.id.tvTranslation)
        private val tvTranslationNotes: TextView = view.findViewById(R.id.tvTranslationNotes)
        private val tvLanguage: TextView = view.findViewById(R.id.tvExpChildLg)

        fun bind(item: EntryItem.Translation) {
            tvTranslation.text = item.translationContent
            tvLanguage.text = item.translationLanguageCode

            if (item.translationNotes.length > 0) {
                tvTranslationNotes.text = item.translationNotes
                tvTranslationNotes.visibility = View.VISIBLE;
            }
        }
    }
}
