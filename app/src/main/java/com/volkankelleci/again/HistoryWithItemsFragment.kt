package com.volkankelleci.again

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkankelleci.again.databinding.FragmentHistoryBinding
import com.volkankelleci.again.databinding.FragmentHistoryWithItemsBinding

class HistoryWithItemsFragment : Fragment() {
    private var _binding: FragmentHistoryWithItemsBinding? = null
    private val binding get() = _binding!!
    private lateinit var shoppingViewModel: HomeFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =FragmentHistoryWithItemsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args=HistoryWithItemsFragmentArgs.fromBundle(it).time
            getActivity()?.setTitle("${args}")
        }
        shoppingViewModel= ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        //adapter define
        val adapter=HistoryWithItemsFragmentAdapter(listOf(),shoppingViewModel)
        binding.historyWithItemsRV.layoutManager= LinearLayoutManager(requireContext())
        binding.historyWithItemsRV.adapter=adapter

        shoppingViewModel.getShopping.observe(viewLifecycleOwner, Observer{

           adapter.setData(it)
        })
    }

}