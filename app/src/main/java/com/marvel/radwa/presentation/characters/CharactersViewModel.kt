package com.marvel.radwa.presentation.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.domain.MarvelCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val marvelUseCase: MarvelCharactersUseCase) :
    ViewModel() {

    val uiFlow = marvelUseCase.uiFlow

    var page = 1
    var isLastPage = false
    var isLoading = false

    val response = marvelUseCase.response

    val characters = MutableLiveData<List<Character>>()

    fun getMarvelCharacters() {
        marvelUseCase.getMarvelCharacters(page)
    }

}