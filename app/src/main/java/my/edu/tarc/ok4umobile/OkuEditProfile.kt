package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import my.edu.tarc.ok4umobile.databinding.FragmentOkuEditProfileBinding


class OkuEditProfile : Fragment() {

    private lateinit var binding: FragmentOkuEditProfileBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_oku_edit_profile, container, false)

        val sharedPref : SharedPreferences? = this.activity?.getSharedPreferences(
            "kotlinsharedpreference", Context.MODE_PRIVATE)
        var name: String =sharedPref?.getString("name","No Data").toString()
        var email: String =sharedPref?.getString("email","No Data").toString()



        // var s:String = name
        val databaseuser  = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
        Log.i("MainActivity", "1" )//test only

databaseuser.addValueEventListener(object : ValueEventListener{
    override fun onDataChange(snapshot: DataSnapshot) {
        for (i in snapshot.children) {

            var namedb=i.child("name").getValue().toString()
            var genderdb=i.child("gender").getValue().toString()
            var addressdb=i.child("address").getValue().toString()
            var phonedb=i.child("phoneNumber").getValue().toString()
            var emaildb=i.child("email").getValue().toString()
            Log.i("MainActivity", ""+email )
            Log.i("MainActivity", ""+namedb )//test only
            if(email.equals(emaildb)){
                Log.i("MainActivity", "3" )//test only

                binding.txtOkuName.setText(namedb)
                binding.editGender.setText(genderdb)
                binding.editTextPhone.setText(phonedb)
                binding.editTextPostalAddress.setText(addressdb)
                binding.btnUpdateProfile.setOnClickListener(){
                    databaseuser.child(email).child("name").setValue(binding.txtOkuName.text.toString())
                    databaseuser.child(email).child("gender").setValue(binding.editGender.text.toString())
                    databaseuser.child(email).child("address").setValue(binding.editTextPostalAddress.text.toString())
                    databaseuser.child(email).child("phoneNumber").setValue(binding.editTextPhone.text.toString())
                    Toast.makeText(context, "Updated Successful", Toast.LENGTH_LONG) //   }

                }
                //  binding.editGender.setText()
            }

        }
    }

    override fun onCancelled(error: DatabaseError) {
      //  TODO("Not yet implemented")
    }


}

)

        // Inflate the layout for this fragment
        return binding.root
    }

}