package my.edu.tarc.ok4umobile.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ok4umobile.R

class MyAdapter (private val eventList : List<Event>): RecyclerView.Adapter<MyAdapter.myViewHolder>() {

    class myViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val tvEventTitle:TextView = itemView.findViewById(R.id.tvEventTitle)
        val tvPostDay : TextView = itemView.findViewById(R.id.tvPostDay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragmentt_event_list,parent,false)


        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val current = eventList[position]
        holder.tvEventTitle.text  = current.eventTitle
        holder.tvPostDay.text = current.postDay
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}