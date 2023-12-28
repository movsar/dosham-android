package com.nohchiyn.entities

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass(name = "User")
open class RealmUser : RealmObject() {

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
    var createdAt: Date = Date()
    var updatedAt: Date = Date()

    var entries: RealmList<RealmEntry> = RealmList()
    var sounds: RealmList<RealmSound> = RealmList()
    var sources: RealmList<RealmSource> = RealmList()
    var translations: RealmList<RealmTranslation> = RealmList()
}