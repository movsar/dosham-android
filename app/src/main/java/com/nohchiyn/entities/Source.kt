package com.nohchiyn.entities
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass(name = "Source")
open class Source : RealmObject() {

    @PrimaryKey
    var sourceId: String? = null
    var user: User? = null
    var name: String = ""
    var notes: String? = null
    var createdAt: Date = Date()
    var updatedAt: Date = Date()
    var entries: RealmList<Entry> = RealmList()

    val userId: String?
        get() = user?.id

}
