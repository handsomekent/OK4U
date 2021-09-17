package my.edu.tarc.ok4umobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text


class OkuEventDetails : Fragment() {


    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_oku_event_details, container, false)


        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvTitle : TextView = view.findViewById(R.id.tvEventTitle2)
        val tvPost : TextView = view.findViewById(R.id.tvPostDay2)
        val tvDesc : TextView = view.findViewById(R.id.tvEventDescription)
        val tvOrganizer : TextView = view.findViewById(R.id.tvOrganizer)
        val tvLocation : TextView = view.findViewById(R.id.tvLocation)
        val tvSlot : TextView = view.findViewById(R.id.tvCurrentSlot)
        val imgEvent : ImageView = view.findViewById(R.id.imageView2)

        val args = this.arguments
        val inputData = args?.get("eventTitle")
        val inputData2 = args?.get("eventDay")
        val inputData3 = args?.get("location")
        val inputData4 = args?.get("ngoName")
        val inputData5 = args?.get("desc")
        val inputData6 = args?.get("slot")
        val inputData7 = args?.get("imageUrl")


        tvTitle.text = "Event Title: " + inputData.toString()
        tvPost.text = "Event Date: " + inputData2.toString()
        tvLocation.text = "Event Location: " + inputData3.toString()
        tvOrganizer.text = "Event Organizer: " + inputData4.toString()
        tvDesc.text = "Event Description: " + inputData5.toString()
        tvSlot.text = "Event Slot: " + inputData6.toString()
        Glide.with(imgEvent.context).load(inputData7).into(imgEvent)


    }

}