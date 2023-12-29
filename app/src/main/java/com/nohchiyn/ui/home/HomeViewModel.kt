package com.nohchiyn.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nohchiyn.EntriesAdapter
import com.nohchiyn.entities.RealmChangeSet
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmSound
import com.nohchiyn.entities.RealmSource
import com.nohchiyn.entities.RealmTranslation
import com.nohchiyn.entities.RealmUser
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun loadEntries(){

    }
}