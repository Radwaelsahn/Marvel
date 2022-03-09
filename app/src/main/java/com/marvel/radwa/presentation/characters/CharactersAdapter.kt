package com.marvel.radwa.presentation.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.radwa.R
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.utils.Constants
import com.marvel.radwa.utils.convertToMd5
import com.marvel.radwa.utils.loadImage
import kotlinx.android.extensions.LayoutContainer


/**
 * Created by RadwaElsahn on 3/4/2022.
 */

class CharactersAdapter(private val listener: CharacterListener) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    lateinit var characters: MutableList<Character>

    fun setList(list: MutableList<Character>) {
        this.characters = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_character, parent, false)

        val display = parent.context.resources.displayMetrics
        val width = display.widthPixels
        //val height = width * 3 / 4 // aspect ratio 4:3
        val height = display.heightPixels / 4
        val params = ViewGroup.LayoutParams(width, height)
        view.layoutParams = params

        return CharacterViewHolder(
            view
        )
    }

    fun addData(listItems: List<Character>) {
        var size = characters?.size!!
        characters?.addAll(listItems)
        notifyItemRangeInserted(size, characters?.size!!)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        holder.bind(characters!![position], listener)
    }

    override fun getItemCount(): Int {
        return if (characters != null) characters!!.size else 0
    }

    class CharacterViewHolder(
        override val containerView: View
    ) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(
            character: Character, listener: CharacterListener
        ) {

            itemView.setOnClickListener { listener.onCharacterClicked(character) }

        val tv_character_name = itemView.findViewById<TextView>(R.id.tv_character_name)
        val iv_character_image = itemView.findViewById<ImageView>(R.id.iv_character_image)

            tv_character_name.text = character.name

            val url = character.thumbnail.path + "/landscape_amazing." + character.thumbnail.extension
            iv_character_image.loadImage(url)
        }

        fun createURL(resourceUri: String): String {
            val ts = System.currentTimeMillis().toString()

            var url =
                resourceUri + "?ts=${ts}&apikey=${Constants.marvel_public_key}&hash=${convertToMd5(ts + Constants.marvel_private_key + Constants.marvel_public_key)}"

            return url
        }


    }



}

