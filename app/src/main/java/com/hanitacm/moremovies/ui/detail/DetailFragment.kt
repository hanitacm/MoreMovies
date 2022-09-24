package com.hanitacm.moremovies.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import androidx.core.text.scale
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import coil.load
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.moremovies.R
import com.hanitacm.moremovies.databinding.DetailFragmentBinding
import com.hanitacm.moremovies.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val binding by viewBinding(DetailFragmentBinding::bind)
    private val viewModel: DetailViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailViewModelState.Loading -> showProgressBar()
                is DetailViewModelState.DetailLoaded -> loadMovieDetail(it.movie)
                is DetailViewModelState.DetailLoadFailure -> showError(it.error?.message)
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
            movieTitle.text = movie.title
            countryDate.text =
                "${movie.originalLanguage.uppercase(Locale.getDefault())} | ${movie.releaseDate}"
            rating.text = movie.voteAverage.toString()
            backdrop.load("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
            val sb = SpannableStringBuilder()
                .bold { append(movie.voteAverage.toString()) }
                .scale(0.75F) { append("/10") }

            rating.text = sb
        }
    }

    private fun showError(error: String?) {

    }


}