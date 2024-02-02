package ru.fabit.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import androidx.viewbinding.ViewBinding
import ru.fabit.banner.databinding.ItemSwipeDismissBannerBinding
import ru.turlir.android.verticalswipe.AlphaElevationSideEffect
import ru.turlir.android.verticalswipe.BelowFractionalClamp
import ru.turlir.android.verticalswipe.NegativeFactorFilterSideEffect
import ru.turlir.android.verticalswipe.SettleOnTopAction
import ru.turlir.android.verticalswipe.VerticalSwipeBehavior

open class SwipeDismissBannerWrapper(
    val banner: Banner,
) : BannerItem<ItemSwipeDismissBannerBinding>(banner.zIndex), BannerListener {
    @Suppress("UNCHECKED_CAST")
    override fun bind(binding: ItemSwipeDismissBannerBinding) {
        if (banner is BannerItem<out ViewBinding>) {
            banner as BannerItem<ViewBinding>
            val itemBinding = banner.initializeViewBinding(
                LayoutInflater.from(binding.root.context),
                binding.bannerRoot,
                true
            )
            banner.bind(itemBinding)
        }
        initBehavior(binding.bannerRoot)
        banner.register(this)
    }

    protected open fun initBehavior(view: View) {
        VerticalSwipeBehavior.from(view).apply {
            sideEffect = NegativeFactorFilterSideEffect(AlphaElevationSideEffect())
            clamp = BelowFractionalClamp(minFraction = 3f)
            settle = SettleOnTopAction()
            listener = object : VerticalSwipeBehavior.SwipeListener {
                override fun onPostSettled(diff: Int) {
                    if (diff < 0)
                        perform(BannerAction.Close, this@SwipeDismissBannerWrapper)
                }

                override fun onPreSettled(diff: Int) {}
            }
        }
    }

    override fun applyTopInset(inset: Int, binding: ItemSwipeDismissBannerBinding) {
        binding.space.layoutParams = LayoutParams(binding.space.layoutParams.width, inset)
    }

    override fun initializeViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ): ItemSwipeDismissBannerBinding =
        ItemSwipeDismissBannerBinding.inflate(inflater, parent, attachToParent)

    override fun perform(action: BannerAction, banner: Banner) {
        listener?.perform(action, banner)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SwipeDismissBannerWrapper

        return banner == other.banner
    }

    override fun hashCode(): Int {
        return banner.hashCode()
    }
}