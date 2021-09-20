package my.edu.tarc.ok4umobile.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ok4umobile.AdminVerifyEventPosting
import my.edu.tarc.ok4umobile.R

class VerifyEventAdapter(private val verEventList: List<Event>) :
    RecyclerView.Adapter<VerifyEventAdapter.verifyEventViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class verifyEventViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val tvVerTitle: TextView = itemView.findViewById(R.id.tvVerEvent)
        val tvVerDate: TextView = itemView.findViewById(R.id.tvVerDate)

        init {
            itemView.setOnClickListener() {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): verifyEventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_verify_event_list, parent, false
        )

        return verifyEventViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: verifyEventViewHolder, position: Int) {
        val current = verEventList[position]
        if (current.status == "0") {
            holder.tvVerTitle.text = "Event Name: " + current.title
            holder.tvVerDate.text = "Event Date: " + current.date
        } else {
            holder.itemView.visibility = View.GONE
        }
        holder.itemView.setOnClickListener() {
            val bundle = Bundle()
            bundle.putString("eventTitle",current.title)
            bundle.putString("eventDay",current.date)
            bundle.putString("location",current.location)
            bundle.putString("ngoName",current.ngoName)
            bundle.putString("desc",current.eventDesc)
            bundle.putString("slot",current.currentSlot)
            bundle.putString("imageUrl",current.imageUrl)
            bundle.putString("maxSlot",current.maxSlot)
            bundle.putString("ngoId",current.ngoId)


            val fragment = AdminVerifyEventPosting()
            fragment.arguments = bundle
            Navigation.findNavController(it).navigate(R.id.action_verifyEvent_to_adminVerifyEventPosting,bundle)

        }
    }

    override fun getItemCount(): Int {
        return verEventList.size
    }


}