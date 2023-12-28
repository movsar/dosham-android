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
    var SourceId: String? = null
    var User: RealmUser? = null
    var Name: String? = ""
    var Notes: String? = null
    var CreatedAt: RealmInstant = RealmInstant.now()
    var UpdatedAt: RealmInstant = RealmInstant.now()
    var Entries: RealmList<RealmEntry> = realmListOf()

    val UserId: String?
        get() = User?.Id

}
