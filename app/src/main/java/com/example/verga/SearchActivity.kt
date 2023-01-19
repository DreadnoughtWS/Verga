package com.example.verga

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verga.databinding.ActivityMainBinding
import com.example.verga.databinding.ActivitySearchBinding
import com.google.firebase.database.*

class SearchActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var datarecyclerview : RecyclerView
    private lateinit var data : ArrayList<ItemsViewModel>
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        // getting the recyclerview by its id
        datarecyclerview = findViewById<RecyclerView>(R.id.search_view)

        // this creates a vertical layout Manager
        datarecyclerview.layoutManager = LinearLayoutManager(this)

        datarecyclerview.setHasFixedSize(true)
        // ArrayList of class ItemsViewModel
        data = arrayListOf<ItemsViewModel>()
        getWebData()
    }


    private fun getWebData(){

        dbref = FirebaseDatabase.getInstance("https://verga2-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Web_Data")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (Web_DataSnapshot in snapshot.children){

                        val webdata = Web_DataSnapshot.getValue(ItemsViewModel::class.java)
                        data.add(webdata!!)
                    }
                    var adapter2 = CustomAdapter(data)
                    datarecyclerview.adapter = adapter2
                    adapter2.setOnItemClickListener(object : CustomAdapter.onItemClickListener{
                        override fun onItemClick(position: Int){
                            val intent = Intent(this@SearchActivity, WebRegActivity::class.java)
                            intent.putExtra("image", data[position].Image)
                            intent.putExtra("judul", data[position].Judul)
                            intent.putExtra("price", data[position].Price)
                            intent.putExtra("tanggal", data[position].Tanggal)
                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}