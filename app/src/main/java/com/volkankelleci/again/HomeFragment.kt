package com.volkankelleci.again

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkankelleci.again.databinding.FragmentHomeBinding
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class HomeFragment() : Fragment() {
    private lateinit var shoppingViewModel: HomeFragmentViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()?.setTitle("Shopping List")
        shoppingViewModel=ViewModelProvider(this).get(HomeFragmentViewModel::class.java)

        //adapter define
        val adapter=HomeFragmentAdapter(listOf(), shoppingViewModel)
        binding.recyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter
        shoppingViewModel.getShopping.observe(viewLifecycleOwner,Observer{
            adapter.setData(it)
        })
        binding.addButton.setOnClickListener {
            val itemName=binding.itemEntry.text.toString()
            //take time
            val date=ZonedDateTime.now()
            val takeDate=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(date)


            val shopping=Shopping(itemName,0,takeDate)
            shoppingViewModel.addShopping(shopping)
            if (itemName.isNotEmpty()){
                Toast.makeText(requireContext(), "DONE", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(requireContext(), "please fill all information", Toast.LENGTH_SHORT).show()
            }

        }
        //observe list is coming or not ?


        //click on listeners
        binding.completeButton.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToHistoryFragment("itemName","takeDate")
            findNavController().navigate(action)
            adapter.notifyDataSetChanged()



        }


    }

}