package com.example.foodreviewapplication.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.foodreviewapplication.R
import com.example.foodreviewapplication.data.Review
import com.example.foodreviewapplication.databinding.ReviewCardItemBinding
import com.example.foodreviewapplication.ui.addeditreview.AddEditReviewViewModel

class ReviewAdapter(private val listener: OnItemClickListener) : ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ReviewViewHolder {
        val binding = ReviewCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ReviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ReviewViewHolder(private val binding : ReviewCardItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val review = getItem(position)
                        listener.onItemClick(review)
                    }
                }
            }
        }

        fun bind(review: Review) {
            binding.apply {
                imageViewItemPhoto.setImageResource(R.drawable.baseline_fireplace_24)
                textViewItemNomeRestaurante.text = review.nomeRestaurante
                textViewItemNomePrato.text = review.nomePrato
                textViewItemNotaPrato.text = review.nota
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(review: Review)
        fun onEditClick(review: Review)
        fun onDeleteClick(review: Review)
    }

    class DiffCallback : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
            oldItem == newItem

    }
}