package com.tasha.screens.planets.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasha.EspressoIdlingResource
import com.tasha.HomeScreenType
import com.tasha.customviews.FullScreenViewType
import com.tasha.data.ApiResult
import com.tasha.data.local.entity.Planet
import com.tasha.databinding.FragmentPlanetBinding
import com.tasha.screens.SecondActivity
import com.tasha.screens.planets.data.PlanetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetFragment :Fragment() {

    val viewModel: PlanetViewModel by viewModels()
    lateinit var binding: FragmentPlanetBinding

    val adapter by lazy {
        PlanetAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlanetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }


    private fun setupUI() {
        adapter.itemClicked = object: ListPlanetClicked {
            override fun itemClicked(item: Planet) {
                var intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("type", HomeScreenType.Planet.name)
                intent.putExtra("planet", item)
                startActivity(intent)
            }

        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        EspressoIdlingResource.increment()
        viewModel.liveData.observe(viewLifecycleOwner){response ->
            when(response.status){
                ApiResult.Status.SUCCESS -> {
                    response.data?.let {
                        showData(it)
                    }?: run {
                        showError()
                    }
                    EspressoIdlingResource.decrement()
                }
                ApiResult.Status.ERROR ->{
                    response.data?.let {
                        showData(it)
                    }?: run {
                        showError()
                    }
                    EspressoIdlingResource.decrement()
                }
                ApiResult.Status.LOADING -> {
                    response.data?.let {
                        if(it.isEmpty()){
                            showLoading()
                        }else {
                            showData(it)
                        }
                    }?: run {
                        showLoading()
                    }
                }
            }
        }

        viewModel.getData()
    }

    fun showData(list: List<Planet>){
        binding.fullScreenview.hide(FullScreenViewType.ErrorView)
        binding.fullScreenview.hide(FullScreenViewType.LoadingView)
        binding.recyclerView.isVisible = true
        adapter.swapData(list)
    }

    fun showError(){
        binding.fullScreenview.show(FullScreenViewType.ErrorView)
        binding.fullScreenview.hide(FullScreenViewType.LoadingView)
        binding.recyclerView.isVisible = false
    }

    fun showLoading(){
        binding.fullScreenview.hide(FullScreenViewType.ErrorView)
        binding.fullScreenview.show(FullScreenViewType.LoadingView)
        binding.recyclerView.isVisible = false
    }

}