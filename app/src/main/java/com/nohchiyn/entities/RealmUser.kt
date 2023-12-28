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
    var id: String? = null
    var email: String? = null
    var rate: Int = 0
    var imagePath: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var patronymic: String? = null
    var type: Int = 0
    var status: Int = 0
    var createdAt: RealmInstant = RealmInstant.now()
    var updatedAt: RealmInstant = RealmInstant.now()

    var entries: RealmList<RealmEntry> = realmListOf()
    var sounds: RealmList<RealmSound> = realmListOf()
    var sources: RealmList<RealmSource> = realmListOf()
    var translations: RealmList<RealmTranslation> = realmListOf()
}