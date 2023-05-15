package com.example.foodreviewapplication.ui.addeditreview

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.foodreviewapplication.R
import com.example.foodreviewapplication.databinding.FragmentAddReviewBinding
import com.example.foodreviewapplication.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddEditReviewFragment : Fragment(R.layout.fragment_add_review) {

    private val viewModel: AddEditReviewViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddReviewBinding.bind(view)

        binding.apply {
            editTextNomeDoPrato.setText(viewModel.nomePrato)
            editTextNomeDoPrato.isVisible = viewModel.review == null
            editTextNomeDoRestaurante.setText(viewModel.nomeRestaurante)
            editTextNomeDoRestaurante.isVisible = viewModel.review == null
            editTextLocalizacao.setText(viewModel.localização)
            editTextLocalizacao.isVisible = viewModel.review != null
            editTextComentario.setText(viewModel.comentario)
            buttonAddLoc.isVisible = viewModel.review == null
            buttonDeletarReview.isVisible = viewModel.review != null
            textViewData.text ="Created: ${viewModel.review?.createdDateFormatted}"
            textViewData.isVisible = viewModel.review != null

            if(viewModel.review != null) {
                ratingBar.rating = viewModel.nota.toFloat()
            }

            editTextNomeDoPrato.addTextChangedListener {
                viewModel.nomePrato = it.toString()
            }

            editTextNomeDoRestaurante.addTextChangedListener {
                viewModel.nomeRestaurante = it.toString()
            }

            editTextLocalizacao.addTextChangedListener {
                viewModel.localização = it.toString()
            }

            buttonAddFotos.setOnClickListener {
                //logica das fotos
            }

            buttonAddLoc.setOnClickListener {
                buttonAddLoc.isVisible = false
                editTextLocalizacao.isVisible = true
            }

            editTextComentario.addTextChangedListener {
                viewModel.comentario = it.toString()
            }

            buttonSalvarReview.setOnClickListener {
                viewModel.nota = ratingBar.getRating().toString()
                viewModel.onSaveClick()
            }
            buttonDeletarReview.setOnClickListener {
                viewModel.review?.let { it1 -> viewModel.onDeleteClick(it1) }
            }

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.addEditReviewEvent.collect { event ->
                    when (event) {
                        is AddEditReviewViewModel.AddEditReviewEvent.ShowInvalidInputMessage -> {
                            Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                        }
                        is AddEditReviewViewModel.AddEditReviewEvent.NavigateBackWithResult -> {
                            binding.editTextNomeDoPrato.clearFocus()
                            setFragmentResult(
                                "add_edit_request",
                                bundleOf("add_edit_result" to event.result)
                            )
                            findNavController().popBackStack()
                        }
                        is AddEditReviewViewModel.AddEditReviewEvent.NavigateBackWithoutResult -> {
                            findNavController().popBackStack()
                        }
                    }.exhaustive
                }
            }
        }
    }

}
