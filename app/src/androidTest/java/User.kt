data class User(
    var id:String,
    var name:String,
    var gender:String,
    var password:String,
    var email:String
){
    constructor(): this("","","","","")
}
