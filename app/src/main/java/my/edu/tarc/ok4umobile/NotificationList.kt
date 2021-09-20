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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.ok4umobile.data.Notification
import my.edu.tarc.ok4umobile.data.NotificationAdapter


class NotificationList : Fragment() {
    private lateinit var notifRecyclerView : RecyclerView
    private lateinit var notifArrayList: ArrayList<Notification>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref: SharedPreferences? = this.activity?.getSharedPreferences(
            "kotlinsharedpreference", Context.MODE_PRIVATE
        )
        var name: String = sharedPref?.getString("name", "No Data").toString()
        var email: String = sharedPref?.getString("email", "No Data").toString()
        var userType: String = sharedPref?.getString("userType", "No Data").toString()
        var id : String = sharedPref?.getString("id","No Data").toString()

        val db =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Users").child("$id").child("notification")

        notifRecyclerView = view.findViewById(R.id.notifyRecyclerView)
        notifRecyclerView.layoutManager = LinearLayoutManager(this.context)
        notifRecyclerView.setHasFixedSize(true)
        notifArrayList = arrayListOf<Notification>()

        db.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (snap in snapshot.children){
                        val notif = snap.getValue(Notification::class.java)
                        notifArrayList.add(notif!!)

                    }
                    notifRecyclerView.adapter = NotificationAdapter(notifArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Error","Not Found")
            }

        })





    }
}