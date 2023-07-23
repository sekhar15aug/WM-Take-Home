package com.wm.takehome.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wm.takehome.domain.models.Country
import com.wm.takehome.ui.list.ListFragment
import com.wm.takehome.ui.list.ListState
import com.wm.takehome.ui.list.ListViewModel
import com.wm.takehome.ui.util.RecyclerViewMatchers.hasItemCount
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListFragmentTest {

    lateinit var listViewModel: ListViewModel

    @Before
    fun setup() {
        listViewModel = mockk(relaxed = true)
    }

    @Test
    fun testListFragment_whenListResponseIsEmpty() {
        every { listViewModel.listState } returns MutableStateFlow(ListState(countries = emptyList()))
        val scenario = launchFragment()
        scenario.moveToState(Lifecycle.State.RESUMED)
        val testString = getString(R.string.list_empty_message)
        onView(withId(R.id.statusMessage)).check(matches(withText(testString)))
    }

    @Test
    fun testListFragment_whenListResponseIsError() {
        every { listViewModel.listState } returns MutableStateFlow(ListState(errorValue = Throwable()))
        val scenario = launchFragment()
        scenario.moveToState(Lifecycle.State.RESUMED)
        val testString = getString(R.string.list_error_message)
        onView(withId(R.id.statusMessage)).check(matches(withText(testString)))
    }

    @Test
    fun testListFragment_whenListResponseIsNonEmpty() {
        every { listViewModel.listState } returns MutableStateFlow(ListState(countries = getCountryList()))
        val scenario = launchFragment()
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.countryList)).check(matches(hasItemCount(3)))
    }

    private fun launchFragment(fragmentArgs: Bundle? = null) =
        launchFragmentInContainer<ListFragment>(
            fragmentArgs,
            R.style.Theme_AppCompat_DayNight,
            factory = object : FragmentFactory() {
                override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                    return ListFragment {
                        TestViewModelFactory()
                    }
                }
            })

    private fun getString(resourceId: Int) =
        InstrumentationRegistry.getInstrumentation().targetContext.getString(resourceId)

    private fun getCountryList(): List<Country> {
        val list = mutableListOf<Country>()
        list.add(
            Country(
                name = "testName1",
                region = "testRegion1",
                code = "testCode1",
                capital = "testCapital1"
            )
        )
        list.add(
            Country(
                name = "testName2",
                region = "testRegion2",
                code = "testCode2",
                capital = "testCapital2"
            )
        )
        list.add(
            Country(
                name = "testName3",
                region = "testRegion3",
                code = "testCode3",
                capital = "testCapital3"
            )
        )
        return list
    }

    @Suppress("UNCHECKED_CAST")
    inner class TestViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {
                    isAssignableFrom(ListViewModel::class.java) ->
                        listViewModel

                    else -> throw IllegalArgumentException("Can't create view model for ${modelClass.name}")
                }
            } as T
    }
}