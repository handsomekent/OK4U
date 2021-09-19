package my.edu.tarc.ok4umobile

class Event1(
    var ngoName:String,
    var eventName:String,
    var eventDescription:String,
    var eventDate:String,
    var eventLocation: String,
    var currentSlot:String,
    var maxslot:String,
    var status:String,
    var denyReason:String,
    var imageURL : String
){
    constructor(): this("","","","","","","","","","")
}