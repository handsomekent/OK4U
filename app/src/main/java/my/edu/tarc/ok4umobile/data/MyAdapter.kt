package my.edu.tarc.ok4umobile.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import my.edu.tarc.ok4umobile.R

class MyAdapter (private val eventList : List<Event>): RecyclerView.Adapter<MyAdapter.myViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragmentt_event_list,parent,false)


        return myViewHolder(itemView,mListener)
    }

    class myViewHolder(itemView:View,listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        val tvEventTitle:TextView = itemView.findViewById(R.id.tvEventTitle)
        val tvPostDay : TextView = itemView.findViewById(R.id.tvPostDay)
        val img : ImageView = itemView.findViewById(R.id.imageView)

        init{
            itemView.setOnClickListener(){
                listener.onItemClick(adapterPosition)
            }
        }
    }


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val current = eventList[position]
        holder.tvEventTitle.text  = current.title
        holder.tvPostDay.text = current.date
        Glide.with(holder.img.context).load(current.imageUrl).into(holder.img)

    }

    override fun getItemCount(): Int {
        return eventList.size
    }


}
