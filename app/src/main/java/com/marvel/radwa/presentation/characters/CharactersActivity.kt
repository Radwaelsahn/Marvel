package com.marvel.radwa.presentation.characters

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.radwa.R
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.presentation.BaseActivity
import com.marvel.radwa.presentation.details.CharacterDetailsActivity
import com.marvel.radwa.utils.Keys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_marvel_characters.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharactersActivity : BaseActivity(), CharacterListener {

    val charactersViewModel: CharactersViewModel by viewModels()

    private val TAG = this.javaClass.simpleName
    lateinit var adapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marvel_characters)

//        initViews()
//        observeFlowData()
//        getData()
    }


    override fun initViews() {
        adapter = CharactersAdapter(this)
    }

    override fun getData() {
        charactersViewModel.getMarvelCharacters()
    }

    override fun observeFlowData() {
        lifecycleScope.launchWhenStarted {
            charactersViewModel.uiFlow.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        showLoading(progress_bar, state.loading)
                    }
                    is Resource.Success -> {
                        showLoading(progress_bar, false)
                        state.data?.let { showCharacters(it) } ?: showError(state.error)
                    }
                    is Resource.Error -> {
                        showLoading(progress_bar, false)
                        state.error?.let {
                            showError(state.error)
                        }
                    }
                }
            }
        }
    }

    private fun showCharacters(list: List<Character>) {
        if (charactersViewModel.page > 1) {
            adapter.addData(list?.toMutableList())
        } else {

            adapter.setList(list.toMutableList())
            rv_characters.adapter = adapter

            rv_characters.addOnScrollListener(object :
                PaginationScrollListener(rv_characters.layoutManager as LinearLayoutManager) {
                override fun isLastPage(): Boolean {
                    return charactersViewModel.isLastPage
                }

                override fun isLoading(): Boolean {
                    return charactersViewModel.isLoading
                }

                override fun loadMoreItems() {
                    charactersViewModel.page++
                    charactersViewModel.isLoading = true
                    charactersViewModel.getMarvelCharacters()
                }
            })
        }
    }

    override fun onCharacterClicked(character: Character) {
        var intent = Intent(this, CharacterDetailsActivity::class.java)
        intent.putExtra(Keys.KEY_CHARACTER_OBJ, character)
        startActivity(intent)
    }

}
