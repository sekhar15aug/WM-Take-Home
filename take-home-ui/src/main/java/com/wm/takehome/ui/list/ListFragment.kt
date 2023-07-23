package com.wm.takehome.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.wm.takehome.ui.R
import com.wm.takehome.ui.databinding.FragmentListBinding
import com.wm.takehome.ui.util.InjectRepository
import com.wm.takehome.ui.util.WMViewModelFactory
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Countries.
 */
class ListFragment(
    private val factoryProducer: (() -> ViewModelProvider.Factory)? = null
) : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var listViewAdapter: ListViewAdapter

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory: ViewModelProvider.Factory = factoryProducer?.invoke() ?: WMViewModelFactory(
            this,
            arguments,
            InjectRepository.listRepository
        )
        listViewModel = ViewModelProvider(this, factory)[ListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListView()
        updateViewState()
    }

    private fun setupListView() {
        listViewAdapter = ListViewAdapter()
        binding.countryList.adapter = listViewAdapter
    }

    private fun updateViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            listViewModel.listState.collect {
                updateViewsVisibility(it)
            }
        }
    }

    private fun updateViewsVisibility(state: ListState) {
        if (!state.countries.isNullOrEmpty()) {
            listViewAdapter.submitList(state.countries)
            binding.countryList.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE
            binding.statusMessage.visibility = View.GONE
        } else if (state.isLoading) {
            binding.countryList.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
            binding.statusMessage.visibility = View.GONE
        } else if (state.isError) {
            binding.countryList.visibility = View.GONE
            binding.loading.visibility = View.GONE
            binding.statusMessage.visibility = View.VISIBLE
            binding.statusMessage.text = getString(R.string.list_error_message)
        } else if (state.isEmpty) {
            binding.countryList.visibility = View.GONE
            binding.loading.visibility = View.GONE
            binding.statusMessage.visibility = View.VISIBLE
            binding.statusMessage.text = getString(R.string.list_empty_message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}