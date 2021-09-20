package my.edu.tarc.ok4umobile.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ok4umobile.R

class NotificationAdapter (private val notif : List<Notification>) : RecyclerView.Adapter<NotificationAdapter.notifViewHolder>() {

    class notifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNotif :TextView = itemView.findViewById(R.id.tvNotif)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notifViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.notification,parent,false)

        return notifViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: notifViewHolder, position: Int) {
        val currentPosition = notif[position]
        holder.tvNotif.text = currentPosition.content
    }

    override fun getItemCount(): Int {
        return notif.size
    }


}