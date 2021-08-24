package com.moneytransfer.transfer.ui.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.moneytransfer.transfer.databinding.FragmentSuccessBinding

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

        _binding = FragmentSuccessBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val closeButton : Button = binding.done

        closeButton.setOnClickListener {

            findNavController().navigateUp()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}