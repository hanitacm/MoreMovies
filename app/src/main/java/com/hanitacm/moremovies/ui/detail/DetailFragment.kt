package com.hanitacm.moremovies.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.moremovies.R
import com.hanitacm.moremovies.databinding.DetailFragmentBinding
import com.hanitacm.moremovies.ui.detail.DetailViewModelState.*
import com.hanitacm.moremovies.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding by viewBinding(DetailFragmentBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.viewState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                when (it) {
                    is Loading -> showProgressBar()
                    is DetailLoaded -> loadMovieDetail(it.movie)
                    is DetailLoadFailure -> showError(it.error.message)
                }
            }

        }

        val movieId: Int = arguments?.getInt("movie")!!


        viewModel.getMovieDetail(movieId)

    }

    private fun showProgressBar() {
        binding.progressBar.isVisible = true
    }

    @SuppressLint("SetTextI18n")
    private fun loadMovieDetail(movie: MovieDomainModel) {
        binding.progressBar.isGone = true


        with(binding) {
            movieOverview.text = movie.overview
            composeView.setContent {
                MovieDetail(
                    title = movie.title,
                    countryDate = "${movie.originalLanguage.uppercase(Locale.getDefault())} | ${movie.releaseDate}",
                    rating = movie.voteAverage
                )
            }
            backdrop.load("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
        }
    }

    private fun showError(error: String?) {
        error?.let { Snackbar.make(requireView(), error, LENGTH_LONG).show() }
    }


}