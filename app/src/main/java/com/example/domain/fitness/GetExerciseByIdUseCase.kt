package com.example.domain.fitness

import android.content.Context
import com.example.data.repository.FitnessRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetExerciseByIdUseCase @Inject constructor(private val fitnessRepository: FitnessRepository) {
    suspend operator fun invoke(id: String, context: Context) =
        fitnessRepository.getExerciseById(id, context)
}