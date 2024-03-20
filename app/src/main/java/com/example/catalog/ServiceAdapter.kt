package com.example.catalog

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.databinding.ServiceItemBinding

public class ServiceAdapter(
    val context: Context, val fragmentManager: FragmentManager
):RecyclerView.Adapter<ServiceAdapter.ServiceHolder>() {

    val serviceList = ArrayList<ServiceItem>()

    class ServiceHolder(item: View):RecyclerView.ViewHolder(item) {
        val binding = ServiceItemBinding.bind(item)
        @SuppressLint("SetTextI18n")
        fun bind(service: ServiceItem, context1: Context, fragment1: FragmentManager) = with(binding){
            titleServiceCard.text = service.title
            priceServiceCard.text = "Цена: ${service.price}"
            callBtn.setOnClickListener {
                openLink(service.url, context1)
            }
            serviceCard.setOnClickListener {
                val handler = ServiceHandler(context1)
                handler.addUrl(service.url)
                fragment1
                    .beginTransaction()
                    .replace(R.id.fragment_place, WebFragment.newInstance())
                    .commit()
            }

        }

        private fun openLink(url: String, context: Context) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.service_item, parent, false)
        return ServiceHolder(view)
    }

    override fun getItemCount(): Int {
        return  serviceList.size
    }

    override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
        holder.bind(serviceList[position], context, fragmentManager)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun add(service: ServiceItem){
        serviceList.add(service)
        notifyDataSetChanged()
    }


}