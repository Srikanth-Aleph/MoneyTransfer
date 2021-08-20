package com.moneytransfer.accounts.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moneytransfer.accounts.R
import com.moneytransfer.accounts.databinding.FragmentAccountsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            @Suppress("detekt.MagicNumber")
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val position = parent.getChildAdapterPosition(view)
                val margin = (16 * requireActivity().resources.displayMetrics.density).toInt()
                if (position == 0) {
                    outRect.set(margin, margin, margin, margin)
                } else {
                    outRect.set(margin, 0, margin, margin)
                }
            }
        })
        recyclerView.adapter = adapter

        // Wire outputs
        viewModel.run {
            onJournalsLoaded.observe(viewLifecycleOwner, Observer {
                adapter.items = it
                swipeRefreshLayout.isRefreshing = false
            })
            onJournalsLoading.observe(viewLifecycleOwner, Observer {
                Timber.i("Journals loading status: $it")
            })
            onJournalsLoadingError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(
                    requireContext(),
                    R.string.generic_error_message,
                    Toast.LENGTH_SHORT
                ).show()
                swipeRefreshLayout.isRefreshing = false
            })
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefreshJournals.postValue(Unit)
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        viewModel.onRefreshJournals.postValue(Unit)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}