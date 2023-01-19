package com.example.verga

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*


class HomeFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var datarecyclerview : RecyclerView
    private lateinit var data_rec : ArrayList<RecommendViewModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var id = inflater.inflate(R.layout.fragment_home, container, false)


        // getting the recyclerview by its id
        datarecyclerview = id.findViewById<RecyclerView>(R.id.recommend_view)

        // this creates a vertical layout Manager
        datarecyclerview.layoutManager = LinearLayoutManager(activity,
            LinearLayoutManager.HORIZONTAL, false)

        datarecyclerview.setHasFixedSize(true)
        // ArrayList of class ItemsViewModel
        data_rec = arrayListOf<RecommendViewModel>()
        getWebData()

        return id
    }

    private fun getWebData(){

        dbref = FirebaseDatabase.getInstance("https://verga2-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Web_Data")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (Web_DataSnapshot in snapshot.children){

                        val webdata = Web_DataSnapshot.getValue(RecommendViewModel::class.java)
                        data_rec.add(webdata!!)
                    }
                    var adapter = RecommendAdapter(data_rec)
                    datarecyclerview.adapter = adapter
                    adapter.setOnItemClickListener(object : RecommendAdapter.onItemClickListener{
                        override fun onItemClick(position: Int){
                            activity?.let {
                                val intent = Intent(it, WebRegActivity::class.java)
                                intent.putExtra("image", data_rec[position].Image)
                                intent.putExtra("judul", data_rec[position].Judul)
                                intent.putExtra("price", data_rec[position].Price)
                                intent.putExtra("tanggal", data_rec[position].Tanggal)
                                startActivity(intent)
                            }
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