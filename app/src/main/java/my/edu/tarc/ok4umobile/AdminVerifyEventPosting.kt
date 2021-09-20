package my.edu.tarc.ok4umobile

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import com.google.firebase.database.*


class AdminVerifyEventPosting : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_verify_event_posting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var Id:Int = 0
        val btnApprove = view.findViewById<Button>(R.id.btnApprove)
        val btnReject = view.findViewById<Button>(R.id.btnReject)

        val tvVerTitle: TextView = view.findViewById(R.id.tvVerifyEventTitle)
        val tvVerDate: TextView = view.findViewById(R.id.tvVerifyEventDate)
        val tvVerDesc: TextView = view.findViewById(R.id.tvVerifyEventDesc)
        val tvVerOrganizer: TextView = view.findViewById(R.id.tvVerifyEventOrganizer)
        val tvVerLocation: TextView = view.findViewById(R.id.tvVerifyEventLocation)

        val args = this.arguments
        val inputData = args?.get("eventTitle")
        val inputData1 = args?.get("eventDay")
        val inputData2 = args?.get("location")
        val inputData3 = args?.get("ngoName")
        val inputData4 = args?.get("desc")
        val inputData5 = args?.get("imageUrl")
        val inputData6 = args?.get("maxSlot")
        val inputData7 = args?.get("ngoId")
        Log.i("NGO","$inputData7")

        tvVerTitle.text = "Event Name: " + inputData.toString()
        tvVerDate.text = "Event Date: " + inputData1.toString()
        tvVerDesc.text = "Event Description: " + inputData4.toString()
        tvVerOrganizer.text = "Organizer: " + inputData3.toString()
        tvVerLocation.text = "Location: " + inputData2.toString()

        val db =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("PendingEvent").child("$inputData")

        val db2 =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("events").child("$inputData")

        btnApprove.setOnClickListener(){
            db.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var del = snapshot.ref.removeValue()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("Error", "Read failed")
                }

            })
            db2.child("title").setValue("$inputData")
            db2.child("date").setValue("$inputData1")
            db2.child("imageUrl").setValue("$inputData5")
            db2.child("status").setValue("1")
            db2.child("currentSlot").setValue("0")
            db2.child("eventDesc").setValue("$inputData4")
            db2.child("location").setValue("$inputData2")
            db2.child("ngoName").setValue("$inputData3")
            db2.child("denyStatus").setValue("")
            db2.child("maxSlot").setValue("$inputData6")
            Toast.makeText(
                context,
                "Event Approve",
                Toast.LENGTH_LONG
            ).show()
            Navigation.findNavController(it).navigate(R.id.action_adminVerifyEventPosting_to_verifyEvent)


        }

        btnReject.setOnClickListener(){
            var denyMsg = ""
            val builder: AlertDialog.Builder =
                AlertDialog.Builder(view.context)
            builder.setTitle("Reject Confirmation")
            builder.setMessage("Confirm to Reject the Event?")
            val input = EditText(view.context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            input.setHint("Enter Reason")
            builder.setView(input)

            builder.setPositiveButton(
                "Confirm",
                DialogInterface.OnClickListener { dialog, which ->
                    denyMsg = input.text.toString()
                    Toast.makeText(
                        context,
                        "Event Rejected",
                        Toast.LENGTH_LONG
                    ).show()
                    db.addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var del = snapshot.ref.removeValue()
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.i("Error", "Read failed")
                        }
                    })

                    val db4 = FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app")
                        .getReference("Users").child("$inputData7").child("notification")

                    db4.addListenerForSingleValueEvent(object:ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            Id = snapshot.childrenCount.toInt()+1
                            var pkId = Id.toString()
                            db4.child("$pkId").child("content").setValue("Event Name: $inputData , Deny Reason: $denyMsg")
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.i("Error","Not Found")
                        }

                    })

                    dialog.cancel()
                })
            builder.setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }



    }
}