package com.marvel.radwa.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.source.remote.repositories.comics.ComicsDataSource
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

class ComicsUseCase @Inject constructor(
    private val dataRepository: ComicsDataSource,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    private val _uiFlow = MutableStateFlow<Resource<List<Comics>>>(Resource.Loading(true))
    val uiFlow: StateFlow<Resource<List<Comics>>> = _uiFlow

    private val _response = MutableLiveData<Resource<BaseResponse<Comics>>>()
    val response = _response


    fun getCharactersComics(characterId: Int) {
        _uiFlow.value = Resource.Loading(true)
        val ts = System.currentTimeMillis().toString()
        val hash =
            convertToMd5(ts + Constants.marvel_private_key + Constants.marvel_public_key)
        launch {
            try {
                _uiFlow.value = Resource.Loading(true)
                var resources = dataRepository.getCharacterComics(
                    characterId,
                    ts,
                    Constants.marvel_public_key,
                    hash
                )
                _uiFlow.value = Resource.Loading(false)
                if (resources.errorResponse != null) {
                    _uiFlow.value = Resource.Error(resources.errorResponse?.message)
                    _uiFlow.value =
                        Resource.Success(dataRepository.getComicsByCharacterId(characterId))
                } else if (resources!!.data != null) {
                    resources.data?.data?.results?.let {
                        _response.postValue(resources)
                        if (it.isNotEmpty()) {
                            _uiFlow.value = Resource.Success(it)
                        }
                        resources.data?.data?.results?.map {
                            it.characterId = characterId
                            dataRepository.saveComic(it)
                            Log.e(
                                "comics size",
                                dataRepository.getComicsByCharacterId(characterId)?.size.toString()
                            )
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
