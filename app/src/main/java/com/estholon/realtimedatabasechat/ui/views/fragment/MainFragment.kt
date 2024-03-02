package com.estholon.realtimedatabasechat.ui.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.estholon.realtimedatabasechat.R
import com.estholon.realtimedatabasechat.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

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
        // BINDING END
        return binding.root
    }

    private fun initListeners() {
        binding.btnChat.setOnClickListener {
            if(!binding.tieName.text.isNullOrEmpty()){
            findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
            }
        }
    }

}