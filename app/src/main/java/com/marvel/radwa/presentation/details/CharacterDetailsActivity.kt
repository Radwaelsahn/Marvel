package com.marvel.radwa.presentation.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.marvel.radwa.R
import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.presentation.BaseActivity
//import com.marvel.radwa.presentation.UIState
import com.marvel.radwa.utils.Keys
import com.marvel.radwa.utils.addVerticalItemDecoration
import com.marvel.radwa.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_character_details.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CharacterDetailsActivity : BaseActivity() {

    val characterDetailsViewModel: CharactersDetailsViewModel by viewModels()

    var character: Character? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_character_details)

        super.onCreate(savedInstanceState)
//        initViews()
//        observeFlowData()
//        getData()
    }

    override fun getData() {
        character?.let {
            characterDetailsViewModel.getCharacterComics(it.id)
        }
    }


    override fun observeFlowData() {
        lifecycleScope.launchWhenStarted {
            characterDetailsViewModel.uiFlow.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        showLoading(progress_bar, state.loading)
                    }
                    is Resource.Success -> {
                        showLoading(progress_bar, state.loading)
                        state.data?.let { showComicsList(it) } ?: showError(state.error)
                    }
                    is Resource.Error -> {
                        showLoading(progress_bar, state.loading)
                        showError(state.error)
                    }
                }
            }
        }
    }

    override fun initViews() {
        character = intent.getParcelableExtra(Keys.KEY_CHARACTER_OBJ)

        onClickListeners()
        character?.let {
            showDetails(character)
        }
    }


//    fun showLoading(show: Boolean) {
//        if (progress_bar != null)
//            if (show) {
//                progress_bar.visibility = View.VISIBLE
//            } else {
//                progress_bar.visibility = View.GONE
//            }
//    }


    private fun showComicsList(list: List<Comics>) {
        var adapter = ComicsAdapter()
        adapter.setList(list.toMutableList())
        rv_comics.adapter = adapter
        rv_comics.addVerticalItemDecoration()
    }


    private fun showDetails(character: Character?) {
        character?.let { character ->
            val url =
                character.thumbnail.path + "/landscape_amazing." + character.thumbnail.extension
            iv_character_image.loadImage(url)
            tv_name.text = character.name
            tv_description.text = character.description
        }
    }

    private fun onClickListeners() {
        iv_back.setOnClickListener { onBackPressed() }
    }

}