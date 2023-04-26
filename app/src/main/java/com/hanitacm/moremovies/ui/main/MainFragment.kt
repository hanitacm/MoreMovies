package com.hanitacm.moremovies.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hanitacm.moremovies.R
import com.hanitacm.moremovies.databinding.MainFragmentBinding
import com.hanitacm.moremovies.ui.theme.MoreMoviesTheme
import com.hanitacm.moremovies.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val binding by viewBinding(MainFragmentBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            MoreMoviesTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}