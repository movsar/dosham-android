package com.nohchiyn.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass(name = "Sound")
open class RealmSound : RealmObject() {

    @PrimaryKey
    var soundId: String? = null
    var user: User = User()
    var entry: Entry = Entry()
    var fileName: String = ""

    var createdAt: Date = Date()
    var updatedAt: Date = Date()

    var rate: Int = 0
}
