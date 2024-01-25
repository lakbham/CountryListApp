package com.example.mycountrylistapp.adapter

import android.hardware.biometrics.BiometricManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycountrylistapp.data.Countries
import com.example.mycountrylistapp.R


class CountryListAdapter() : RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {
    private var countryList: List<Countries>? = null

    fun setCountryList(countryList: List<Countries>) {
        this.countryList = countryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.MyViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!)

    }

    override fun getItemCount(): Int {
        if (countryList == null)
            return 0
        else
            return countryList?.size!!
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
        val region = view.findViewById<TextView>(R.id.region)
        val code = view.findViewById<TextView>(R.id.code)
        val capital = view.findViewById<TextView>(R.id.capital)
        fun bind(data: Countries) {
            name.text = data.name
            region.text = data.region
            code.text = data.code
            capital.text = data.capital


        }

    }
}