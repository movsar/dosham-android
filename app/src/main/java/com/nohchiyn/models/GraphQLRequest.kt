package com.nohchiyn.models
data class GraphQLRequest(
    val query: String,
    val variables: Map<String, Any?>
)