package br.com.fornaro.mlsamples.image_labeling

import android.os.Bundle
import android.view.View
import br.com.fornaro.mlsamples.BaseFragment
import br.com.fornaro.mlsamples.R
import br.com.fornaro.mlsamples.databinding.FragmentImageLabelingBinding

class ImageLabelingFragment :
    BaseFragment<FragmentImageLabelingBinding>(R.layout.fragment_image_labeling) {

    private lateinit var viewModel: ImageLabelingViewModel

    override fun bind(view: View) = FragmentImageLabelingBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}