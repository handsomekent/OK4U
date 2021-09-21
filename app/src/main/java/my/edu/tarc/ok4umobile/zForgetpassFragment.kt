package my.edu.tarc.ok4umobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentForgetpassBinding

class zForgetpassFragment : Fragment() {
    private lateinit var binding: FragmentForgetpassBinding
    private lateinit var auth: FirebaseAuth;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgetpass, container, false)
        auth= Firebase.auth


        binding.btna.setOnClickListener(){
           val email= binding.txtEmailForget.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this.context, "Please fill in the email.", Toast.LENGTH_LONG).show()

            }else{
                auth.sendPasswordResetEmail(email).addOnCompleteListener{task->

                    if(task.isSuccessful){
                        Toast.makeText(this.context, "Reset password had been sent to email.", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this.context, "Error", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
//fAuth.

        return binding.root
    }

}