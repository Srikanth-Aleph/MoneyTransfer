package com.moneytransfer.demo.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moneytransfer.demo.databinding.FragmentAccountsBinding

class AccountsFragment : Fragment() {

    private lateinit var accountsViewModel: AccountsViewModel
    private var _binding: FragmentAccountsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountsViewModel =
            ViewModelProvider(this).get(AccountsViewModel::class.java)

        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAccounts
        accountsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}