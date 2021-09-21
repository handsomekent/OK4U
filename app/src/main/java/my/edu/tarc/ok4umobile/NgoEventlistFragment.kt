package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import my.edu.tarc.ok4umobile.data.Event
import my.edu.tarc.ok4umobile.data.KzAdapter
import my.edu.tarc.ok4umobile.data.MyAdapter

class NgoEventlistFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventArrayList: ArrayList<Event>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_ngo_eventlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref : SharedPreferences? = this.activity?.getSharedPreferences("kotlinsharedpreference", Context.MODE_PRIVATE)
        var name: String =sharedPref?.getString("name","No Data").toString()
        var email: String =sharedPref?.getString("email","No Data").toString()
        var id1: String =sharedPref?.getString("id","No Data").toString()

        Log.i("beta", "$email" )//test only

        eventRecyclerView = view.findViewById(R.id.eventRecyclerView)
        eventRecyclerView.layoutManager = LinearLayoutManager(this.context)
        eventRecyclerView.setHasFixedSize(true)
        eventArrayList = arrayListOf<Event>()

        getEventData(id1)
    }

    private fun getEventData(id1: String) {
        database = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("events")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val eve = snap.getValue(Event::class.java)
                        if(id1.equals(eve?.ngoId) == true ){
                            eventArrayList.add(eve!!)
                        }

                    }
                    var adapter = KzAdapter(eventArrayList)
                    eventRecyclerView.adapter = adapter
                    adapter.setonItemClickListener(object :
                        KzAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
//                            val bundle = Bundle()
//                            bundle.putString("eventTitle",eventArrayList[position].title)
//                            bundle.putString("eventDay",eventArrayList[position].date)
//                            bundle.putString("location",eventArrayList[position].location)
//                            bundle.putString("ngoName",eventArrayList[position].ngoName)
//                            bundle.putString("desc",eventArrayList[position].eventDesc)
//                            bundle.putString("slot",eventArrayList[position].currentSlot)
//                            bundle.putString("imageUrl",eventArrayList[position].imageUrl)
//
//                            val fragment = OkuEventDetails()
//                            fragment.arguments = bundle
//                            fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment_content_drawer,fragment)?.commit()
//                            Log.i("pos", "$position")
                        }
                    })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.i("Error", "No Event Found")
            }
        })

    }
}