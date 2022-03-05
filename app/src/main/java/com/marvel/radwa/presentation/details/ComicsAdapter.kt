package com.marvel.radwa.presentation.details

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.radwa.R
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.utils.changeSizeAspectRatio
import com.marvel.radwa.utils.loadImage
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by RadwaElsahn on 3/4/2022.
 */

class ComicsAdapter :
    RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>() {

    lateinit var comicItems: MutableList<Comics>

    fun setList(list: MutableList<Comics>) {
        this.comicItems = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_comic, parent, false)

        val display = parent.context.resources.displayMetrics
        //val width = (display.widthPixels - convertDpToPixel(32)) / 4
        val width = (display.widthPixels) / 3
//        val height = width * 3 / 4
//        val params = ViewGroup.LayoutParams(width, height)
//        view.layoutParams = params
        view.layoutParams.width = width
        return ComicViewHolder(
            view
        )
    }

    fun addData(listItems: List<Comics>) {
        var size = comicItems?.size!!
        comicItems?.addAll(listItems)
        Log.e("size", comicItems?.size.toString())
        notifyItemRangeInserted(size, comicItems?.size!!)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {

        holder.bind(comicItems!![position])
    }

    override fun getItemCount(): Int {
        return if (comicItems != null) comicItems!!.size else 0
    }

    class ComicViewHolder(
        override val containerView: View
    ) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(
            comicsItem: Comics
        ) {

            val iv_comic_image = itemView.findViewById<ImageView>(R.id.iv_comic_image)
            val tv_comic_name = itemView.findViewById<TextView>(R.id.tv_comic_name)

            tv_comic_name.text = comicsItem.title

            val url =
                comicsItem.thumbnail?.path + "/portrait_fantastic." + comicsItem.thumbnail?.extension
            Log.e("title", comicsItem.title.toString())
            Log.e("comicUrl", url)

//            changeSizeAspectRatio(iv_comic_image, itemView.context, itemView.width)
            iv_comic_image.loadImage(url)
        }
    }

}

