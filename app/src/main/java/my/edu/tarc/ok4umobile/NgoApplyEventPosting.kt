package my.edu.tarc.ok4umobile

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import my.edu.tarc.ok4umobile.databinding.FragmentNgoApplyEventPostingFragmenttBinding
import java.util.*


class ApplyEventPostingFragmentt : Fragment() {

    private lateinit var binding: FragmentNgoApplyEventPostingFragmenttBinding
    lateinit var ImageUri: Uri


    private val pickImages =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri?
            ->
            ImageUri = uri!!
            uri?.let { it ->
                Picasso.with(context).load(it).resize(800, 800).into(binding.ivUpload)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_ngo_apply_event_posting_fragmentt,
            container,
            false
        )

        val sharedPref = this.activity?.getSharedPreferences(
            "pref", Context.MODE_PRIVATE
        )
        var name: String = sharedPref?.getString("name", "No Data").toString()
        var email: String = sharedPref?.getString("email", "No Data").toString()

        val databaseuser =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("events")

        binding.btnUpload.setOnClickListener() {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), 2000
                )


            } else {
                val cameraIntent = Intent(Intent.ACTION_GET_CONTENT)
                cameraIntent.type = "image/*"

                pickImages.launch("image/")
            }

            binding.btnRegister.setOnClickListener() {

                val ngoName: String = binding.tvInputNgoName.text.toString()
                val eventName: String = binding.tvInputEventName.text.toString()
                val eventDescription = binding.tvInputDescription.text.toString()
                val date = binding.tvInputDate.text.toString()
                val location = binding.tvInputLocation.text.toString()
                val availableSlot = binding.tvInputSlot.text.toString()


                var imageLink: String


                val database =
                    Firebase.database("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                val ref = database.getReference("events")

                val storageReference =
                    FirebaseStorage.getInstance()
                        .getReference("images/" + UUID.randomUUID().toString())



                Toast.makeText(this.context, "Event Register Success", Toast.LENGTH_LONG).show()
                storageReference.putFile(ImageUri).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.ivUpload.setImageURI(null)
                        imageLink = task.result.toString()
                        Toast.makeText(this.context, "Upload Success", Toast.LENGTH_LONG).show()

                        val newevent = Event1(
                            ngoName,
                            eventName,
                            eventDescription,
                            date,
                            location,
                            "",
                            availableSlot,
                            "0",
                            "",
                            imageLink
                        )
                        ref.child(eventName).setValue(newevent)
                    } else {
                        Toast.makeText(this.context, "Upload Fail", Toast.LENGTH_LONG).show()
                    }

                    Toast.makeText(this.context, "Event Register Success", Toast.LENGTH_LONG).show()

//                Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)


                }


            }
        }
        return binding.root
    }
}
