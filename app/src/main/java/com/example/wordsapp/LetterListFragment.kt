package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {
    private var _binding: FragmentLetterListBinding?=null
        /*If you're certain a value won't be null when you access it, you can append !! to its type name.
        Then you can access it like any other property, without the ? operator.*/
    private val binding get() = _binding!!
        /*get() means this property is "get-only".
        That means you can get the value, but once assigned (as it is here), you can't assign it to something else.*/

    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager=true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        To display the options menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLetterListBinding.inflate(inflater, container, false)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclerView
        chooseLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val layoutButton=menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    private fun chooseLayout(){
        if(isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        else{
            recyclerView.layoutManager= GridLayoutManager(context,4)
        }
        recyclerView.adapter=LetterAdapter()
    }
    private fun setIcon(menuItem : MenuItem?){
        if(menuItem==null)
            return
        menuItem.icon=
            if(isLinearLayoutManager) ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(),R.drawable.ic_linear_layout)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_switch_layout ->{
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}