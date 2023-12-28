package com.nohchiyn.entities
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass(name = "ChangeSet")
open class RealmChangeSet : RealmObject() {

    @PrimaryKey
    var changeSetIndex: Long = 0
    var changeSetId: String? = null
    var userId: String? = null
    var recordId: String? = null
    var recordChanges: String? = null
    var recordType: Int = 0
    var operation: Int = 0
    var createdAt: Date = Date()

}
