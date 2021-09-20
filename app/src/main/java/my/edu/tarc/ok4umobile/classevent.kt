package my.edu.tarc.ok4umobile

class Event1(
    var ngoName:String,
    var title:String,
    var eventDesc:String,
    var date:String,
    var location: String,
    var currentSlot:String,
    var maxSlot:String,
    var status:String,
    var denyReason:String,
    var imageUrl : String,
    var ngoId : String
){
    constructor(): this("","","","","","","","","","","")
}