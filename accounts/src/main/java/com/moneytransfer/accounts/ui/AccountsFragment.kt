package com.moneytransfer.accounts.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moneytransfer.accounts.R
import com.moneytransfer.accounts.databinding.FragmentAccountsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import androidx.recyclerview.widget.DividerItemDecoration




class AccountsFragment : Fragment() {

    private var _binding: FragmentAccountsBinding? = null

    private val binding get() = _binding!!
    private val viewModel by viewModel<AccountsViewModel>()
    private val adapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.accountRecyclerView
        val swipeRefreshLayout: SwipeRefreshLayout = binding.swipeRefreshLayout

        // Prepare views
        var layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = adapter

        // Wire outputs
        viewModel.run {
            onAccountLoaded.observe(viewLifecycleOwner, Observer {
                adapter.items = it
                swipeRefreshLayout.isRefreshing = false
            })
            onAccountLoading.observe(viewLifecycleOwner, Observer {
                Timber.i("Account loading status: $it")
            })
            onAccountLoadingError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(
                    requireContext(),
                    R.string.generic_error_message,
                    Toast.LENGTH_SHORT
                ).show()
                swipeRefreshLayout.isRefreshing = false
            })
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshAccount.postValue(Unit)
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.onRefreshAccount.postValue(Unit)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}