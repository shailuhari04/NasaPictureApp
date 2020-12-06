package com.droidplusplus.nasapictureapp.ui.imagelist

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.droidplusplus.nasapictureapp.R
import com.droidplusplus.nasapictureapp.model.DataItem
import kotlinx.android.synthetic.main.image_list_row_item.view.*

class ImageListAdapter :
    ListAdapter<DataItem, ImageListAdapter.MViewHolder>(MDiffUtilCallback()) {

    // OnItemClickListener to pass the clicked position
    var itemClickListener: (position: Int) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        return MViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_list_row_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class MViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return
            itemClickListener.invoke(adapterPosition)
        }

        fun bind(item: DataItem) = with(itemView) {
            titleTV.text = item.title
            imageIV.load(item.url)
        }
    }

    private class MDiffUtilCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem == newItem
        }
    }

}
