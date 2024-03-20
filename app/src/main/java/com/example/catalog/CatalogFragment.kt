package com.example.catalog

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.catalog.databinding.FragmentCatalogBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class CatalogFragment : Fragment() {


    private var bg_index = 0;



    private lateinit var binding: FragmentCatalogBinding
    var dataArray: Set<String>? = null
    private var timer:CountDownTimer? = null
    var index: Int = 0
    @SuppressLint("Recycle")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(inflater)

//        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
//        val adapter = ServiceAdapter(requireContext(), parentFragmentManager)
//        val handler = ServiceHandler(requireContext())
//        val dataArray: Set<String> = handler.getDataArray()
//
//        dataArray.forEach { jsonString ->
//            val dataItem = Gson().fromJson(jsonString, ServiceItem::class.java)
//            adapter.add(dataItem)
//        }
//        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
//        binding.rcView.adapter = adapter

        val colors = resources.obtainTypedArray(R.array.background_colors)
        binding.bgLayout.setBackgroundResource(R.drawable.item_bg)
        binding.bgLayout.setBackgroundColor(colors.getColor(0, 0))

        val handler = ServiceHandler(requireContext())
        dataArray = handler.getDataArray()
        displayService()

        binding.button.setOnClickListener {
            index+=1
            displayService()
        }

        binding.bgLayout.setOnClickListener {
            bg_index += 1
            val colors = resources.obtainTypedArray(R.array.background_colors)

            if(bg_index >= colors.length()) bg_index = 0

            binding.bgLayout.setBackgroundColor(colors.getColor(bg_index, 0))

            Toast.makeText(context, "Фон услуги изменен", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun displayService(){
        if(dataArray!!.isEmpty()) return

        val item = Gson().fromJson(dataArray!!.elementAt(index), ServiceItem::class.java)
        binding.catalogTitle.text = item.title
        binding.catalogPrice.text = "${item.price} бел. руб."


        Picasso.with(requireContext())
            .load(item.img)
            .fit()
            .centerCrop()
            .into(binding.img)

        if(index == dataArray!!.size -1) index = -1
        startTimer(30)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun startTimer(seconds: Long){
        timer?.cancel()
        timer = object : CountDownTimer(seconds*1000, 1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(timeS: Long) {

                binding.timer.text = "00:${(timeS/1000).toInt()}"
            }

            override fun onFinish() {
                index+=1
                displayService()
            }

        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CatalogFragment()
    }
}