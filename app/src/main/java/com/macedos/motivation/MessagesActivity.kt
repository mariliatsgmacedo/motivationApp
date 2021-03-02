package com.macedos.motivation


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
        //builder.setTitle, builder.setMessage builder.setOnPositiveListener{dialog, b ->  dialog.dismiss()}

        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.choose_del_message, null)
        val btnYes = view.findViewById<TextView>(R.id.btn_yes)
        val btnNo = view.findViewById<TextView>(R.id.btn_no)
        builder.setView(view)
        val alert: AlertDialog = builder.show()
        alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnYes.setOnClickListener{ deleteOK(message)
            alert.dismiss()
        }

        btnNo.setOnClickListener {
            alert.dismiss()
        }
    }

    private fun deleteOK(message: Message){
        messages.remove(message)
        adapter.messages = messages
        adapter.notifyDataSetChanged()
    }

}