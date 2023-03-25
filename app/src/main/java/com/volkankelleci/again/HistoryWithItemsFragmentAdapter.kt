package com.volkankelleci.again

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.again.databinding.HomeRawBinding

class HistoryWithItemsFragmentAdapter(var get:List<Shopping>,private val viewModel: HomeFragmentViewModel): RecyclerView.Adapter<HistoryWithItemsFragmentAdapter.homeViewHolder>() {

     class homeViewHolder(val binding: HomeRawBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val itemBinding=HomeRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return homeViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        val shoppingItem=get[position]
        holder.binding.textForItem.text=shoppingItem.item
        holder.binding.amount.text= shoppingItem.count.toString()
        holder.binding.amount.visibility= View.VISIBLE
        holder.binding.minus.visibility= View.INVISIBLE
        holder.binding.plus.visibility= View.INVISIBLE



    }

    override fun getItemCount(): Int {
        return get.size
    }
    fun setData(shop: List<Shopping>){
        this.get =shop
        notifyDataSetChanged()
    }

}