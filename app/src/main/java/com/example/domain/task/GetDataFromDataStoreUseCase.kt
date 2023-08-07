package com.example.domain.task

import com.example.data.DataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDataFromDataStoreUseCase @Inject constructor(
    private val dataStore: DataStore
) {
     operator fun invoke() : Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}