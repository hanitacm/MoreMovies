package com.hanitacm.moremovies.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.ExternalResource

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : ExternalResource() {
    val testScheduler: TestCoroutineScheduler
        get() = testDispatcher.scheduler

    override fun before() {
        Dispatchers.setMain(testDispatcher)
    }

    override fun after() {
        Dispatchers.resetMain()
    }
}
