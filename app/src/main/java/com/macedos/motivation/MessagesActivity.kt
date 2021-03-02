package com.macedos.motivation


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.macedos.motivation.databinding.ActivityMessagesBinding
import com.macedos.motivation.model.Message

class MessagesActivity : AppCompatActivity(), MessageAdapter.MessageListener {

    private lateinit var binding: ActivityMessagesBinding
    private lateinit var messages: ArrayList<Message>
    private lateinit var adapter:MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messages = intent.getSerializableExtra("messages") as ArrayList<Message>

        binding = DataBindingUtil.setContentView(this, R.layout.activity_messages)

        //Mostrar botão voltar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        adapter =  MessageAdapter(messages, this)

        binding.toShowMessagesSave.adapter = adapter


    }
    //Ação do Action Bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                val intent = Intent()
                intent.putExtra("messages", messages)
                setResult(1, intent) // Enviar resultado
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDelete(message: Message) {
        messages.remove(message)
        adapter.messages = messages
        adapter.notifyDataSetChanged()
    }

}