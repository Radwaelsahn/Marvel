package com.marvel.radwa.domain

import androidx.lifecycle.MutableLiveData
import com.marvel.radwa.data.source.DataSource
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.utils.Constants
import com.marvel.radwa.utils.convertToMd5
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

class CharacterDetailsUseCase @Inject constructor(
    private val dataRepository: DataSource,
    override val coroutineContext: CoroutineContext
) : CoroutineScope {

    val showLoading = MutableLiveData<Boolean>()
    var page = 1

    private val _response = MutableLiveData<Resource<BaseResponse<Character>>>()
    val response = _response

    fun getCharactersDetails(characterId: Int) {
        showLoading.postValue(true)
        val ts = System.currentTimeMillis().toString()
        val hash =
            convertToMd5(ts + Constants.marvel_private_key + Constants.marvel_public_key)
        launch {
            try {
                var resources = dataRepository.getCharacterDetails(
                    characterId,
                    ts,
                    Constants.marvel_public_key,
                    hash
                )
                showLoading.postValue(false)
                if (resources!!.data != null) {
                    _response.postValue(resources)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                showLoading.postValue(false)

            }
        }
    }
}
