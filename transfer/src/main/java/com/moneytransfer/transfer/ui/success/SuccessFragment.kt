package com.moneytransfer.transfer.ui.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moneytransfer.transfer.databinding.FragmentSuccessBinding
import com.moneytransfer.transfer.databinding.FragmentTransferBinding

class SuccessFragment : Fragment() {

    private lateinit var successViewModel: SuccessViewModel
    private var _binding: FragmentSuccessBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        successViewModel =
            ViewModelProvider(this).get(SuccessViewModel::class.java)

        _binding = FragmentSuccessBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val transferButton : Button = binding.button

//        transferButton.setOnClickListener {
//
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}