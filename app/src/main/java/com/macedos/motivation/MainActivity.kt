package com.macedos.motivation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.macedos.motivation.databinding.ActivityMainBinding
import com.macedos.motivation.model.Message

class MainActivity : AppCompatActivity() {
    //Definir Binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var messages: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializar pra carregar layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Mensagens padrões do app
        messages = arrayListOf(
            Message("Eu sei que não sou perfeito e está tudo bem",false),
            Message("Estou feliz em estar aqui",false),
            Message("Eu sou forte e corajoso",false)
        )

        //Btn mensagem aleatoria
        binding.msgButton.setOnClickListener {
            showMessageRandom()
        }

        //Btn salvar mensagens
        binding.saveButton.setOnClickListener {
            saveMessage()
        }

        //Btn para listar minhas mensagens
        binding.listMessage.setOnClickListener {
            messageListIntent()
        }

    }

    @SuppressLint("InflateParams")
    private fun showMessageRandom() {
        //Inicializando o Builder de alertDialog
        val builder = AlertDialog.Builder(this)

        val view = LayoutInflater.from(this).inflate(R.layout.alert_message, null)
        val message = view.findViewById<TextView>(R.id.message_random)

        val index = (0 until messages.size).random()
        message.text = messages[index].text
        builder.setView(view)

        val alertDialog = builder.show()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun saveMessage(){
        val message = binding.inputMessage.text.toString().trim()
        if (message.isEmpty()){
            binding.inputMessage.error = getString(R.string.required_field)
            return
        }

        messages.add(Message(message))
        binding.inputMessage.text?.clear()

        Toast.makeText(this,getString(R.string.add_success_message),Toast.LENGTH_LONG).show()

    }

    private fun messageListIntent(){
        val intent = Intent(this,MessagesActivity::class.java)
        intent.putExtra("messages", messages)
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            (data?.getSerializableExtra("messages") as? ArrayList<Message>)?.let {
                messages = it
            }
        }
    }
}