package com.example.firebase

class Hostel(
    val id: String,
    val stdname: String,
    val ftrname:String,
    val ctyname:String,
    val ratingbar: Int
){

    constructor():this("","","","",0)
}