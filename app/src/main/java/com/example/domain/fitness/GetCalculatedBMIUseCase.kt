package com.example.domain.fitness

import com.example.data.repository.FitnessRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCalculatedBMIUseCase @Inject constructor(private val fitnessRepository: FitnessRepository) {
    suspend operator fun invoke(age: Int, weight: Double, height: Double) =
        fitnessRepository.getCalculatedBMI(age, weight, height)
}