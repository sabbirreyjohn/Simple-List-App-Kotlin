package xyz.androidrey.basiclistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import xyz.androidrey.basiclistapp.databinding.RowPictureItemBinding
import xyz.androidrey.basiclistapp.model.Picture
import xyz.androidrey.basiclistapp.ui.listscreen.ListFragmentDirections

class PictureAdapter : ListAdapter<Picture,
        PictureAdapter.TheViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TheViewHolder {
        return TheViewHolder(
            RowPictureItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }


    class TheViewHolder(
        private var binding:
        RowPictureItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(picture: Picture) {
            binding.picture = picture
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailsFragment(picture)
                binding.root.findNavController().navigate(action)
            }

        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Picture>() {
        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem.picId == newItem.picId
        }

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
            return oldItem.picThumbnail == newItem.picThumbnail
        }
    }
}
