package com.nohchiyn.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass(name = "Translation")
open class RealmTranslation : RealmObject() {

    private var _content: String? = null

    @PrimaryKey
    var translationId: String? = null
    var entry: Entry = Entry()
    var user: User = User()
    var notes: String? = null
    var rate: Int = 0
    var languageCode: String? = null
    var createdAt: Date = Date()
    var updatedAt: Date = Date()

    var content: String?
        get() = _content
        set(value) {
            _content = value
            rawContents = if (value.isNullOrEmpty()) null else value.toLowerCase()
        }

    var rawContents: String? = null
        private set

    companion object {
//        fun fromModel(dto: TranslationModel): RealmTranslation {
//            val translation = RealmTranslation()
//
//            translation.translationId = dto.translationId
//            translation.entry = RealmEntry.fromModel(dto.entry)
//            translation.user = RealmUser.fromModel(dto.user)
//            translation.content = dto.content
//            translation.notes = dto.notes
//            translation.rate = dto.rate
//            translation.languageCode = dto.languageCode
//            translation.createdAt = dto.createdAt
//            translation.updatedAt = dto.updatedAt
//
//            return translation
//        }
    }
}
