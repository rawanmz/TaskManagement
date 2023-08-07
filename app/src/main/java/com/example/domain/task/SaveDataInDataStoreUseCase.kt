package com.example.domain.task

import com.example.data.DataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveDataInDataStoreUseCase @Inject constructor(
    private val dataStore: DataStore
) {
    suspend operator fun invoke(showTipsPage: Boolean) {
        dataStore.saveOnBoardingState(showTipsPage = showTipsPage)
    }
}