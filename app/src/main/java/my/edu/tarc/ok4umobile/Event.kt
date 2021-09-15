package my.edu.tarc.ok4umobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.data.Event
import my.edu.tarc.ok4umobile.data.MyAdapter

class Event : AppCompatActivity() {
    //private lateinit var db : FirebaseFirestore
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val eventsRef : CollectionReference = db.collection("events")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        //db = FirebaseFirestore.getInstance()
        //val eventsRef = db.collection("events")
        eventsRef.addSnapshotListener{ snapshot, exception ->
            if(exception != null || snapshot == null){
                Log.d("TAG","Exception",exception)
                return@addSnapshotListener
            }
            for(document in snapshot.documents){
                Log.i("Testing","Document ${document.id} : ${document.data}")
            }

        }

        val eventList : List<my.edu.tarc.ok4umobile.data.Event> = listOf(
            Event("Cycling","2 days ago"),
            Event("Food Giving","5 days ago"),Event("Give Away","1 days ago"))

        val eventAdapter = MyAdapter(eventList)
        val recycleView : RecyclerView = findViewById(R.id.eventRecycleView)


        recycleView.adapter = eventAdapter
        recycleView.setHasFixedSize(true)
    }
}