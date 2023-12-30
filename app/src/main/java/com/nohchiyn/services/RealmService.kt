package com.nohchiyn.services

import com.nohchiyn.entities.RealmChangeSet
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmSound
import com.nohchiyn.entities.RealmSource
import com.nohchiyn.entities.RealmTranslation
import com.nohchiyn.entities.RealmUser
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.Realm

object RealmService {
    private val realmConfig: RealmConfiguration by lazy {
        // Configure your Realm instance here
        RealmConfiguration.Builder(
            schema = setOf(
                RealmChangeSet::class,
                RealmEntry::class,
                RealmSource::class,
                RealmSound::class,
                RealmUser::class,
                RealmTranslation::class,
            )
        ).schemaVersion(18).name("local.datx").build()
    }

    fun getInstance(): Realm {
        return Realm.open(realmConfig)
    }
}
