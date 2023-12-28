package com.nohchiyn.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

@PersistedName("Translation")
open class RealmTranslation() : RealmObject {
    @PrimaryKey
    var TranslationId: String? = null
    var Entry: RealmEntry? = null
    var User: RealmUser? = null
    var Notes: String? = null
    var Rate: Int = 0
    var LanguageCode: String? = null
    var CreatedAt: RealmInstant = RealmInstant.now()
    var UpdatedAt: RealmInstant = RealmInstant.now()

    var Content: String? = ""
    var RawContents: String? = null
        private set

}
