package com.example.animation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdepter(private val userList: ArrayList<User>) : RecyclerView.Adapter<MyAdepter.myviewholder> (){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_items,parent,false)

        return  myviewholder(itemView)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
      val currentitem = userList[position]

        holder.firstname.text=currentitem.firstname
        holder.lastname.text=currentitem.lastname
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    class myviewholder (itemView: View ): RecyclerView.ViewHolder(itemView){

        val firstname  : TextView = itemView.findViewById(R.id.firstname)
        val lastname: TextView = itemView.findViewById(R.id.lastname)


    }
}