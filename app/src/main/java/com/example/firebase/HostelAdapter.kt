package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HostelAdapter(val mctx: Context,val layoutResid:Int,val hostellist:List<Hostel>)
    :ArrayAdapter<Hostel>(mctx,layoutResid,hostellist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater= LayoutInflater.from(mctx)
        val view:View=layoutInflater.inflate(layoutResid,null)

        val textViewName=view.findViewById<TextView>(R.id.textViewName)
        val Hostel=hostellist[position]
        textViewName.text=Hostel.stdname
        textViewName.text=Hostel.ftrname
        textViewName.text=Hostel.ctyname
        return view
    }
}