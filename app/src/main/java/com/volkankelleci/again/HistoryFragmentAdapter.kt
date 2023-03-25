package com.volkankelleci.again

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.again.databinding.HomeRawBinding

class HistoryFragmentAdapter(var save:List<Shopping>,private val viewModel: HomeFragmentViewModel): RecyclerView.Adapter<HistoryFragmentAdapter.homeViewHolder>() {


     class homeViewHolder(val binding: HomeRawBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val itemBinding=HomeRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return homeViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        val shoppingItem=save[position]
        holder.binding.textForItem.text=shoppingItem.time
        holder.binding.textForItem.setOnClickListener{
            val action =HistoryFragmentDirections.actionHistoryFragmentToHistoryWithItemsFragment(shoppingItem.time!!,shoppingItem.item)
            Navigation.findNavController(it).navigate(action)
            notifyDataSetChanged()
        }
        holder.binding.amount.visibility= View.INVISIBLE
        holder.binding.minus.visibility= View.INVISIBLE
        holder.binding.plus.visibility= View.INVISIBLE



    }

    override fun getItemCount(): Int {
        return save.size
    }
    fun setData(shop: List<Shopping>){
        this.save =shop
        notifyDataSetChanged()
    }

}