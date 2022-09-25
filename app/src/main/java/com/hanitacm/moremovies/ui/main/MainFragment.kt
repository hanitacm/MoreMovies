package com.hanitacm.moremovies.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.hanitacm.data.repository.model.MovieDomainModel
import com.hanitacm.moremovies.R
import com.hanitacm.moremovies.databinding.MainFragmentBinding
import com.hanitacm.moremovies.ui.main.MainViewModelState.*
import com.hanitacm.moremovies.ui.main.adapters.MoviesAdapter
import com.hanitacm.moremovies.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind) { rvMovies.adapter = null }
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var viewAdapter: MoviesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar()
        setupRecyclerView()
        subscribeObservers()

        viewModel.getPopularMovies()
    }

    private fun setToolBar() {
        binding.toolbar.title = getString(R.string.app_name)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun setupRecyclerView() {
        val viewManager = GridLayoutManager(requireContext(), 2)
         viewAdapter = MoviesAdapter { movieId ->
            findNavController().navigate(
                R.id.action_firstFragment_to_detailFragment,
                bundleOf("movie" to movieId)
            )
        }
        binding.rvMovies.adapter = viewAdapter
        binding.rvMovies.layoutManager = viewManager
    }

    private fun subscribeObservers() {
        lifecycleScope.launch {
            viewModel.viewState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                when (it) {
                    is Loading -> showProgressBar()
                    is MoviesLoaded -> loadMovies(it.movies)
                    is MoviesLoadFailure -> showError(it.error.message)

                }
            }
        }
    }

    private fun showProgressBar() {
        binding.progressBar.isVisible = true
    }

    private fun loadMovies(movies: List<MovieDomainModel>) {
        binding.progressBar.isGone = true
        viewAdapter.submitList(movies)
    }

    private fun showError(error: String?) {
        error?.let {
            Snackbar.make(requireView(), error, LENGTH_LONG).show()
        }
    }


}