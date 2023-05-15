package com.example.foodreviewapplication.ui.addeditreview


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodreviewapplication.data.Review
import com.example.foodreviewapplication.data.ReviewDao
import com.example.foodreviewapplication.ui.ADD_REVIEW_RESULT_OK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditReviewViewModel @Inject constructor(
    private val reviewDao: ReviewDao,
    private val state: SavedStateHandle,
) : ViewModel() {

    val review = state.get<Review>("review")

    var nomePrato = state.get<String>("nomePrato") ?: review?.nomePrato ?: ""
        set(value) {
            field = value
            state.set("nomePrato", value)
        }

    var nomeRestaurante = state.get<String>("nomeRestaurante") ?: review?.nomeRestaurante ?: ""
        set(value) {
            field = value
            state.set("nomeRestaurante", value)
        }

    var nota = state.get<String>("nota") ?: review?.nota ?: ""
        set(value) {
            field = value
            state.set("nota", value)
        }

    var localização = state.get<String>("localização") ?: review?.localização ?: ""
        set(value) {
            field = value
            state.set("localização", value)
        }

    var reviewData = state.get<Long>("data") ?: review?.created ?: ""
        set(value) {
            field = value
            state.set("data", value)
        }

    var comentario = state.get<String>("comentario") ?: review?.comentario ?: ""
        set(value) {
            field = value
            state.set("comentario", value)
        }

    var foto1 = state.get<String>("foto1") ?: review?.foto1 ?: ""
        set(value) {
            field = value
            state.set("foto1", value)
        }

    var foto2 = state.get<String>("foto2") ?: review?.foto2 ?: ""
        set(value) {
            field = value
            state.set("foto2", value)
        }

    var foto3 = state.get<String>("foto3") ?: review?.foto3 ?: ""
        set(value) {
            field = value
            state.set("foto3", value)
        }

    var foto4 = state.get<String>("foto4") ?: review?.foto4 ?: ""
        set(value) {
            field = value
            state.set("foto4", value)
        }

    var foto5 = state.get<String>("foto5") ?: review?.foto5 ?: ""
        set(value) {
            field = value
            state.set("foto5", value)
        }

    private val addEditReviewEventChannel = Channel<AddEditReviewEvent>()
    val addEditReviewEvent = addEditReviewEventChannel.receiveAsFlow()

    fun onDeleteClick(review: Review) = viewModelScope.launch {
        reviewDao.delete(review)
        addEditReviewEventChannel.send((AddEditReviewEvent.NavigateBackWithoutResult(
            ADD_REVIEW_RESULT_OK)))
    }

    fun onSaveClick() {
        if (nomePrato.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }

        if (review != null) {
            val updatedReview = review.copy(
                nomeRestaurante, nomePrato, nota, foto1, foto2, foto3,
                foto4, foto5, localização, comentario = comentario
            )
            updateReview(updatedReview)
        } else {
            val newReview = Review(
                nomeRestaurante, nomePrato, nota, foto1, foto2, foto3,
                foto4, foto5, localização, comentario = comentario
            )
            createReview(newReview)
        }
    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditReviewEventChannel.send((AddEditReviewEvent.ShowInvalidInputMessage(text)))
    }

    fun createReview(review: Review) {
        viewModelScope.launch {
            reviewDao.insert(review)
            addEditReviewEventChannel.send((AddEditReviewEvent.NavigateBackWithResult(
                ADD_REVIEW_RESULT_OK)))
        }
    }

    fun updateReview(review: Review) {
        viewModelScope.launch {
            reviewDao.update(review)
            addEditReviewEventChannel.send((AddEditReviewEvent.NavigateBackWithResult(
                ADD_REVIEW_RESULT_OK)))
        }
    }

    sealed class AddEditReviewEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditReviewEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditReviewEvent()
        data class NavigateBackWithoutResult(val result: Int) : AddEditReviewEvent()
    }
}