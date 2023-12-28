package com.nohchiyn.entities
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass(name = "Source")
open class RealmSource : RealmObject() {

    @PrimaryKey
    var sourceId: String? = null
    var realmUser: RealmUser? = null
    var name: String = ""
    var notes: String? = null
    var createdAt: Date = Date()
    var updatedAt: Date = Date()
    var entries: RealmList<RealmEntry> = RealmList()

    val userId: String?
        get() = realmUser?.id

}
