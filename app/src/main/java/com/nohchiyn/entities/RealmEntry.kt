package com.nohchiyn.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey

@PersistedName("Entry")
open class RealmEntry() : RealmObject {
    private var _content: String? = null

    @PrimaryKey
    var entryId: String = ""

    var parentEntryId: String? = null
    var user: RealmUser? = RealmUser()
    var source: RealmSource? = RealmSource()
    var type: Int = 0
    var subtype: Int = 0
    var rate: Int = 0

    var content: String?
        get() = _content
        set(value) {
            _content = value
            rawContents = if (value.isNullOrEmpty()) null else value.toLowerCase()
        }

    var rawContents: String? = null
        private set

    var details: String? = null
    var createdAt: Long = 0
    var updatedAt: Long = 0
    var sounds: RealmList<RealmSound> = realmListOf()
    var translations: RealmList<RealmTranslation> = realmListOf()
}
