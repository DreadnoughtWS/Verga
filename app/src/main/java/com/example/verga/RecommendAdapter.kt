package com.example.verga

import android.app.Activity
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.security.AccessController.getContext

class RecommendAdapter(private val rList: List<RecommendViewModel>) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    private lateinit var rListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position : Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        rListener = listener
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recommend_list, parent, false)

        return ViewHolder(view,rListener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val RecommendViewModel = rList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = RecommendViewModel.Tanggal
        holder.textView2.text = RecommendViewModel.Price
        Glide.with(holder.itemView).load(RecommendViewModel.Image).into(holder.imageview)
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return rList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View, listener : onItemClickListener) : RecyclerView.ViewHolder(ItemView) {

        val textView: TextView = itemView.findViewById(R.id.dateView)
        val textView2: TextView = itemView.findViewById(R.id.price_view)
        val imageview: ImageView = itemView.findViewById(R.id.poster)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}