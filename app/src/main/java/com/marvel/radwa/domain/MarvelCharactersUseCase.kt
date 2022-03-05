package com.marvel.radwa.domain

import androidx.lifecycle.MutableLiveData
import com.marvel.radwa.data.DataSource
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.responses.BaseResponse
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

class MarvelCharactersUseCase @Inject constructor(
    private val dataRepository: DataSource,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    private val _uiFlow = MutableStateFlow<Resource<List<Character>>>(Resource.Loading(true))
    val uiFlow: StateFlow<Resource<List<Character>>> = _uiFlow

    private val _response = MutableLiveData<Resource<BaseResponse<Character>>>()
    val response = _response

    fun getMarvelCharacters(page: Int) {
        if (page == 1 || page <= response.value!!?.data?.data?.total!!) {
            val ts = System.currentTimeMillis().toString()
            val hash =
                convertToMd5(ts + Constants.marvel_private_key + Constants.marvel_public_key)

            launch {
                try {
                    _uiFlow.value = Resource.Loading(true)
                    var resources = dataRepository.getMarvelCharacters(
                        ts,
                        Constants.marvel_public_key,
                        hash,
                        Constants.pageSize,
                        page
                    )
                    _uiFlow.value = Resource.Loading(false)
                    if (resources!!.data != null) {
                        _response.postValue(resources)
                        _uiFlow.value = Resource.Success(resources.data?.data?.results)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _uiFlow.value = Resource.Loading(false)
                    _uiFlow.value = Resource.Error(e.message)
                }
            }
        }
    }

}