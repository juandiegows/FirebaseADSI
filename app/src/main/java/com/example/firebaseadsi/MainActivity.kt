package com.example.firebaseadsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseadsi.databinding.ActivityMainBinding
import com.example.firebaseadsi.models.Gender
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fireDatabase: FirebaseDatabase
    lateinit var generoRef: DatabaseReference
    lateinit var generoRef3: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        fireDatabase = FirebaseDatabase.getInstance()
        generoRef = fireDatabase.getReference("categoria")
        generoRef3 = fireDatabase.getReference("categoria")
        setContentView(binding.root)
       create()
        read()
    }

    fun create() {
        var id = generoRef.push().key
        generoRef.child(id!!).setValue(Gender(id!!, "masculino"))
    }

    fun read() {
        generoRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { snap ->

                    var gender = snap.getValue(Gender::class.java)
                    Toast.makeText(
                        this@MainActivity,
                        "$ ${gender!!.id} ${gender!!.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun update() {
        var id = "-NhfxC6q7D6NnjifaZzo"
        var nuevo = HashMap<String, Any>()
        nuevo["name"] = "Masculino"
        generoRef.child(id).updateChildren(nuevo)

    }

    fun delete() {
        var id = "-NhfxC6q7D6NnjifaZzo"
        generoRef.child(id).removeValue()
    }

}

