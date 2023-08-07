package com.example.domain.fitness

import android.content.Context
import com.example.data.repository.FitnessRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetAllExercisesUseCase @Inject constructor(private val fitnessRepository: FitnessRepository) {
    suspend operator fun invoke(context: Context) = fitnessRepository.getAllExercises(context)

}