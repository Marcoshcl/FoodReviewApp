package com.example.foodreviewapplication.ui.reviews

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodreviewapplication.R
import com.example.foodreviewapplication.data.Review
import com.example.foodreviewapplication.data.SortOrder
import com.example.foodreviewapplication.databinding.FragmentReviewsBinding
import com.example.foodreviewapplication.util.exhaustive
import com.example.foodreviewapplication.util.onQueryTextChanged
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : Fragment(R.layout.fragment_reviews), ReviewAdapter.OnItemClickListener {

    private val viewModel: ReviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentReviewsBinding.bind(view)

        val reviewAdapter = ReviewAdapter(this)

        binding.apply {
            recyclerViewReviews.apply {
                adapter = reviewAdapter
                layoutManager = LinearLayoutManager(this.context)
                setHasFixedSize(true)
            }
            floatAddReview.setOnClickListener {
                viewModel.onAddNewReviewClick()
            }
        }

        setFragmentResultListener("add_edit_request") {_,bundle ->
            val result = bundle.getInt("add_edit_result")
            viewModel.onAddEditResult(result)
        }

        viewModel.reviews.observe(viewLifecycleOwner) {
            reviewAdapter.submitList(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.reviewEvent.collect { event ->
                when (event) {
                    is ReviewViewModel.ReviewEvent.NavigateToAddReviewScreen -> {
                        val action = ReviewFragmentDirections.actionReviewFragmentToAddEditReviewFragment( "New Task")
                        action.review = null
                        findNavController().navigate(action)
                    }
                    is ReviewViewModel.ReviewEvent.NavigateToEditReviewScreen -> {
                        val action = ReviewFragmentDirections.actionReviewFragmentToAddEditReviewFragment("Edit Task")
                        action.review = event.review
                        findNavController().navigate(action)
                    }
                    is ReviewViewModel.ReviewEvent.ShowReviewSavedConfirmationMessage -> {
                        Snackbar.make(requireView(), event.msg,Snackbar.LENGTH_SHORT).show()
                    }
                }
            }.exhaustive
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_reviews, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_date_created -> {
                viewModel.onSortOrderSelected(SortOrder.BY_DATE)
                true
            }

            R.id.action_sort_by_name_created -> {
                viewModel.onSortOrderSelected(SortOrder.BY_NAME)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(review: Review) {
        viewModel.onReviewSelected(review)
    }

    override fun onEditClick(review: Review) {
        viewModel.onEditClick(review)
    }

    override fun onDeleteClick(review: Review) {
        viewModel.onDeleteClick(review)
    }
}