package my.edu.tarc.ok4umobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import my.edu.tarc.ok4umobile.databinding.FragmentOkuSuggestfacilitiesBinding
import my.edu.tarc.ok4umobile.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var fusedLocation : FusedLocationProviderClient

/**
 * A simple [Fragment] subclass.
 * Use the [Suggestfacilities.newInstance] factory method to
 * create an instance of this fragment.
 */
class Suggestfacilities : Fragment(),
    ActivityCompat.OnRequestPermissionsResultCallback {
    // TODO: Rename and change types of parameters
    private var permissionDenied = false
    private lateinit var binding: FragmentOkuSuggestfacilitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_oku_suggestfacilities,
            container,
            false
        )


        val database =
            Firebase.database("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
        val ref = database.getReference("Facilities")

        binding.btnSuggest.setOnClickListener() {

            val name: String = binding.txtFacilityName.text.toString()
            val desc: String = binding.txtDesc.text.toString()
            val location: String = binding.txtLocation.text.toString()

            val radtemp: Int = binding.radgrp.checkedRadioButtonId
            val rad = view?.findViewById<RadioButton>(radtemp)
            var radtxt = rad?.text.toString()

            if (radtxt == "Facility") {
                radtxt = "1"
            } else if (radtxt == "Service") {
                radtxt = "0"
            }

            binding.btnLocation.setOnClickListener() {


            }


            //   val newFacility = Facilities(radtxt, la, lo,name,desc,"0")

            //   ref.child(name).setValue(newFacility)
        }


        // Inflate the layout for this fragment
        return binding.root
    }


    companion object {


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Suggestfacilities.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Suggestfacilities().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}