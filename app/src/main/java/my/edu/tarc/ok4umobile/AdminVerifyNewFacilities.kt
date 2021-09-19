package my.edu.tarc.ok4umobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class AdminVerifyNewFacilities : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_verify_new_facilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnApprove = view.findViewById<Button>(R.id.btnApprove)
        val btnReject = view.findViewById<Button>(R.id.btnReject)

        val tvFacTitle: TextView = view.findViewById(R.id.tvFacTitle)
        val tvFacDesc: TextView = view.findViewById(R.id.tvFacDesc)
        val tvFacType: TextView = view.findViewById(R.id.tvFacType)
        val tvFacLat: TextView = view.findViewById(R.id.tvFacLat)
        val tvFacLong: TextView = view.findViewById(R.id.tvFacLong)

        val args = this.arguments
        val inputData = args?.get("facTitle")
        val inputData1 = args?.get("facDesc")
        val inputData2 = args?.get("facType")
        val inputData3 = args?.get("facLat")
        val inputData4 = args?.get("facLong")

        tvFacTitle.text = "Facility Name: " + inputData.toString()
        tvFacDesc.text = "Facility Description: " + inputData1.toString()
        if(inputData2 == "1"){
            tvFacType.text = "Facility Type: Facility"

        }else{
            tvFacType.text = "Facility Type: Service"

        }
        tvFacLat.text = "Facility Latitude: " + inputData3.toString()
        tvFacLong.text = "Facility Longitude: " + inputData4.toString()

    }
}