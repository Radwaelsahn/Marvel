package com.marvel.radwa.comics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.marvel.radwa.App
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.source.DataRepository
import com.marvel.radwa.domain.ComicsUseCase
import com.marvel.radwa.presentation.characters.CharactersViewModel
import com.marvel.radwa.presentation.details.ComicsViewModel
import com.marvel.radwa.utils.InstantExecutorExtension
import com.marvel.radwa.utils.MainCoroutineRule
import com.marvel.radwa.utils.TestModelsGenerator
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class ComicsTest {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var comicsViewModel: ComicsViewModel

    @RelaxedMockK
    private lateinit var comicsUseCase: ComicsUseCase

    @RelaxedMockK
    private lateinit var dataRepository: DataRepository


    private val testModelsGenerator: TestModelsGenerator = TestModelsGenerator()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        comicsViewModel = ComicsViewModel(comicsUseCase)
        App.context = mockk(relaxed = true)
    }


    @Test
    fun `verify getcomics is success`() {
        val expectedResponse = testModelsGenerator.generateComicsResponse()
        val comicsModelSuccess = MutableLiveData<Resource<BaseResponse<Comics>>>()
        comicsModelSuccess.value = Resource.Success(expectedResponse)
        // mock with use case
        every { comicsUseCase.getCharactersComics(1017100) } just Runs
        every { comicsUseCase.response } returns comicsModelSuccess
        // call the involved method on view model
        comicsViewModel = ComicsViewModel(comicsUseCase)
        comicsViewModel.getCharacterComics(1017100)
        // verify
        comicsViewModel.response.observeForever {
            Assert.assertEquals(comicsModelSuccess.value!!, it)
            Assert.assertNotNull(it)
        }
    }

}