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
import my.edu.tarc.ok4umobile.data.Event
import my.edu.tarc.ok4umobile.data.VerifyEventAdapter


class verifyEvent : Fragment() {
    private lateinit var db: DatabaseReference
    private lateinit var verifyEventRecyclerView: RecyclerView
    private lateinit var verifyFacilityArrayList : ArrayList<Event>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        verifyEventRecyclerView = view.findViewById(R.id.verifyEventRecyclerView)
        verifyEventRecyclerView.layoutManager = LinearLayoutManager(this.context)
        verifyEventRecyclerView.setHasFixedSize(true)
        verifyFacilityArrayList = arrayListOf<Event>()

        getVerifyEventData()

    }

    private fun getVerifyEventData() {
        db =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("events")

        db.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(snap in snapshot.children){
                        val verifyEvent = snap.getValue(Event::class.java)
                        verifyFacilityArrayList.add(verifyEvent!!)
                    }
                    var verifyEventAdap = VerifyEventAdapter(verifyFacilityArrayList)
                    verifyEventRecyclerView.adapter = verifyEventAdap
                    verifyEventAdap.setonItemClickListener(object : VerifyEventAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Error", "No Event for Verify ")
            }

        })
    }
}