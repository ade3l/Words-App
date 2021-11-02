package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {
    private lateinit var letterId:String
    companion object{
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="

    }

    var _binding :FragmentWordListBinding?=null
    val binding get() = _binding!!
    lateinit var recycler_view: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            letterId=it.getString(LETTER).toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentWordListBinding.inflate(layoutInflater, container,false)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view=binding.recyclerView
        recycler_view.layoutManager= LinearLayoutManager(requireContext())
/*
        As fragments don't have an intent property and shouldn't normally access the intent of the parent activity.
        For now, refer to activity.intent to get the extras.
*/
        val letter= activity?.intent?.extras?.getString(LETTER).toString()
        recycler_view.adapter=WordAdapter(context,letterId)

        recycler_view.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}