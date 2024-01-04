package com.nohchiyn.models

data class GraphQLResponse<T>(
    val data: T,
    val errors: List<GraphQLError>?
)
