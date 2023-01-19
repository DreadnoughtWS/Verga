package com.example.verga

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class WebRegActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_reg)


        val image : ImageView = findViewById(R.id.image)
        val judul : TextView = findViewById(R.id.judul)
        val price : TextView = findViewById(R.id.price)
        val tanggal : TextView = findViewById(R.id.tanggal)

        val bundle : Bundle? = intent.extras

        val imageid = bundle?.getString("image")
        val judulid = bundle?.getString("judul")
        val priceid = bundle?.getString("price")
        val tanggalid = bundle?.getString("tanggal")

        Glide.with(image).load(imageid).into(image)
        judul.text = judulid
        price.text = priceid
        tanggal.text = tanggalid
    }
}