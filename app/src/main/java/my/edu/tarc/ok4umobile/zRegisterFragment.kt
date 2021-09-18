package my.edu.tarc.ok4umobile

import my.edu.tarc.ok4umobile.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentRegisterBinding
//import com.google.firebase.firestore.auth.User as User



class zRegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)


        binding.btnRegister.setOnClickListener(){
            val pass : String = binding.txtPassword.text.toString()
            val conf : String = binding.txtConfirm.text.toString()

            if(pass.compareTo(conf) == 0){
                val name : String = binding.txtName.text.toString()
                val email : String = binding.txtEmail.text.toString()
                val temp : Int  = binding.radGender.checkedRadioButtonId
                val rad = view?.findViewById<RadioButton>(temp)
                val gender = rad?.text.toString()
                val address=binding.txtAddress.text.toString()
                val phoneNum=binding.txtPhoneNum.text.toString()
                val radio_user_type:Int =binding.radUserType.checkedRadioButtonId
                val radio_usertype= view?.findViewById<RadioButton>(radio_user_type)
                val user_type= radio_usertype?.text.toString()


               // val database = Firebase.database("https://ok4u-bc86a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                val database = Firebase.database("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                val ref = database.getReference("Users")


               // val newuser = User("003", name, gender, pass, email,user_type)
               // ref.child("003").setValue(newuser)
                val newuser = User(name, gender, pass, email,address,phoneNum,user_type)



                ref.child(email).setValue(newuser)
                Toast.makeText(this.context, "Register Success", Toast.LENGTH_LONG).show()

                Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)
            }else{
                Toast.makeText(this.context, "Incorrect Password", Toast.LENGTH_SHORT).show()
            }
            Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)


            //database.child("users").child("001").setValue(newuser)
        }

        return binding.root
    }
}