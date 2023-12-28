package com.nohchiyn.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

@PersistedName("Sound")
open class RealmSound() : RealmObject {

    @PrimaryKey
    var soundId: String? = null
    var realmUser: RealmUser? = RealmUser()
    var realmEntry: RealmEntry? = RealmEntry()
    var fileName: String = ""

    var createdAt: RealmInstant = RealmInstant.now()
    var updatedAt: RealmInstant = RealmInstant.now()

    var rate: Int = 0
}
