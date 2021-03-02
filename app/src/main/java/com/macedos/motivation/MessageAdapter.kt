package com.macedos.motivation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.macedos.motivation.model.Message

//Gerenciar a lista criada
class MessageAdapter(
        var messages: ArrayList<Message>,
        private var listener: MessageListener) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.message_random)
        val contentCard: MaterialCardView = view.findViewById(R.id.card_item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.message.text = messages[position].text
        holder.contentCard.isClickable = messages[position].isRemovable
        holder.contentCard.isFocusable = messages[position].isRemovable

        if (messages[position].isRemovable){
            holder.contentCard.setOnLongClickListener {
                listener.onDelete(messages[position])
                true
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