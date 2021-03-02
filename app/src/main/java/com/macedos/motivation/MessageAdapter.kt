package com.macedos.motivation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.macedos.motivation.model.Message

//Gerenciar a lista criada
class MessageAdapter(
        var messages: ArrayList<Message>,
        private var listener: MessageListener) :
        RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.message_random)
        val deleteBtn: ExtendedFloatingActionButton = view.findViewById(R.id.delete_btn)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.message.text = messages[position].text
        holder.deleteBtn.visibility = if (messages[position].isRemovable) View.VISIBLE else View.GONE
        if (messages[position].isRemovable) {
            holder.deleteBtn.setOnClickListener {
                listener.onDelete(messages[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_messagee, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    interface MessageListener {
        fun onDelete(message: Message)
    }
}