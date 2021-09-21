package my.edu.tarc.ok4umobile.data

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import my.edu.tarc.ok4umobile.R
import my.edu.tarc.ok4umobile.ParticipateFragment

class KzAdapter (private val eventList : List<Event>): RecyclerView.Adapter<KzAdapter.myViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_ngo_event,parent,false)


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
        holder.tvEventTitle.text  = "Event Name: " +current.title
        holder.tvPostDay.text = "Event Date: " +current.date
        Glide.with(holder.img.context).load(current.imageUrl).into(holder.img)

        holder.itemView.setOnClickListener(){
            val bundle = Bundle()
            bundle.putString("eventTitle",current.title)

            val fragment = ParticipateFragment()
            fragment.arguments = bundle
            Navigation.findNavController(it).navigate(R.id.action_ngoEventlistFragment_to_participateFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
        return eventList.size
    }


}