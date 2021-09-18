package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentNgoApplyEventPostingFragmenttBinding


class ApplyEventPostingFragmentt : Fragment() {

    private lateinit var binding: FragmentNgoApplyEventPostingFragmenttBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ngo_apply_event_posting_fragmentt, container, false)

        val sharedPref = this.activity?.getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )
        var name: String = sharedPref?.getString("name", "No Data").toString()
        var email: String = sharedPref?.getString("email", "No Data").toString()

        val databaseuser =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("events")

        binding.btnRegister.setOnClickListener(){

                val ngoName : String = binding.tvInputNgoName.text.toString()
                val eventName : String = binding.tvInputEventName.text.toString()
                val eventDescription=binding.tvInputDescription.text.toString()
                val date = binding.tvInputDate.text.toString()
                val location = binding.tvInputLocation.text.toString()
                val availableSlot = binding.tvInputSlot.text.toString()
                val status = "0"



                val database = Firebase.database("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                val ref = database.getReference("events")


                val newevent = Event1(ngoName, eventName, eventDescription, date, location,"",availableSlot,status,"")



                ref.child(eventName).setValue(newevent)
                Toast.makeText(this.context, "Event Register Success", Toast.LENGTH_LONG).show()

                Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)


        }

        return binding.root

    }
}