package com.watsoncell.publictoiletfinder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.watsoncell.publictoiletfinder.Interface.SearchToiletListener
import com.watsoncell.publictoiletfinder.R
import com.watsoncell.publictoiletfinder.models.NearByToiletDTO
import com.watsoncell.publictoiletfinder.utils.Constant
import java.text.DecimalFormat

class SearchCityToiletListAdapter(
    ctx: Context,
    toiletList: ArrayList<NearByToiletDTO>,
    listener: SearchToiletListener
) :
    RecyclerView.Adapter<SearchCityToiletListAdapter.SearchToiletViewHolder>(), Filterable {


    var searchToiletList: ArrayList<NearByToiletDTO> = toiletList
    var filterSearchToiletList = toiletList
    val searchToiletListener = listener
    val decimalFormat = DecimalFormat("#.##")

    var context = ctx

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchToiletViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.layout_search_toilet_row, p0, false)
        return SearchToiletViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filterSearchToiletList.size
    }

    override fun onBindViewHolder(holder: SearchToiletViewHolder, position: Int) {
        holder.tvCityName.text = filterSearchToiletList[position].title
        holder.tvToiletAvailableFor.text =
            "Verified - ${filterSearchToiletList[position].toiletAvailable}"
        holder.tvDistance.text = "${decimalFormat.format(filterSearchToiletList[position].distance.toDouble())} km"

        holder.itemView.setOnClickListener {
            searchToiletListener.searchToiletViewClickListener(holder.adapterPosition)
            Constant.nearByToiletDetail = filterSearchToiletList[position]
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchWord = constraint.toString()
                if (searchWord.isEmpty()) {
                    filterSearchToiletList = searchToiletList
                } else {
                    val filterList = ArrayList<NearByToiletDTO>()

                    for (toilet in searchToiletList) {
                        if (toilet.title.toLowerCase().contains(searchWord.toLowerCase()) or toilet.distance.contains(
                                searchWord
                            )
                            or toilet.toiletAvailable.toLowerCase().contains(searchWord.toLowerCase())
                        ) {

                            filterList.add(toilet)
                        }
                    }
                    filterSearchToiletList = filterList
                }
                val filterResult = FilterResults()
                filterResult.values = filterSearchToiletList

                return filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterSearchToiletList = results!!.values as ArrayList<NearByToiletDTO>
                notifyDataSetChanged()

                //if no toilet is found, this listener will be called and show no toilet found view
                if (filterSearchToiletList.size == 0)
                    searchToiletListener.noSearchedToiletIsFound(false)
                else
                    searchToiletListener.noSearchedToiletIsFound(true)

            }

        }
    }

    class SearchToiletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCityName = itemView.findViewById<TextView>(R.id.tvCityName)
        var tvDistance = itemView.findViewById<TextView>(R.id.tvToiletDistance)
        var tvToiletAvailableFor = itemView.findViewById<TextView>(R.id.tvToiletAvailableFor)
    }
}