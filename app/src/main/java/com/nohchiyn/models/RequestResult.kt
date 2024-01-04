package com.nohchiyn.models

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class RequestResult() {
    var success: Boolean = false
        private set

    var serializedData: String? = null

    private var _errorMessage: String = ""
    var errorMessage: String
        get() = _errorMessage
        set(value) {
            _errorMessage = value
            success = value.isBlank()
        }

    constructor(errorMessage: String) : this() {
        this.errorMessage = errorMessage
    }

    companion object {
        inline fun <reified T> getData(requestResult: RequestResult): T {
            if (!requestResult.success || requestResult.serializedData == null) {
                throw Exception("Error:Unexpected_result")
            }

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val adapter = moshi.adapter(T::class.java)

            return adapter.fromJson(requestResult.serializedData)
                ?: throw Exception("Error deserializing data")
        }
    }
}
