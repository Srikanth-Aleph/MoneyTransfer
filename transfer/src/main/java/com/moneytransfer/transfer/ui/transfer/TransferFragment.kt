package com.moneytransfer.transfer.ui.transfer

import TransferViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.moneytransfer.transfer.databinding.FragmentTransferBinding

class TransferFragment : Fragment() {

    private lateinit var transferViewModel: TransferViewModel
    private var _binding: FragmentTransferBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        transferViewModel =
            ViewModelProvider(this).get(TransferViewModel::class.java)

        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val fromAccoutTV: TextView = binding.fromTV
//        val toAccountTV: TextView = binding.toTV

        transferViewModel.text.observe(viewLifecycleOwner, Observer {
//            fromAccoutTV.text = it
        })

        val transferButton : Button = binding.button

        // Wire inputs
        transferButton.setOnClickListener {

            val action = TransferFragmentDirections.actionTransFragmentToSuccessFragment()
            findNavController().navigate(action)

//            val action = TransferFragmentDirections
////            val action = TransferFragmentD
////            val action = Tra
////            findNavController().navigate(action)
//
////            val action = TransferFragmen
//////            val action = HomeFragmentDirections.actionEditJournal("")
////            findNavController().navigate(action)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}