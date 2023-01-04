package com.example.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var fname : EditText
    private  lateinit var lname : EditText
    private lateinit var btn : Button

    private lateinit var Dbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        fname = findViewById(R.id.fname)
        lname = findViewById(R.id.lname)
        btn = findViewById(R.id.btnsave)


        Dbref = FirebaseDatabase.getInstance().getReference("employe")

        btn.setOnClickListener {
            saveEmployeeData()
        }


    }

    private fun saveEmployeeData() {


        val fn = fname.text.toString();
        val ln = lname.text.toString();

        if (fn.isEmpty()){
            fname.error="please enter first name"
        }
        if(ln.isEmpty())
        {
            lname.error="please enter last name"
        }

        val eid = Dbref.push().key!!

        val employye = employeemodel(eid,fn,ln)

        Dbref.child(eid).setValue(employye)
            .addOnCompleteListener{
                Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show()
                }
            .addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
            }
            }

    }
