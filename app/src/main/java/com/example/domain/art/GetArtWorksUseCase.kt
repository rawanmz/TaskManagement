package com.example.domain.art

import com.example.data.repository.ArtWorkRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetArtWorksUseCase @Inject constructor(private val artWorkRepository: ArtWorkRepository) {
    suspend operator fun invoke() = artWorkRepository.getArtWorks()

}