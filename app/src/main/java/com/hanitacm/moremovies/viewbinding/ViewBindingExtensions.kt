package com.hanitacm.moremovies.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T,
    disposeCallback: T.() -> Unit = {}
) =
    FragmentViewBindingDelegate(this, viewBindingFactory, disposeCallback)
