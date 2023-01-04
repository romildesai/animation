package com.example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class user_list : AppCompatActivity() {

    private lateinit var Dbref : DatabaseReference
    private lateinit var recycle:RecyclerView
    private lateinit var arrylist : ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        recycle = findViewById(R.id.userlist)
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.setHasFixedSize(true)

        arrylist = arrayListOf<User>()
        getUserData()
    }

    private fun getUserData() {
        Dbref = FirebaseDatabase.getInstance().getReference("employe")

        Dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists())
                    {
                        for (userSnapshot in snapshot.children)
                        {
                            val user =  userSnapshot.getValue(User::class.java)
                            arrylist.add(user!!)
                        }
                        recycle.adapter = MyAdepter(arrylist)
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}