package com.example.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.catalog.databinding.FragmentAddBinding
import com.google.gson.Gson

class AddFragment : Fragment() {

    private  lateinit var  binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater)

        binding.apply {
            clearBtn.setOnClickListener{
                titleInput.text.clear()
                priceInput.text.clear()
                urlInput.text.clear()
                imgInput.text.clear()
            }

            addBtn.setOnClickListener {
                val title = titleInput.text.toString()
                val price = priceInput.text.toString()
                val url = urlInput.text.toString()
                val img = imgInput.text.toString()

                val serviceHandler = ServiceHandler(requireContext())
                serviceHandler.addData(title, price, url, img)

                titleInput.text.clear()
                priceInput.text.clear()
                urlInput.text.clear()
                imgInput.text.clear()

                Toast.makeText(context, "Успешно сохранено", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        @JvmStatic
        fun newInstance() = AddFragment()
    }
}