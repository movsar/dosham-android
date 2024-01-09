package com.nohchiyn.ui.home

import EntryItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.services.RealmService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import java.util.Random


class HomeViewModel : ViewModel() {

    val realm: Realm = RealmService.getInstance();

    private val _entries = MutableLiveData<List<EntryItem>>()
    val entries: LiveData<List<EntryItem>> = _entries

    fun setEntries(realmEntries: List<RealmEntry>) {
        val combinedEntries = mutableListOf<RealmEntry>()

        // Filter subEntries that have a ParentEntryId
        val subEntries = realmEntries.filter { it.ParentEntryId != null }

        // Add all entries that are not sub-entries to the combinedEntries list
        combinedEntries.addAll(realmEntries.filter { it.ParentEntryId == null })

        // If there are subEntries, process them
        if (subEntries.isNotEmpty()) {
            // Prepare the IN clause with unique ParentEntryIds
            val parentEntryIds = subEntries.mapNotNull { it.ParentEntryId }.distinct()
            val inStatement =
                parentEntryIds.joinToString(separator = "', '", prefix = "'", postfix = "'")

            // Query to find parent entries
            val query = "EntryId IN {$inStatement}"
            val parents = realm.query(RealmEntry::class, query).find()

            combinedEntries.addAll(parents)
        }

        val flatList = mutableListOf<EntryItem>()

        combinedEntries.filter { e -> e.ParentEntryId == null }.forEach { entry ->
            // Add the entry itself
            val forms = entry.SubEntries.map { it -> it.Content }
                .joinToString(separator = ", ")
            flatList.add(
                EntryItem.Entry(
                    entryContent = entry.Content!!.replaceFirstChar { c->c.titlecaseChar() },
                    entrySource = entry.GetSource(),
                    entryForms = if (forms.isNotEmpty()) "[ $forms ]" else "",
                )
            )

            // Add translations of the entry
            entry.Translations.forEach { translation ->
                flatList.add(
                    EntryItem.Translation(
                        translationContent = translation.Content!!,
                        translationLanguageCode = translation.LanguageCode!!,
                        translationNotes = translation.Notes ?: ""
                    )
                )
            }
        }
        _entries.postValue(flatList)
    }

    fun loadRandomEntries() {
        val realmEntries = getRandomEntries(50)

        setEntries(realmEntries)
    }

    fun getRandomEntries(numberOfEntries: Int): List<RealmEntry> {
        val results = mutableListOf<RealmEntry>()
        val totalEntries = realm.query(RealmEntry::class).find().count().toInt()

        if (totalEntries == 0 || numberOfEntries <= 0) {
            return results
        }

        val random = Random()
        for (i in 1..numberOfEntries) {
            val randomIndex = random.nextInt(totalEntries)
            realm.query(RealmEntry::class, "ParentEntryId = null").find()?.let {
                if (it.size > randomIndex) {
                    results.add(it[randomIndex])
                }
            }
        }

        return results.distinct() // To ensure unique entries if needed
    }

    fun search(s: String) {
        if (s.isBlank()) {
            return;
        }

        var searchText = s.lowercase();
        searchText = searchText.replace('1', 'ӏ')
        searchText = searchText.replace('|', 'ӏ');
        searchText = searchText.trim().replace("[+!\"?^]*", "");

        val fromRate = 0;
        val limitBy = 100;

        val entryStartsWithQuery = "RawContents LIKE [c] '${searchText}*' AND Rate > ${fromRate}"
        val translationStartsWithQuery = "SUBQUERY(Translations, \$translation, \$translation.RawContents LIKE [c] '${searchText}*' AND \$translation.Rate > ${fromRate}).@count > 0 LIMIT (${limitBy})"
        val containsQuery = "(RawContents LIKE [c] '*${searchText}*' AND Rate > ${fromRate}) OR (SUBQUERY(Translations, \$translation, \$translation.RawContents LIKE [c] '*${searchText}*' AND \$translation.Rate > ${fromRate}).@count > 0) LIMIT (${limitBy})"

        val results = when {
            searchText.length < 3 -> {
                // StartsWith for Entry
                realm.query<RealmEntry>("$entryStartsWithQuery LIMIT ($limitBy)").find()
            }
            searchText.length < 6 -> {
                // StartsWith for Translation
                realm.query<RealmEntry>("$entryStartsWithQuery OR $translationStartsWithQuery").find()
            }
            else -> {
                // Contains
                realm.query<RealmEntry>(containsQuery).find()
            }
        }

        // Custom sorting logic
        val sortedResults = results.sortedWith(compareBy<RealmEntry> {
            !it.RawContents!!.startsWith(searchText, ignoreCase = true)
        }.thenBy {
            it.RawContents!!.length
        })

        setEntries(sortedResults)
    }
}