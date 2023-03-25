package com.volkankelleci.again

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.again.databinding.HomeRawBinding

class HomeFragmentAdapter(var shop:List<Shopping>, private val viewModel: HomeFragmentViewModel): RecyclerView.Adapter<HomeFragmentAdapter.homeViewHolder>() {

     class homeViewHolder(val binding: HomeRawBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val itemBinding=HomeRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return homeViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        val shoppingItem=shop[position]
        holder.binding.textForItem.text=shoppingItem.item
        holder.binding.amount.text= shoppingItem.count.toString()

        holder.binding.plus.setOnClickListener {
                shoppingItem.count++
                viewModel.addShopping(shoppingItem)
        }
        holder.binding.minus.setOnClickListener {
            if (shoppingItem.count>0){
                shoppingItem.count--
                viewModel.addShopping(shoppingItem)
            }else{
                Toast.makeText(it.context, "You can't get negative item count", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return shop.size
    }

    fun setData(shop: List<Shopping>){
        this.shop = shop
        notifyDataSetChanged()
    }

}