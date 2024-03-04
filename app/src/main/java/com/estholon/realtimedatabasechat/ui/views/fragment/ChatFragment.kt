package com.estholon.realtimedatabasechat.ui.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.estholon.realtimedatabasechat.R
import com.estholon.realtimedatabasechat.databinding.FragmentChatBinding
import com.estholon.realtimedatabasechat.ui.viewModels.ChatFragmentViewModel
import com.estholon.realtimedatabasechat.ui.views.adapters.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    // CONNECTION

    private val viewModel by viewModels<ChatFragmentViewModel>()

    // BINDING VARIABLE

    private lateinit var binding : FragmentChatBinding

    private lateinit var chatAdapter : ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // BINDING START
        binding = FragmentChatBinding.inflate(inflater,container,false)
        // BINDING BODY
        initUI()
        initListeners()
        // BINDING END
        return binding.root
    }

    private fun initUI() {
        setUpMessages()
        subscribeToMessages()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        binding.tvTitle.text = viewModel.name
    }

    private fun setUpMessages() {
        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvMessages.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener {
            viewModel.logout { findNavController().navigate(R.id.action_chat_fragment_to_main_fragment) }

        }
        binding.btnSendMsg.setOnClickListener{
            val msg = binding.etChat.text.toString()
            if(msg.isNotEmpty()){
                viewModel.sendMessage(msg)
            }
            binding.etChat.text.clear()
        }
    }

    private fun subscribeToMessages(){
        lifecycleScope.launch {
            viewModel.messageList.collect{
                setUpToolbar()
                chatAdapter.updateList(it.toMutableList(),viewModel.name)
                binding.rvMessages.scrollToPosition(chatAdapter.messageList.size - 1)
            }
        }
    }

}