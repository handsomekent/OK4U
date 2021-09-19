package my.edu.tarc.ok4umobile

import my.edu.tarc.ok4umobile.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import com.google.android.gms.tasks.OnCompleteListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.firebase.auth.AuthResult

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentRegisterBinding
//import com.google.firebase.firestore.auth.User as User

private lateinit var auth: FirebaseAuth;


class zRegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)






        binding.btnRegister.setOnClickListener(){
            val pass : String = binding.txtPassword.text.toString().trim{it<=' '}
            val conf : String = binding.txtConfirm.text.toString()

            if(pass.compareTo(conf) == 0){
                val name : String = binding.txtName.text.toString()
                val email : String = binding.txtEmail.text.toString().trim{it<=' '}
                val temp : Int  = binding.radGender.checkedRadioButtonId
                val rad = view?.findViewById<RadioButton>(temp)
                val gender = rad?.text.toString()
                val address=binding.txtAddress.text.toString()
                val phoneNum=binding.txtPhoneNum.text.toString()
                val radio_user_type:Int =binding.radUserType.checkedRadioButtonId
                val radio_usertype= view?.findViewById<RadioButton>(radio_user_type)
                val user_type= radio_usertype?.text.toString()
                var id:Int =0


                auth = Firebase.auth

                val database = Firebase.database("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                val ref = database.getReference("Users")

                ref.addListenerForSingleValueEvent(object : ValueEventListener
                    {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            id= snapshot.childrenCount.toInt()+1
                            var idS =id.toString()
                            val newuser = User(idS.toString(),name, gender, pass, email,address,phoneNum,user_type)
                            ref.child(idS).setValue(newuser)
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }

                    }
                )




                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                      //  val firebaseUser: FirebaseUser = task.result!!.user!!
                        Toast.makeText(this.context, "Register Successful", Toast.LENGTH_LONG).show()
                        Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)

                    }else{
                        Toast.makeText(this.context, "Register Fail", Toast.LENGTH_LONG).show()
                    }

                }


                //         Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)
            }else{
                Toast.makeText(this.context, "Both Passwords are not same!", Toast.LENGTH_SHORT).show()
            }



            //database.child("users").child("001").setValue(newuser)
        }

        return binding.root
    }
}