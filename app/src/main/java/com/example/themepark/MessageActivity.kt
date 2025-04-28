package com.example.themepark

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themepark.adapter.MessageAdapter
import com.example.themepark.databinding.ActivityMessageBinding
import com.example.themepark.models.Message

class MessageActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMessageBinding
    private lateinit var messageAdapter: MessageAdapter
    private val messagesList = mutableListOf<Message>()
    private var deletePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setupRecyclerView()
        loadSampleMessages()
        setupClickListeners()
    }

    private fun setToolbar() {
        binding.toolbar.tvTitle.text = getString(R.string.message)
        binding.toolbar.ivBack.setOnClickListener(this)
    }
    private fun setupRecyclerView() {
        messageAdapter = MessageAdapter(messagesList, onItemChecked = { position ->
            deletePosition = position
        })

        binding.rvMessage.layoutManager = LinearLayoutManager(this)
        binding.rvMessage.adapter = messageAdapter
    }

    private fun setupClickListeners() {
        binding.toolbar.ivBack.setOnClickListener(this)
        binding.btnDelete.setOnClickListener(this)
        binding.btnAdd.setOnClickListener(this)
    }

    private fun loadSampleMessages() {
        val text = binding.etMessage.text.toString()
        messagesList.add(0, Message(text))
        messageAdapter.notifyDataSetChanged()
        binding.etMessage.text?.clear()
    }

    private fun deleteSampleMessage(){
        messagesList.isNotEmpty() && deletePosition in messagesList.indices
        messagesList.removeAt(deletePosition)
        messageAdapter.notifyItemRemoved(deletePosition)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivBack -> onBackPressedDispatcher.onBackPressed()

            R.id.btnAdd -> {
                loadSampleMessages()
            }

            R.id.btnDelete -> {
               deleteSampleMessage()
            }

        }
    }
}