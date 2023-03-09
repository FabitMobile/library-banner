package ru.fabit.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.updateMargins
import androidx.viewbinding.ViewBinding

abstract class BannerItem<Binding : ViewBinding>(override val zIndex: Int) : Banner(zIndex) {

    abstract fun bind(binding: Binding)

    abstract fun initializeViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ): Binding

    open fun applyTopInset(inset: Int, binding: Binding) {
        (binding.root.layoutParams as MarginLayoutParams).apply {
            updateMargins(top = inset)
        }
    }
}