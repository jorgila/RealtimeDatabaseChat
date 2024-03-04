package com.estholon.realtimedatabasechat.ui.views.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.estholon.realtimedatabasechat.databinding.ItemChatMeBinding
import com.estholon.realtimedatabasechat.databinding.ItemChatOthersBinding
import com.estholon.realtimedatabasechat.domain.model.MessageModel

class ChatViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when(itemViewType){
            ChatAdapter.SENT_MESSAGE -> bindSentMessage(messageModel)
            ChatAdapter.RECEIVED_MESSAGE -> bindReceivedMessage(messageModel)
        }
    }

    private fun bindReceivedMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatOthersBinding
        binding.tvDate.text = messageModel.date
        binding.tvChat.text = messageModel.msg
        binding.tvName.text = messageModel.user.username
        binding.tvHour.text = messageModel.hour
    }

    private fun bindSentMessage(messageModel: MessageModel) {
        val currentBinding = binding as ItemChatMeBinding
        binding.tvDate.text = messageModel.date
        binding.tvChat.text = messageModel.msg
        binding.tvHour.text = messageModel.hour
    }

}