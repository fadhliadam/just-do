package com.adam.justdo.domain.usecase

import com.adam.justdo.domain.repository.IRepository
import javax.inject.Inject

class Interactor @Inject constructor(
    private val iRepository: IRepository
) : UseCase {
}