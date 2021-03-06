package my.edu.tarc.ok4umobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Color
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentLoginBinding

class zLoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userList: ArrayList<User>

    private lateinit var auth: FirebaseAuth;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)



        binding.btnLogin.setOnClickListener() {
            val email1 = binding.txtEmail.text.toString()
            val pass1 = binding.txtPassword.text.toString()

            val database =
                FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .getReference("Users")
            auth=Firebase.auth
            if(email1!=""&&pass1!="") {
                auth.signInWithEmailAndPassword(email1, pass1)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            database.addValueEventListener(object : ValueEventListener {


                                override fun onDataChange(snapshot: DataSnapshot) {
                                    if (snapshot.exists()) {

                                        //change text failed

                                        for (i in snapshot.children) {
                                            var temp1 = i.child("id").getValue().toString()
                                            var temp2 = i.child("name").getValue().toString()
                                            var temp3 = i.child("gender").getValue().toString()
                                            var temp7 = i.child("address").getValue().toString()
                                            var temp8 = i.child("phoneNumber").getValue().toString()
                                            var temp5 = i.child("email").getValue().toString()
                                            var temp6 = i.child("userType").getValue().toString()

                                            if (email1.equals(temp5) == true) {
                                                Toast.makeText(context, "Sucess", Toast.LENGTH_LONG)
                                                val intent = Intent(
                                                    activity,
                                                    Drawer::class.java
                                                ) // open activity from fragment
                                                val sharedPrefFile = "kotlinsharedpreference"
                                                val sharedPref: SharedPreferences? =
                                                    activity?.getSharedPreferences(
                                                        sharedPrefFile, Context.MODE_PRIVATE
                                                    )
                                                val editor: SharedPreferences.Editor? =
                                                    sharedPref?.edit()
                                                editor?.putString("userType", temp6)
                                                editor?.putString("email", temp5)
                                                editor?.putString("name", temp2)
                                                editor?.putString("id", temp1)
                                                editor?.putString("address", temp7)
                                                editor?.putString("phoneNumber", temp8)

                                                editor?.apply()
                                                startActivity(intent)


                                            }
                                        }

                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                }
                            })
                        } else {
                            Toast.makeText(context, "Email or password is invalid.", Toast.LENGTH_LONG).show()
                        }

                    }
            }else {
                Toast.makeText(context, "Please fill in your email and password.", Toast.LENGTH_LONG).show()
            }


        }







        binding.lblRegister.setOnClickListener() {
            binding.lblRegister.setTextColor(Color.BLACK)
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.lblReset.setOnClickListener() {
            binding.lblRegister.setTextColor(Color.BLACK)
            Navigation.findNavController(it)
                .navigate(R.id.action_loginFragment_to_forgetpassFragment2)
        }

        return binding.root
    }
}