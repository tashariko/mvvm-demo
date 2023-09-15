package com.tasha.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.tasha.extensions.isGone
import com.tasha.extensions.visible
import com.tasha.customviews.FullScreenViewType.LoadingView
import com.tasha.customviews.FullScreenViewType.ErrorView
import com.tasha.databinding.FullScreenViewBinding
import com.tasha.extensions.gone
import com.tasha.extensions.isVisible

class FullScreenView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: FullScreenViewBinding

    init {
        binding = FullScreenViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun show(type: FullScreenViewType) {
        when(type) {
            LoadingView -> {
                if(binding.loader.isGone()){
                    binding.loader.visible()
                }
            }
            ErrorView -> {
                if(binding.loader.isVisible()){
                    binding.loader.gone()
                }
                if(binding.errorView.isGone()){
                    binding.errorView.visible()
                }
                if(binding.errorText.isGone()){
                    binding.errorText.visible()
                }
            }
        }
    }

    fun hide(type: FullScreenViewType) {
        when(type) {
            LoadingView -> {
                if(binding.loader.isVisible()){
                    binding.loader.gone()
                }
            }
            ErrorView -> {
                if(binding.errorView.isVisible()){
                    binding.errorView.gone()
                }
                if(binding.errorText.isVisible()){
                    binding.errorText.gone()
                }
            }
        }
    }

}