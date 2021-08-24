package com.moneytransfer.transfer.ui.transfer

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.moneytransfer.transfer.R
import com.moneytransfer.transfer.databinding.FragmentTransferBinding
import com.moneytransfer.transfer.model.TransferRequest
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class TransferFragment : Fragment() {


    private var _binding: FragmentTransferBinding? = null

    private val binding get() = _binding!!
    private val viewModel by viewModel<TransferViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val fromAccoutTV: TextView = binding.fromET
        val toAccountTV: TextView = binding.toET
        val amountTv : TextView = binding.amountET

        val transferButton : Button = binding.buttonTranfer

        // Wire inputs
        transferButton.setOnClickListener { _ ->

            if(!checkFields(fromAccoutTV, toAccountTV, amountTv)){
                viewModel.transferInformation.value?.let {
                    val copy = TransferRequest(
                        amount = amountTv.text.toString().toInt(),
                        fromAccount = fromAccoutTV.text.toString(),
                        toAccount = toAccountTV.text.toString(),
                        metaData = "3a46884-84ac-4b29-985f-b3c8eebf7e19"
                    )
                    viewModel.onTransferRequestSubmitted.postValue(copy)
                    Handler().post { viewModel.onTransferClicked.postValue(Unit) }
                }
            }else {
                Toast.makeText(
                    requireContext(),
                    R.string.empty_field_error,
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

        // Wire outputs
        viewModel.run {
            onTransferProcessingSuccess.observe(viewLifecycleOwner, Observer {
                val action = TransferFragmentDirections.actionTransFragmentToSuccessFragment()
                findNavController().navigate(action)
            })
            onTransferProcessing.observe(viewLifecycleOwner, Observer {
                Timber.i("Account loading status: $it")
            })
            onTransferProcessingError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(
                    requireContext(),
                    R.string.generic_error_message,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }

        return root
    }

    private fun checkFields(fromAccountTV : TextView, toAccountTV : TextView, amountTV : TextView) : Boolean{

        return fromAccountTV.text.toString().trim().isEmpty() &&
                toAccountTV.text.toString().trim().isEmpty() &&
                amountTV.text.toString().trim().isEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}