package com.marvel.radwa.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.domain.ComicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(private val useCase: ComicsUseCase) :
    ViewModel() {

    val uiFlow = useCase.uiFlow

    val character = MutableLiveData<Character>()
    val response = useCase.response

    fun getCharacterDetails(characterId: Int) = useCase.getCharactersComics(characterId)

    fun getCharacterComics(characterId: Int) {
        useCase.getCharactersComics(characterId)
    }

}