package my.edu.tarc.ok4umobile

class Event(
    var ngoName:String,
    var eventName:String,
    var eventDescription:String,
    var eventDate:String,
    var eventLocation: String,
    var currentSlot:String,
    var maxslot:String,
    var status:Int,
    var denyReason:String
){
    constructor(): this("","","","","","","","","")
}