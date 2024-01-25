package com.example.mycountrylistapp


import android.content.ContentValues.TAG
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycountrylistapp.Network.MyAPI
import com.example.mycountrylistapp.adapter.CountryListAdapter
import com.example.mycountrylistapp.databinding.ActivityMainBinding


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    // Declaring and initializing the variables

    lateinit var viewModel: MainActivityViewModel
    private val adapter = CountryListAdapter()
    lateinit var binding: ActivityMainBinding
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = MyAPI.getInstance()
        val mainRepository = MainRepository(retrofitService)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setLayoutManager(layoutManager)
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainActivityViewModel::class.java)

        viewModel.countryList.observe(this, {
            adapter.setCountryList(it)
            Log.i(TAG, " View Model data with Configuration change ")
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.getAllCountries()


    }
}



