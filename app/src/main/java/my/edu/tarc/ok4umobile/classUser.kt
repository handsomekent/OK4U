//data class User(
//        var id:String,
//        var name:String,
//        var gender:String,
//        var password:String,
//        var email:String
//){
//    constructor(): this("","","","","")
//
//
//}

package my.edu.tarc.ok4umobile
class User(
    var name:String,
    var gender:String,
    var password:String,
    var email:String,
    var userType:String
){
    constructor(): this("","","","","")
}
