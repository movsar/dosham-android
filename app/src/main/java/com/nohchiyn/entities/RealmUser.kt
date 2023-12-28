package com.nohchiyn.entities

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

@PersistedName("User")
open class RealmUser() : RealmObject {
    @PrimaryKey
    var Id: String? = null
    var Email: String? = null
    var Rate: Int = 0
    var ImagePath: String? = null
    var FirstName: String? = null
    var LastName: String? = null
    var Patronymic: String? = null
    var Type: Int = 0
    var Status: Int = 0
    var CreatedAt: RealmInstant = RealmInstant.now()
    var UpdatedAt: RealmInstant = RealmInstant.now()

    var Entries: RealmList<RealmEntry> = realmListOf()
    var Sounds: RealmList<RealmSound> = realmListOf()
    var Sources: RealmList<RealmSource> = realmListOf()
    var Translations: RealmList<RealmTranslation> = realmListOf()
}