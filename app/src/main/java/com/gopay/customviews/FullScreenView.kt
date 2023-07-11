package com.gopay.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.gopay.R
import com.gopay.extensions.isGone
import com.gopay.extensions.visible
import com.gopay.customviews.FullScreenViewType.LoadingView
import com.gopay.customviews.FullScreenViewType.ErrorView
import com.gopay.databinding.FullScreenViewBinding
import com.gopay.extensions.gone
import com.gopay.extensions.isVisible

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