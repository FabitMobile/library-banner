package ru.fabit.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.fabit.banner.BannerItem
import ru.fabit.sample.databinding.BannerMainBinding

class MainBanner(
    private val text: CharSequence? = null,
) : BannerItem<BannerMainBinding>(0) {
    override fun bind(binding: BannerMainBinding) {
        binding.tvTitle.text = text
        binding.tvTitle.setOnClickListener {
            listener?.positive(this)
        }
    }

    override fun initializeViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ): BannerMainBinding = BannerMainBinding.inflate(inflater, parent, attachToParent)
}