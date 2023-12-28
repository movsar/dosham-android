package com.nohchiyn.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey

@PersistedName("Entry")
open class RealmEntry() : RealmObject {
    @PrimaryKey
    var EntryId: String? = ""
    var ParentEntryId: String? = null
    var User: RealmUser? = null
    var Source: RealmSource? = null
    var Type: Int = 0
    var Subtype: Int = 0
    var Rate: Int = 0

    var Content: String? = ""

    var RawContents: String? = null
        private set

    var Details: String? = null
    var CreatedAt: Long = 0
    var UpdatedAt: Long = 0
    var Sounds: RealmList<RealmSound> = realmListOf()
    var Translations: RealmList<RealmTranslation> = realmListOf()
}
