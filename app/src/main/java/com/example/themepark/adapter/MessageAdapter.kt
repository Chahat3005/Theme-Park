package com.example.themepark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themepark.databinding.MessageItemsBinding
import com.example.themepark.models.Message

class MessageAdapter(
    private val list: MutableList<Message>,
    private val onItemChecked: (Int) -> Unit
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(val binding: MessageItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding =
            MessageItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val messageItem = list[position]
        holder.binding.cbSelect.text = messageItem.title

        holder.binding.cbSelect.setOnCheckedChangeListener(null)
        holder.binding.cbSelect.isChecked = messageItem.isSelected

        holder.binding.cbSelect.setOnCheckedChangeListener { _, isChecked ->
            messageItem.isSelected = isChecked
            onItemChecked(position)
        }
    }
}
