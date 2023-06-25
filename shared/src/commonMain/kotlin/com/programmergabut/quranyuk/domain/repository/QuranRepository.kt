package com.programmergabut.quranyuk.domain.repository

import com.programmergabut.quranyuk.data.local.source.LocalDataSource
import com.programmergabut.quranyuk.data.remote.response.AllSurahResponse
import com.programmergabut.quranyuk.data.remote.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class QuranRepository(
    private val remote: RemoteDataSource,
    //private val local: LocalDataSource
) {

    suspend fun fetchAllSurah(): AllSurahResponse {
        return remote.fetchAllSurah()
    }

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