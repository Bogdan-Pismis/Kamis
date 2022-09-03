package com.example.kamis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class CustomAdapter(private val listener: OnItemClickListener, private val mList: ArrayList<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(),
    Filterable {

    var productsFilterList = ArrayList<ItemsViewModel>()

    init {
        productsFilterList = mList
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = productsFilterList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text

        holder.price.text = ItemsViewModel.price.toString() + " lei"


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return productsFilterList.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val price: TextView = itemView.findViewById(R.id.price)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position: Int = absoluteAdapterPosition
            val clickedItem: ItemsViewModel = productsFilterList[position]
            listener.onItemClick(clickedItem.price)
        }

    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                productsFilterList = if (charSearch.isEmpty()) {
                    mList
                } else {
                    val resultList = ArrayList<ItemsViewModel>()
                    for (row in productsFilterList) {
                        if (row.text.lowercase().contains(charSearch.lowercase())) {
                            resultList.add(row)
                        }
                    }
                    println(productsFilterList)
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = productsFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productsFilterList = results?.values as ArrayList<ItemsViewModel>
                notifyDataSetChanged()
            }

        }
    }
    interface OnItemClickListener {
        fun onItemClick(priceNumber: String)
    }
}
