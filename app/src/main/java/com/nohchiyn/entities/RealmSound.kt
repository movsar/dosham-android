package com.nohchiyn.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

@PersistedName("Sound")
open class RealmSound() : RealmObject {

    @PrimaryKey
    var SoundId: String? = null
    var User: RealmUser? = null
    var Entry: RealmEntry? = null
    var FileName: String? = ""

    var CreatedAt: RealmInstant = RealmInstant.now()
    var UpdatedAt: RealmInstant = RealmInstant.now()

    var Rate: Int = 0
}
