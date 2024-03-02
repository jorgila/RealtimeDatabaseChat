package com.estholon.realtimedatabasechat.ui.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.estholon.realtimedatabasechat.R
import com.estholon.realtimedatabasechat.databinding.FragmentChatBinding
import com.estholon.realtimedatabasechat.ui.viewModels.ChatFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    // CONNECTION

    private val viewModel by viewModels<ChatFragmentViewModel>()

    // BINDING VARIABLE

    private lateinit var binding : FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // BINDING START
        binding = FragmentChatBinding.inflate(inflater,container,false)
        // BINDING BODY
        initListeners()
        // BINDING END
        return binding.root
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_chat_fragment_to_main_fragment)
        }
        binding.btnSendMsg.setOnClickListener{
            viewModel.sendMessage()
        }
    }

}