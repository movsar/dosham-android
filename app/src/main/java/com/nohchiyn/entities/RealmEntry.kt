package com.nohchiyn.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.*

@RealmClass(name = "Entry")
open class RealmEntry : RealmObject() {

    private var _content: String? = null

    @PrimaryKey
    var entryId: String = ""

    @Ignore
    var sourceId: String? = null
        get() = source?.sourceId

    @Ignore
    var userId: String? = null
        get() = user?.id

    var parentEntryId: String? = null
    var user: RealmUser = RealmUser()
    var source: RealmSource = RealmSource()
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
    var sounds: RealmList<RealmSound> = RealmList()
    var translations: RealmList<RealmTranslation> = RealmList()
}
