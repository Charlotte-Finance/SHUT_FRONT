package com.example.shut_fe.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shut_fe.R
import com.example.shut_fe.databinding.FragmentPreferenceBinding
import com.example.shut_fe.models.User
import com.example.shut_fe.view_model_factories.PreferenceViewModelFactory
import com.example.shut_fe.view_models.PreferenceViewModel

class PreferenceFragment : Fragment() {
    private lateinit var application: Application
    private lateinit var viewModel: PreferenceViewModel
    private lateinit var viewModelFactory: PreferenceViewModelFactory
    private var binding: FragmentPreferenceBinding? = null
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_preference,
            container,
            false,
        )
        user = PreferenceFragmentArgs.fromBundle(requireArguments()).user
        application = requireNotNull(this.activity).application
        viewModelFactory = PreferenceViewModelFactory(application, user)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PreferenceViewModel::class.java)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.repeatFun()
        binding?.logoutButton?.setOnClickListener {
            findNavController().navigate(PreferenceFragmentDirections.preferenceFragmentToLoginFragment())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


