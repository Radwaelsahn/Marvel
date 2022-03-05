package com.marvel.radwa.domain

import com.marvel.radwa.data.DataSource
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.utils.Constants
import com.marvel.radwa.utils.convertToMd5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

class CharacterComicsUseCase @Inject constructor(
    private val dataRepository: DataSource,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    private val _uiFlow = MutableStateFlow<Resource<List<Comics>>>(Resource.Loading(true))
    val uiFlow: StateFlow<Resource<List<Comics>>> = _uiFlow


    fun getCharactersComics(characterId: Int) {
        _uiFlow.value = Resource.Loading(true)
        val ts = System.currentTimeMillis().toString()
        val hash =
            convertToMd5(ts + Constants.marvel_private_key + Constants.marvel_public_key)
        launch {
            try {
                var resources = dataRepository.getCharacterComics(
                    characterId,
                    ts,
                    Constants.marvel_public_key,
                    hash
                )
                _uiFlow.value = Resource.Loading(true)
                if (resources!!.data != null) {
                    resources.data?.data?.results?.let {
                        if (it.isNotEmpty()) {
                            _uiFlow.value = Resource.Success(it)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiFlow.value = Resource.Loading(false)
                _uiFlow.value = Resource.Error(e.message)
            }
        }
    }
}
