package com.example.catalog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView

class WebFragment : Fragment() {

private lateinit var webView: WebView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_web, container, false)

        webView = view.findViewById(R.id.web_view)
        setUpWeb()
        // Inflate the layout for this fragment
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWeb() {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val handler = ServiceHandler(requireContext())

        webView.loadUrl(        handler.getUrl())

        webView.visibility = View.VISIBLE
    }


    companion object {

        @JvmStatic
        fun newInstance() = WebFragment()
    }
}