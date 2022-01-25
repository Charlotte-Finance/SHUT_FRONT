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
import com.example.shut_fe.databinding.FragmentLoginBinding
import com.example.shut_fe.view_model_factories.LoginViewModelFactory
import com.example.shut_fe.view_models.LoginViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {
    private lateinit var application: Application
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory
    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false,
        )
        application = requireNotNull(this.activity).application
        viewModelFactory = LoginViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding?.viewModel = viewModel
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigateToPreferenceFragment.observe(viewLifecycleOwner, { user ->
            user?.let {
                this.findNavController()
                    .navigate(LoginFragmentDirections.loginFragmentToPreferenceFragment(user))
                viewModel.doneNavigating()
            }
            // ToDo : fix toast
            Toast.makeText(this.context, viewModel.message, Toast.LENGTH_SHORT).show()
        })
        binding?.registerButton?.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.loginFragmentToRegisterFragment())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}