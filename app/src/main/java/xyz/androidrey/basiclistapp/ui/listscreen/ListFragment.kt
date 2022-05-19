package xyz.androidrey.basiclistapp.ui.listscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import xyz.androidrey.basiclistapp.R
import xyz.androidrey.basiclistapp.adapter.OnPictureItemClickedListener
import xyz.androidrey.basiclistapp.adapter.PictureAdapter
import xyz.androidrey.basiclistapp.databinding.FragmentListBinding
import xyz.androidrey.basiclistapp.model.Picture
import xyz.androidrey.basiclistapp.navigate

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class ListFragment : Fragment() {


    private val viewModel: ListViewModel by viewModels()

    private val onPictureItemClickedListener =  object : OnPictureItemClickedListener{
        override fun onItemClicked(data: Picture) {
            navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(data))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rcvPictures.adapter= PictureAdapter(onPictureItemClickedListener)
        binding.rcvPictures.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.bu.setOnClickListener {
//            findNavController().navigate(R.id.action_ListFragment_to_DetailsFragment)
//        }
    }


}