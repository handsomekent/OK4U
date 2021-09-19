package my.edu.tarc.ok4umobile.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

        }
    }

    override fun getItemCount(): Int {
        return verEventList.size
    }


}