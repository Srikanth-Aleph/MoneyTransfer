package com.moneytransfer.demo.ui.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moneytransfer.demo.databinding.FragmentTransferBinding

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

        val fromTV: TextView = binding.fromTv
        val toTV: TextView = binding.toTv
        transferViewModel.text.observe(viewLifecycleOwner, Observer {
            fromTV.text = it
        })

        val transferButton : Button = binding.button

        transferButton.setOnClickListener {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}