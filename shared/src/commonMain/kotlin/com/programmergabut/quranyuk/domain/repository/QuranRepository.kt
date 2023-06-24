package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.local.source.LocalDataSource
import com.programmergabut.quranyuk.data.remote.response.SurahResponse
import com.programmergabut.quranyuk.data.remote.network.QuranApi
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class QuranRepository(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) {

//    fun getAllAgents(): Flow<List<AgentEntity>> {
//        return queries.getAllAgents().asFlow()
//    }

//    suspend fun insertAgent(agents: List<AgentEntity>) {
//        queries.transaction {
//            agents.forEach {
//                queries.insertAgent(
//                    uuid = it.uuid,
//                    backgroundGradientColors = it.backgroundGradientColors,
//                    displayName = it.displayName,
//                    displayIcon = it.displayIcon,
//                    fullPortrait = it.fullPortrait,
//                    background = it.background,
//                    role = it.role,
//                    description = it.description
//                )
//            }
//        }
//    }

}