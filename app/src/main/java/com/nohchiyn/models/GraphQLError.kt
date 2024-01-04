package com.nohchiyn.models

data class GraphQLError(
    val message: String,
    val locations: List<GraphQLRequestSender.GraphQLLocation>?,
    val path: List<String>?,
    val extensions: Map<String, Any?>?
)