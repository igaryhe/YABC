package io.igaryhe.yabc.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.squareup.picasso.Picasso

@BindingAdapter("liveImageUrl")
fun ImageView.setImageUrl(url: LiveData<String?>) {
    if (url.value != null) {
        Picasso.get().load(url.value).into(this)
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    if (url != null)
        Picasso.get().load(url).into(this)
}