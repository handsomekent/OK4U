package my.edu.tarc.ok4umobile.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import my.edu.tarc.ok4umobile.AdminVerifyNewFacilities
import my.edu.tarc.ok4umobile.R

class FacilityAdapter (private val facilityList : List<Facility>): RecyclerView.Adapter<FacilityAdapter.facilityViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setonItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class facilityViewHolder(itemView: View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView){
        val tvFacName : TextView = itemView.findViewById(R.id.tvFacilityName)
        val tvFacType : TextView = itemView.findViewById(R.id.tvFacilityType)
        //val tvFacStat : TextView = itemView.findViewById(R.id.tvFacStatus)

        init{
            itemView.setOnClickListener(){
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): facilityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_facility_list,parent,false)

        return facilityViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: facilityViewHolder, position: Int) {

        val current = facilityList[position]
        if(current.status == "0"){
            holder.tvFacName.text  = "Facility Name : "+ current.facilityName
            if(current.facility == "1"){
                holder.tvFacType.text = "Facility Type : "+"Facility"
            }else{
                holder.tvFacType.text = "Facility Type : "+"Service"
            }
        }else if (current.status == "1"){
            holder.itemView.visibility = View.GONE
        }
        holder.itemView.setOnClickListener(){
            val bundle = Bundle()
            bundle.putString("facTitle",current.facilityName)
            bundle.putString("facDesc",current.faciDesc)
            bundle.putString("facType",current.facility)
            bundle.putString("facLat",current.latitude)
            bundle.putString("facLong",current.longitude)
            bundle.putString("okuId",current.okuId)

            val fragment = AdminVerifyNewFacilities()
            fragment.arguments = bundle
            Navigation.findNavController(it).navigate(R.id.action_facility2_to_nav_verify_new_facilities,bundle)
        }
    }

    override fun getItemCount(): Int {
        return facilityList.size
    }
}