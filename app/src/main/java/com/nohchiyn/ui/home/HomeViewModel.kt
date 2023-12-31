package com.nohchiyn.ui.home

import EntryItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nohchiyn.entities.RealmEntry
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

        var results: RealmResults<RealmEntry>;
        if (s.length < 3) {
            results = realm.query<RealmEntry>("RawContents LIKE [c] '${s}*'").find()
        }else {
            results = realm.query<RealmEntry>("(RawContents LIKE [c] '*${s}*') OR SUBQUERY(Translations, \$translation, \$translation.RawContents LIKE [c] '${s}*').@count > 0").find()
        }

        setEntries(results);
    }
}