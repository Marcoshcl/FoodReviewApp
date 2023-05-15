package com.example.foodreviewapplication.ui.reviews

import androidx.lifecycle.*
import com.example.foodreviewapplication.data.PreferencesManager
import com.example.foodreviewapplication.data.Review
import com.example.foodreviewapplication.data.ReviewDao
import com.example.foodreviewapplication.data.SortOrder
import com.example.foodreviewapplication.ui.ADD_REVIEW_RESULT_OK
import com.example.foodreviewapplication.ui.EDIT_REVIEW_RESULT_OK
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val reviewDao: ReviewDao,
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    val searchQuery = state.getLiveData("searchQuery", "")

    val preferencesFlow = preferencesManager.preferenceFlow

    private val ReviewsEventChannel = Channel<ReviewEvent>()
    val reviewEvent = ReviewsEventChannel.receiveAsFlow()

    private val reviewsFlow = combine(
        searchQuery.asFlow(),
        preferencesFlow
    ) { query, filterPreference ->
        Pair(query, filterPreference)
    }.flatMapLatest { (query, filterPreference) ->
        reviewDao.getReviews(query, filterPreference.sortOrder)
    }

    val reviews = reviewsFlow.asLiveData()

    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrderCompleted(sortOrder)
    }

    fun onReviewSelected(review: Review) = viewModelScope.launch {
        ReviewsEventChannel.send(ReviewEvent.NavigateToEditReviewScreen(review))
    }

    fun onDeleteClick(review: Review) = viewModelScope.launch {
        reviewDao.delete(review)
    }

    fun onEditClick(review: Review) = viewModelScope.launch {
        reviewDao.update(review)
    }

    fun onAddNewReviewClick() = viewModelScope.launch {
        ReviewsEventChannel.send(ReviewEvent.NavigateToAddReviewScreen)
    }

    fun onAddEditResult(result: Int) {
        when (result) {
            ADD_REVIEW_RESULT_OK -> showReviewSavedConfirmationMessage("Review adicionada")
            EDIT_REVIEW_RESULT_OK -> showReviewSavedConfirmationMessage("Review atualizada")
        }
    }

    private fun showReviewSavedConfirmationMessage(text: String) {
        viewModelScope.launch {
            ReviewsEventChannel.send(ReviewEvent.ShowReviewSavedConfirmationMessage(text))
        }
    }

    sealed class ReviewEvent {
        object NavigateToAddReviewScreen : ReviewEvent()
        data class NavigateToEditReviewScreen(val review: Review) : ReviewEvent()
        data class ShowReviewSavedConfirmationMessage(val msg: String) : ReviewEvent()
    }
}