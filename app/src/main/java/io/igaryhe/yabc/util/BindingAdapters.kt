package io.igaryhe.yabc.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.adapters.CollectionSubjectAdapter
import io.igaryhe.yabc.models.CollectionSubject

@BindingAdapter("liveImageUrl")
fun ImageView.setImageUrl(url: LiveData<String>) {
    Picasso.get().load(url.value).into(this)
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Picasso.get().load(url).into(this)
}