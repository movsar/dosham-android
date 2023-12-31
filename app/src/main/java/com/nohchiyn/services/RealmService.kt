package com.nohchiyn.services

import android.content.Context
import com.nohchiyn.entities.RealmChangeSet
import com.nohchiyn.entities.RealmEntry
import com.nohchiyn.entities.RealmSound
import com.nohchiyn.entities.RealmSource
import com.nohchiyn.entities.RealmTranslation
import com.nohchiyn.entities.RealmUser
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.Realm

object RealmService {
    private lateinit var realmConfig: RealmConfiguration

    fun init(context: Context) {
        realmConfig = RealmConfiguration.Builder(
            schema = setOf(
                RealmChangeSet::class,
                RealmEntry::class,
                RealmSource::class,
                RealmSound::class,
                RealmUser::class,
                RealmTranslation::class
            )
        )
            .schemaVersion(18)
            .directory(context.filesDir.absolutePath + "/database")
            .name("local.datx")
            .build()
    }

    fun getInstance(): Realm {
        if (!::realmConfig.isInitialized) {
            throw IllegalStateException("RealmService is not initialized. Call init(context) first.")
        }
        return Realm.open(realmConfig)
    }
}
