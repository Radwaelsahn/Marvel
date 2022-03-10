package com.marvel.radwa.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.domain.MarvelCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val marvelUseCase: MarvelCharactersUseCase) :
    ViewModel() {

    val uiFlow = marvelUseCase.uiFlow

    var page = 1
    val isLastPage = marvelUseCase.isLastPage
    val isLoading = marvelUseCase.isLoading

    val response = marvelUseCase.response
    val resource = marvelUseCase.resource

    val characters = MutableLiveData<List<Character>>()

    fun getMarvelCharacters() {
        marvelUseCase.getMarvelCharacters(page)
    }

}