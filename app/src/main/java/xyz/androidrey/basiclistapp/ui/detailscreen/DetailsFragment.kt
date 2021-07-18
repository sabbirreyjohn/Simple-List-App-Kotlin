package xyz.androidrey.basiclistapp.ui.detailscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import xyz.androidrey.basiclistapp.R
import xyz.androidrey.basiclistapp.databinding.FragmentDetailsBinding
import xyz.androidrey.basiclistapp.model.Picture


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var picture: Picture

    companion object {
        const val PICTURE = "picture"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            picture = it?.getSerializable(PICTURE) as Picture
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.picture = picture
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_DetailsFragment_to_ListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}