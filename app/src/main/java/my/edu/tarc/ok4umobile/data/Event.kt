package my.edu.tarc.ok4umobile.data

import com.google.firebase.database.PropertyName

data class Event(var title:String = "",
                 var date:String = "",
                 @get:PropertyName("imageUrl") @set:PropertyName("imageUrl") var imageUrl : String = "",
                var currentSlot: String="",
                var eventDesc: String ="",
                var location:String ="",
                var ngoName: String = "",
                var status: String = "",
                var denyStatus: String = "",
                var maxSlot:String = "")

