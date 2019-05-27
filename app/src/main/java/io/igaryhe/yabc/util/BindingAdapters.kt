package io.igaryhe.yabc.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.adapters.CollectionSubjectAdapter
import io.igaryhe.yabc.models.CollectionSubject

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: LiveData<String>) {
    Picasso.get().load(url.value).into(this)
}

@BindingAdapter("adapter")
fun RecyclerView.setAdapter(list: LiveData<List<CollectionSubject>>) {
    val adapter = this.adapter as CollectionSubjectAdapter
    adapter.setCollectionSubjects(list.value!!)
}