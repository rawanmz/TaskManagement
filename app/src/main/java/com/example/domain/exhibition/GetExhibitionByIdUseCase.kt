package com.example.domain.exhibition

import com.example.data.repository.ArtWorkRepository
import javax.inject.Inject

class GetExhibitionByIdUseCase @Inject constructor(private val artWorkRepository: ArtWorkRepository) {
    suspend operator fun invoke(id: Long) =
        artWorkRepository.getExhibitionById(
            id = id
        )
}