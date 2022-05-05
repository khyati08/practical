package com.khyati.practical

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/** Bind data used for data binding */


@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}


/**
 * Load Image in imageview using Glide
 *
 * @param imageView AppCompatImageView imageview object
 * @param url String? URL
 */
@BindingAdapter(value = ["bind:imageSet"], requireAll = false)
fun bindImageData(imageView: AppCompatImageView, url: String?) {
    var placeHolder: Drawable = ContextCompat.getDrawable(imageView.context, R.color.gray)!!
    when {
        url == null || url.isEmpty() -> {
            imageView.setImageDrawable(placeHolder)
            return
        }
        else -> {
            val requestOptions =
                RequestOptions().placeholder(placeHolder).error(placeHolder).dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            Glide.with(imageView.context).setDefaultRequestOptions(requestOptions)
                .load(url.toUri().buildUpon().scheme("https").build())
                .into(imageView)
        }
    }
}


