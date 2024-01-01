package com.nohchiyn.ui.home

import EntryItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmTranslation
import com.nohchiyn.services.RealmService
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmQuery
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.query.find
import java.util.Random


class HomeViewModel : ViewModel() {

    val realm: Realm = RealmService.getInstance();

    private val _entries = MutableLiveData<List<EntryItem>>()
    val entries: LiveData<List<EntryItem>> = _entries

    fun setEntries(realmEntries: List<RealmEntry>) {
        val flatList = mutableListOf<EntryItem>()
        realmEntries.forEach { entry ->
            flatList.add(EntryItem.Entry(entry))
            entry.Translations.forEach { translation ->
                flatList.add(EntryItem.Translation(translation))
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
            realm.query(RealmEntry::class).find()?.let {
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

        // StartsWith for Entry
        val entryStartsWithResults: List<RealmEntry> =
            realm.query<RealmEntry>("RawContents LIKE [c] '${searchText}*' AND Rate > ${fromRate}")
                .find()
                .sortedBy { it.RawContents }

        setEntries(entryStartsWithResults)

        // StartsWith for Translation
        val translationStartsWithResults =
            realm.query<RealmEntry>("SUBQUERY(Translations, \$translation, \$translation.RawContents LIKE [c] '${searchText}*' AND \$translation.Rate > ${fromRate}).@count > 0")
                .find()

        setEntries(entryStartsWithResults + translationStartsWithResults)

        if (searchText.length > 4) {
            val containsResults =
                realm.query<RealmEntry>("(RawContents LIKE [c] '*${searchText}*' AND Rate > ${fromRate}) OR SUBQUERY(Translations, \$translation, \$translation.RawContents LIKE [c] '*${searchText}*' AND \$translation.Rate > ${fromRate}).@count > 0")
                    .find()
                    .filterNot { it in entryStartsWithResults }
                    .filterNot { it in translationStartsWithResults }
                    .sortedBy { it.RawContents }

            setEntries(entryStartsWithResults + translationStartsWithResults + containsResults);
        }

    }
}