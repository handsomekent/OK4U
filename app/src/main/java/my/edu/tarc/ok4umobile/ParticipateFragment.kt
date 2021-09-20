package my.edu.tarc.ok4umobile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.R
import my.edu.tarc.ok4umobile.data.Event
import my.edu.tarc.ok4umobile.data.KzAdapter
import my.edu.tarc.ok4umobile.data.Register
import my.edu.tarc.ok4umobile.data.RgAdapter
import my.edu.tarc.ok4umobile.databinding.FragmentLoginBinding
import my.edu.tarc.ok4umobile.databinding.FragmentParticipateBinding

class ParticipateFragment : Fragment() {
    private lateinit var rgRecyclerView : RecyclerView
    private lateinit var rgArrayList : ArrayList<Register>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_participate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = this.arguments
        val inputData = args?.get("eventTitle")
        val db = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("events").child("$inputData").child("register")

        rgRecyclerView = view.findViewById(R.id.registerRecyclerView)
        rgRecyclerView.layoutManager = LinearLayoutManager(this.context)
        rgRecyclerView.setHasFixedSize(true)
        rgArrayList = arrayListOf<Register>()

        db.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (snap in snapshot.children){
                        val register = snap.getValue(Register::class.java)
                        rgArrayList.add(register!!)

                    }
                    rgRecyclerView.adapter = RgAdapter(rgArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("Error","Not Found")
            }

        })

    }

}