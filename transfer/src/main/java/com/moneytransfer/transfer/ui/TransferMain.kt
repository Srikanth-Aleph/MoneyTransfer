package com.moneytransfer.transfer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moneytransfer.transfer.databinding.FragmentTransferMainBinding

class TransferMain : Fragment() {
    private var _binding: FragmentTransferMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTransferMainBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val localNavHost = childFragmentManager.findFragmentById(R.id.nav_host_transfer_main) as NavHostFragment
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}