package my.edu.tarc.ok4umobile.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import my.edu.tarc.ok4umobile.R

class RgAdapter (private val registerList : List<Register>): RecyclerView.Adapter<RgAdapter.RgViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RgViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.register,parent,false)

        return RgViewHolder(itemView)
    }

    class RgViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val lblName: TextView = itemView.findViewById(R.id.lblName)
        val lblEmail: TextView = itemView.findViewById(R.id.lblEmail)
        val lblctc: TextView = itemView.findViewById(R.id.lblctc)
        val lbladdress: TextView = itemView.findViewById(R.id.lbladdress)
        val lbltransport: TextView = itemView.findViewById(R.id.lbltransport)
    }



    override fun onBindViewHolder(holder: RgViewHolder, position: Int) {
        val current = registerList[position]

        holder.lblName.text  = "Name :" + current.name
        holder.lblEmail.text  = "Email :" + current.email
        holder.lblctc.text  = "Phone Number :" + current.phNumber
        holder.lbladdress.text  = "Address : " + current.address
        holder.lbltransport.text  = "Transport" + current.transport
    }

    override fun getItemCount(): Int {
        return registerList.size
    }


}
