package org.americanairlines.our1grouprestaurantmapproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.americanairlines.our1grouprestaurantmapproject.R
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult

class PlaceAdapter (private var placeList: List<PlaceResult>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {


    fun updatePlaceList(placeList: List<PlaceResult>) {
        this.placeList = placeList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.nearby_place_item_layout, parent, false)

        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placeList[position]

        holder.apply {
            nameTextView.text = place.name
            // TODO retrieve address information?
        }
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    class PlaceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTextView: TextView = itemView.findViewById(R.id.textview_place_name)
        val addressTextView: TextView = itemView.findViewById(R.id.textview_place_address)
    }
}