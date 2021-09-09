package com.example.apninews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listener : NewsClicked) : RecyclerView.Adapter<NewsViewHolder>() {

    private val items : ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener(){
            listener.onitemclicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentitem = items[position]
        holder.title.text = currentitem.title
        Glide.with(holder.itemView.context).load(currentitem.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews : ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()

    }
}



class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title : TextView = itemView.findViewById(R.id.title)
    val image : ImageView = itemView.findViewById(R.id.image)

}
interface NewsClicked{
    fun onitemclicked(items: News)
}