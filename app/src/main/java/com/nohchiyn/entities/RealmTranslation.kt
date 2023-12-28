package com.nohchiyn.entities

import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

@PersistedName("Translation")
open class RealmTranslation() : RealmObject {

    private var _content: String? = null

    @PrimaryKey
    var translationId: String? = null
    var realmEntry: RealmEntry? = RealmEntry()
    var realmUser: RealmUser? = RealmUser()
    var notes: String? = null
    var rate: Int = 0
    var languageCode: String? = null
    var createdAt: RealmInstant = RealmInstant.now()
    var updatedAt: RealmInstant = RealmInstant.now()

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
