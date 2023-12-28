package com.nohchiyn.entities
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import io.realm.kotlin.types.annotations.PersistedName
import java.util.*

@PersistedName("Source")
open class RealmSource() : RealmObject{

    @PrimaryKey
    var sourceId: String? = null
    var realmUser: RealmUser? = null
    var name: String = ""
    var notes: String? = null
    var createdAt: RealmInstant = RealmInstant.now()
    var updatedAt: RealmInstant = RealmInstant.now()
    var entries: RealmList<RealmEntry> = realmListOf()

    val userId: String?
        get() = realmUser?.id

}
