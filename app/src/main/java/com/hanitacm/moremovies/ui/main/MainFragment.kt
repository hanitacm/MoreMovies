package com.hanitacm.moremovies.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hanitacm.moremovies.R
import com.hanitacm.moremovies.databinding.MainFragmentBinding
import com.hanitacm.moremovies.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar()
        binding.composeView.setContent {
            MainScreen(viewModel = viewModel)
        }
    }

    private fun setToolBar() {
        binding.toolbar.title = getString(R.string.app_name)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

//    private fun setupRecyclerView() {
//        val viewManager = GridLayoutManager(requireContext(), 2)
//         viewAdapter = MoviesAdapter { movieId ->
//            findNavController().navigate(
//                R.id.action_firstFragment_to_detailFragment,
//                bundleOf("movie" to movieId)
//            )
//        }
//        binding.rvMovies.adapter = viewAdapter
//        binding.rvMovies.layoutManager = viewManager
//    }

}