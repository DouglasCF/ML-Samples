package br.com.fornaro.mlsamples.image_labeling

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import br.com.fornaro.mlsamples.BaseFragment
import br.com.fornaro.mlsamples.R
import br.com.fornaro.mlsamples.databinding.FragmentImageLabelingBinding
import coil.load
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import java.io.File

class ImageLabelingFragment :
    BaseFragment<FragmentImageLabelingBinding>(R.layout.fragment_image_labeling) {

    private lateinit var viewModel: ImageLabelingViewModel

    override fun bind(view: View) = FragmentImageLabelingBinding.bind(view)

    var takePictureResultLauncher: ActivityResultLauncher<Uri>? = null
    var uri: Uri? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        takePictureResultLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success) {
                    binding.imageView.load(uri)
                    val image = InputImage.fromFilePath(requireContext(), uri)

                    val options = ImageLabelerOptions.Builder()
                        .setConfidenceThreshold(.7f)
                        .build()
                    val labeler = ImageLabeling.getClient(options)
                    labeler.process(image)
                        .addOnSuccessListener { labels ->
                            binding.resultText.text = null
                            for (label in labels) {
                                val text = label.text
                                val confidence = label.confidence
                                val index = label.index

                                binding.resultText.append("$text $confidence $index\n")
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT)
                                .show()
                        }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.takePictureButton.setOnClickListener { dispatchTakePictureIntent() }
    }

    private fun dispatchTakePictureIntent() {
        uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            File.createTempFile(
                "IMG_",
                ".jpg",
                requireContext().cacheDir
            )
        )

        takePictureResultLauncher?.launch(uri)
    }
}