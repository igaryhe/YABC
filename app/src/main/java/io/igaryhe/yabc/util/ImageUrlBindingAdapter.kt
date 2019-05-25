package io.igaryhe.yabc.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: LiveData<String>) {
    Picasso.get().load(url.value).into(this)
}