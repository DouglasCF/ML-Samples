package br.com.fornaro.mlsamples

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import br.com.fornaro.mlsamples.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun bind(view: View) = FragmentMainBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageLabelingButton.setOnClickListener { findNavController().navigate(R.id.imageLabelingFragment) }
    }
}