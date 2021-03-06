package my.edu.tarc.ok4umobile.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import my.edu.tarc.ok4umobile.OkuEventDetails
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
        val tvEventTitle:TextView = itemView.findViewById(R.id.tvEventName)
        val tvPostDay : TextView = itemView.findViewById(R.id.tvEventDate)
        val img : ImageView = itemView.findViewById(R.id.imageView)
        val slot : TextView = itemView.findViewById(R.id.tvPartSlot)

        init{
            itemView.setOnClickListener(){
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
            val current = eventList[position]
            if(current.status == "1"){
                holder.tvEventTitle.text  = "Event Name: " +current.title
                holder.tvPostDay.text = "Event Date: " +current.date
                holder.slot.text = "Participant: " +current.currentSlot
                Glide.with(holder.img.context).load(current.imageUrl).into(holder.img)
            }else if (current.status == "0"){
                holder.itemView.visibility = View.GONE
            }

        holder.itemView.setOnClickListener(){
            val bundle = Bundle()
            bundle.putString("eventTitle",current.title)
            bundle.putString("eventDay",current.date)
            bundle.putString("location",current.location)
            bundle.putString("ngoName",current.ngoName)
            bundle.putString("desc",current.eventDesc)
            bundle.putString("slot",current.currentSlot)
            bundle.putString("imageUrl",current.imageUrl)

            val fragment = OkuEventDetails()
            fragment.arguments = bundle
            Navigation.findNavController(it).navigate(R.id.action_event_to_okuEventDetails,bundle)
        }

    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}
