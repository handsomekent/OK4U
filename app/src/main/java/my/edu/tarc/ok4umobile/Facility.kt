package my.edu.tarc.ok4umobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import my.edu.tarc.ok4umobile.data.Facility
import my.edu.tarc.ok4umobile.data.FacilityAdapter


class Facility : Fragment() {
    private lateinit var db: DatabaseReference
    private lateinit var facilityRecyclerView: RecyclerView
    private lateinit var facilityArrayList: ArrayList<Facility>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facility, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        facilityRecyclerView = view.findViewById(R.id.facilityRecyclerView)
        facilityRecyclerView.layoutManager = LinearLayoutManager(this.context)
        facilityRecyclerView.setHasFixedSize(true)
        facilityArrayList = arrayListOf<Facility>()

        getFacilityData()
    }

    private fun getFacilityData() {
        db =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("PendingFacility")

        db.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(snap in snapshot.children){
                        val fac = snap.getValue(Facility::class.java)
                        facilityArrayList.add(fac!!)
                    }
                    var facilityAdap = FacilityAdapter(facilityArrayList)
                    facilityRecyclerView.adapter = facilityAdap
                    facilityAdap.setonItemClickListener(object : FacilityAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Error", "No Facility Found")
            }

        })
    }
}