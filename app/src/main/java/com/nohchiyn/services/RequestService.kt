package com.nohchiyn.services

import com.nohchiyn.models.GraphQLRequest
import com.nohchiyn.models.GraphQLRequestSender
import com.nohchiyn.models.RequestResult

class RequestService(private val graphQLRequestSender: GraphQLRequestSender) {

//    suspend fun addSoundAsync(pronunciation: PronunciationDto): RequestResult {
//        val operation = "addSound"
//        val request = GraphQLRequest(
//            query = """
//                mutation $operation($pronunciation: PronunciationDto!) {
//                    $operation(pronunciation: $pronunciation) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("pronunciation" to pronunciation)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, operation)
//        return response.data
//    }

//    suspend fun updatePasswordAsync(email: String, token: String, newPassword: String): RequestResult {
//        val request = GraphQLRequest(
//            query = """
//                mutation($email: String!, $token: String!, $newPassword: String!) {
//                    updatePassword(email: $email, token: $token, newPassword: $newPassword) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("email" to email, "token" to token, "newPassword" to newPassword)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, "updatePassword")
//        return response.data
//    }
//
//    suspend fun confirmUserAsync(token: String): RequestResult {
//        val request = GraphQLRequest(
//            query = """
//                mutation($token: String!) {
//                    confirmEmail(token: $token) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("token" to token)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, "confirmEmail")
//        val data = response.data
//        if (!data.success) {
//            throw Exception(data.errorMessage)
//        }
//        return data
//    }
//
//    suspend fun logInEmailPasswordAsync(email: String, password: String): RequestResult {
//        val request = GraphQLRequest(
//            query = """
//                mutation($email: String!, $password: String!) {
//                    loginEmailPassword(email: $email, password: $password) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("email" to email, "password" to password)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, "loginEmailPassword")
//        return response.data
//    }
//
//    suspend fun registerUserAsync(email: String, password: String): RequestResult {
//        val request = GraphQLRequest(
//            query = """
//                mutation($email: String!, $password: String!) {
//                    registerUser(email: $email, password: $password) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("email" to email, "password" to password)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, "registerUser")
//        return response.data
//    }
//
//    suspend fun refreshTokens(accessToken: String, refreshToken: String): RequestResult {
//        val request = GraphQLRequest(
//            query = """
//                mutation($accessToken: String!, $refreshToken: String!) {
//                    refreshTokens(accessToken: $accessToken, refreshToken: $refreshToken) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("accessToken" to accessToken, "refreshToken" to refreshToken)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, "refreshTokens")
//        return response.data
//    }
//
//    suspend fun passwordResetRequestAsync(email: String): RequestResult {
//        val request = GraphQLRequest(
//            query = """
//                mutation($email: String!) {
//                    passwordReset(email: $email) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("email" to email)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, "passwordReset")
//        return response.data
//    }
//
//    suspend fun takeLastAsync(recordType: RecordType, count: Int): RequestResult {
//        val operation = "takeLast"
//        val request = GraphQLRequest(
//            query = """
//                query $operation($recordTypeName: String!, $count: Int!) {
//                    $operation(recordTypeName: $recordTypeName, count: $count) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("recordTypeName" to recordType.toString(), "count" to count)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, operation)
//        return response.data
//    }
//
//    suspend fun takeAsync(recordType: RecordType, offset: Int, limit: Int): RequestResult {
//        val operation = "take"
//        val request = GraphQLRequest(
//            query = """
//                query $operation($recordTypeName: String!, $offset: Int!, $limit: Int!) {
//                    $operation(recordTypeName: $recordTypeName, offset: $offset, limit: $limit) {
//                        success
//                        errorMessage
//                        serializedData
//                    }
//                }
//            """,
//            variables = mapOf("recordTypeName" to recordType.toString(), "offset" to offset, "limit" to limit)
//        )
//        val response = graphQLRequestSender.sendRequestAsync<RequestResult>(request, operation)
//        return response.data
//    }

    // ... additional methods continue ...
}
