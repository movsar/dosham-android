package com.nohchiyn.ui.home

import EntriesAdapter
import EntryItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nohchiyn.R
import com.nohchiyn.databinding.FragmentHomeBinding
import com.nohchiyn.entities.RealmChangeSet
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmSound
import com.nohchiyn.entities.RealmSource
import com.nohchiyn.entities.RealmTranslation
import com.nohchiyn.entities.RealmUser
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import java.util.Random

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val recyclerView: RecyclerView = root.findViewById(R.id.rvMain)

        // Setup local database access
        val config = RealmConfiguration.Builder(
            schema = setOf(
                RealmChangeSet::class,
                RealmEntry::class,
                RealmSource::class,
                RealmSound::class,
                RealmUser::class,
                RealmTranslation::class,
            )
        ).schemaVersion(18).name("local.datx").build()

        val realm: Realm = Realm.open(config)

        val realmEntries = getRandomEntries(realm, 50)

        val flatList = mutableListOf<EntryItem>()
        realmEntries.forEach { entry ->
            flatList.add(EntryItem.Entry(entry))
            entry.Translations.forEach { translation ->
                flatList.add(EntryItem.Translation(translation))
            }
        }


        val adapter = EntriesAdapter(flatList)
        recyclerView.adapter = adapter


        return root
    }
    fun getRandomEntries(realm: Realm, numberOfEntries: Int): List<RealmEntry> {
        val results = mutableListOf<RealmEntry>()
        val totalEntries = realm.query(RealmEntry::class).find().count().toInt()

        if (totalEntries == 0 || numberOfEntries <= 0) {
            return results
        }

        val random = Random()
        for (i in 1..numberOfEntries) {
            val randomIndex = random.nextInt(totalEntries)
            realm.query(RealmEntry::class).find()?.let {
                if (it.size > randomIndex) {
                    results.add(it[randomIndex])
                }
            }
        }

        return results.distinct() // To ensure unique entries if needed
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}