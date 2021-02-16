package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var studentname: EditText
    lateinit var fathername: EditText
    lateinit var cityname: EditText
    lateinit var ratingbar: RatingBar
    lateinit var savebutton: Button
    lateinit var ref: DatabaseReference
    lateinit var hostellist:MutableList<Hostel>
    lateinit var listview:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hostellist= mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("hostels")
        studentname = findViewById(R.id.ETstudentName)
        fathername = findViewById(R.id.ETfatherName)
        cityname = findViewById(R.id.ETcityName)
        ratingbar = findViewById(R.id.ratingBar)
        savebutton = findViewById(R.id.button)
        listview=findViewById(R.id.listviewDisplay)

        savebutton.setOnClickListener {


            SaveHostel()

        }
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    hostellist.clear()
                    for (h in p0.children){
                        val hostel=h.getValue(Hostel::class.java)
                        hostellist.add(hostel!!)

                    }
                    val adapter=HostelAdapter(applicationContext,R.layout.hosteles,hostellist)
                    listview.adapter=adapter

                }



            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun SaveHostel() {

        val stdname = studentname.text.toString().trim()
        val ftrname = fathername.text.toString().trim()
        val ctyname = cityname.text.toString().trim()

        if (stdname.isEmpty()) {
            studentname.error = "please enter the name "
            return

        }

        if (ftrname.isEmpty()) {
            fathername.error = "please enter the father name "
            return
        }


        if (ctyname.isEmpty()) {
            cityname.error = "please enter the city  name "
            return


        }


        val hostelid = ref.push().key.toString()
        val hostel = Hostel(hostelid, stdname, ftrname, ctyname, ratingbar.numStars)

        ref.child(hostelid).setValue(hostel).addOnSuccessListener {

            Toast.makeText(applicationContext, "record inserted sucessfully", Toast.LENGTH_SHORT)
                .show()
        }
    }
}





