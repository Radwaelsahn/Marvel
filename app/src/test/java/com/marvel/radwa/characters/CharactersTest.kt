package com.marvel.radwa.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.marvel.radwa.App
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.models.responses.ErrorResponse
import com.marvel.radwa.data.source.DataRepository
import com.marvel.radwa.domain.MarvelCharactersUseCase
import com.marvel.radwa.presentation.characters.CharactersViewModel
import com.marvel.radwa.utils.InstantExecutorExtension
import com.marvel.radwa.utils.MainCoroutineRule
import com.marvel.radwa.utils.TestModelsGenerator
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.bouncycastle.jcajce.provider.symmetric.ARC4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class CharactersTest {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var charactersViewModel: CharactersViewModel

    @RelaxedMockK
    private lateinit var charactersUseCase: MarvelCharactersUseCase

    @RelaxedMockK
    private lateinit var dataRepository: DataRepository


    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        charactersViewModel = CharactersViewModel(charactersUseCase)
        App.context = mockk(relaxed = true)
    }


    @Test
    fun `verify getcharacters is success`() {
        val expectedResponse = testModelsGenerator.generateCharactersResponse()
//        val expectedList = testModelsGenerator.generateCharactersList()
        val characterModelSuccess = MutableLiveData<Resource<BaseResponse<Character>>>()
        characterModelSuccess.value = Resource.Success(expectedResponse)
        // mock with use case
        every { charactersUseCase.getMarvelCharacters(1) } just Runs
        every { charactersUseCase.response } returns characterModelSuccess
        // call the involved method on view model
        charactersViewModel = CharactersViewModel(charactersUseCase)
        charactersViewModel.getMarvelCharacters()
        // verify
        charactersViewModel.response.observeForever{
            Assert.assertEquals(characterModelSuccess.value!!, it)
            Assert.assertNotNull(it)
        }
    }

}