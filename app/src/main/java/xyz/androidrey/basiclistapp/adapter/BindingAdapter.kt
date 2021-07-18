package xyz.androidrey.basiclistapp.adapter

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import xyz.androidrey.basiclistapp.R
import xyz.androidrey.basiclistapp.model.Picture
import xyz.androidrey.basiclistapp.ui.listscreen.ApiStatus


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imageUrl: String?) {

    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imageUri) {
            placeholder(R.drawable.ic_baseline_cached_24)
            error(R.drawable.ic_baseline_error_24)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<Picture>?
) {

    val adapter = recyclerView.adapter as PictureAdapter
    adapter.submitList(data)
}

@BindingAdapter("srcByApiStatus")
fun bindByApiStatus(imageView: ImageView, status: ApiStatus) {

    when (status) {
        ApiStatus.DONE -> {
            imageView.visibility = View.INVISIBLE

        }

        ApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }

        ApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
    }

}