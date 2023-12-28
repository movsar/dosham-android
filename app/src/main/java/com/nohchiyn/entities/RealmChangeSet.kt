package com.nohchiyn.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

@PersistedName("ChangeSet")

open class RealmChangeSet() : RealmObject {
    @PrimaryKey
    var ChangeSetIndex: Long = 0
    var ChangeSetId: String? = null
    var UserId: String? = null
    var RecordId: String? = null
    var RecordChanges: String? = null
    var RecordType: Int = 0
    var Operation: Int = 0
    var CreatedAt: RealmInstant = RealmInstant.now()

}
