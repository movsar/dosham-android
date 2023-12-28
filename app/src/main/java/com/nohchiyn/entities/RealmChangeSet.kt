package com.nohchiyn.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

@PersistedName("ChangeSet")
open class RealmChangeSet() : RealmObject {

    @PrimaryKey
    var changeSetIndex: Long = 0
    var changeSetId: String? = null
    var userId: String? = null
    var recordId: String? = null
    var recordChanges: String? = null
    var recordType: Int = 0
    var operation: Int = 0
    var createdAt: RealmInstant = RealmInstant.now()

}
