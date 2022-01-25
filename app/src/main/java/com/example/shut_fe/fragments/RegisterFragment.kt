package com.example.shut_fe.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shut_fe.R
import com.example.shut_fe.databinding.FragmentRegisterBinding
import com.example.shut_fe.view_model_factories.RegisterViewModelFactory
import com.example.shut_fe.view_models.RegisterViewModel

class RegisterFragment : Fragment() {
    private lateinit var application: Application
    private lateinit var viewModel: RegisterViewModel
    private lateinit var viewModelFactory: RegisterViewModelFactory
    private var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false,
        )
        application = requireNotNull(this.activity).application
        viewModelFactory = RegisterViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
        binding?.viewModel = viewModel
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToLoginFragment.observe(viewLifecycleOwner, { user ->
            user?.let {
                this.findNavController().navigate(RegisterFragmentDirections.registerFragmentToLoginFragment())
                viewModel.doneNavigating()
            }
            Toast.makeText(this.context, viewModel.message, Toast.LENGTH_SHORT).show()
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


