package com.example.kamis

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class FirstFragment: Fragment(R.layout.fragment_first), CustomAdapter.OnItemClickListener {
    // ArrayList of class ItemsViewModel
    private var data = ArrayList<ItemsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(activity)
        // This loop will create 20 Views containing
        data = ArrayList()
        var product = arrayOf("Piper", "Boia", "Usturoi", "Ceapa", "Busuioc", "Foi de dafin", "Chimen", "Oregano", "Curry", "Ghimbir")
        for (item in product)   {
            var price = Random.nextInt(1, 5)
            data.add(ItemsViewModel(R.drawable.picture, item, price.toString() ))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(this, data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        var searchView: SearchView = view.findViewById(R.id.products_search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
    override fun onItemClick(priceNumber: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, priceNumber)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "test")
        startActivity(shareIntent)
    }
}