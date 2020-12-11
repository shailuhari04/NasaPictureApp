package com.droidplusplus.nasapictureapp.ui.imagelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.droidplusplus.nasapictureapp.R
import com.droidplusplus.nasapictureapp.data.model.DataItem
import com.droidplusplus.nasapictureapp.utils.gone
import com.droidplusplus.nasapictureapp.utils.visible
import kotlinx.android.synthetic.main.image_list_row_item.view.*

class ImageListAdapter :
    ListAdapter<DataItem, ImageListAdapter.MViewHolder>(MDiffUtilCallback()) {

    // OnItemClickListener to pass the clicked position
    var itemClickListener: (position: Int) -> Unit = {}

    private lateinit var mCtx: Context

    // Delegates Properties
    private val lottieDrawableInstance by lazy { LottieDrawable() }

    private val lottieDrawable by lazy {
        if (::mCtx.isInitialized)
            LottieCompositionFactory.fromAsset(mCtx, "loader1.json")
                .addListener { lottieCompostion ->
                    lottieDrawableInstance.composition = lottieCompostion
                    lottieDrawableInstance.scale = 0.3f
                    lottieDrawableInstance.playAnimation()
                    lottieDrawableInstance.repeatCount = LottieDrawable.INFINITE
                }
        lottieDrawableInstance
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        mCtx = parent.context // initialize the context
        return MViewHolder(
            LayoutInflater.from(mCtx)
                .inflate(R.layout.image_list_row_item, parent, false)
        )
    }


    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return
            itemClickListener.invoke(adapterPosition)
        }

        fun bind(item: DataItem) = with(itemView) {
            // Title
            titleTV.apply {
                item.title?.takeIf { it.isNotBlank() }?.let {
                    text = it
                    visible()
                } ?: run { gone() }
            }

            // Image
            imageIV.apply {
                item.url?.takeIf { it.isNotBlank() }?.let {
                    load(it) {
                        placeholder(lottieDrawable)
                        error(R.drawable.ic_baseline_broken_image_24)
                    }
                    visible()
                } ?: run { gone() }
            }

        }
    }

    private class MDiffUtilCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }

}
