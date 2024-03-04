package com.estholon.realtimedatabasechat.ui.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.estholon.realtimedatabasechat.R
import com.estholon.realtimedatabasechat.databinding.FragmentMainBinding
import com.estholon.realtimedatabasechat.ui.viewModels.MainFragmentViewModel
import com.estholon.realtimedatabasechat.ui.viewModels.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    //CONNECTIONS
    private val viewModel by viewModels<MainFragmentViewModel>()
    //BINDING VARIABLE
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // BINDING START
        binding = FragmentMainBinding.inflate(inflater,container,false)
        // BINDING BODY
        initListeners()
        subscribeToState()
        // BINDING END
        return binding.root
    }

    private fun subscribeToState() {
        lifecycleScope.launch{
            viewModel.uiState.collect{state ->
                when(state){
                    MainViewState.LOADING -> { binding.pb.visibility = View.VISIBLE }
                    MainViewState.REGISTERED -> { findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)}
                    MainViewState.UNREGISTERED -> { binding.pb.visibility = View.GONE }
                }

            }
        }
    }

    private fun initListeners() {
        binding.btnChat.setOnClickListener {
            if(!binding.tieName.text.isNullOrEmpty()){
                viewModel.saveUserName(binding.tieName.text.toString())
            findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
            }
        }
    }

}