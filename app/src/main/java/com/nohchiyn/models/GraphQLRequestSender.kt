package com.nohchiyn.models

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException
import java.lang.reflect.ParameterizedType

class GraphQLRequestSender(private val baseUrl: String) {
    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    suspend fun <T> sendRequestAsync(
        requestData: GraphQLRequest,
        responseType: Class<T>
    ): GraphQLResponse<T> {
        val json = moshi.adapter(GraphQLRequest::class.java).toJson(requestData)
        val requestBody = json.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url(baseUrl)
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val responseBody = response.body?.string()
                if (responseBody.isNullOrEmpty()) {
                    throw IOException("Response body is null or empty")
                }

                val type: ParameterizedType =
                    Types.newParameterizedType(GraphQLResponse::class.java, responseType)
                val adapter = moshi.adapter<GraphQLResponse<T>>(type)

                adapter.fromJson(responseBody) ?: throw IOException("Error parsing response")
            }
        }
    }

    data class GraphQLLocation(
        val line: Int,
        val column: Int
    )
}