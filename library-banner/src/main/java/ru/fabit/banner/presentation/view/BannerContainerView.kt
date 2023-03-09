package ru.fabit.banner.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.fabit.banner.Banner
import ru.fabit.banner.BannerEventListener
import ru.fabit.banner.BannerItem
import ru.fabit.banner.databinding.ViewBannerContainerBinding
import ru.fabit.banner.presentation.lifecycle.LifecycleView
import ru.fabit.banner.presentation.lifecycle.ViewLifecycleOwner
import ru.fabit.banner.presentation.store.state.BannerEvent
import ru.fabit.banner.presentation.store.state.BannerState
import ru.fabit.banner.presentation.viewcontroller.BannerViewController
import ru.fabit.viewcontroller.StateView
import ru.fabit.viewcontroller.registerViewController
import ru.fabit.viewcontroller.viewControllers

@AndroidEntryPoint
class BannerContainerView : CoordinatorLayout, LifecycleOwner, LifecycleView,
    StateView<BannerState> {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        viewLifecycleOwner = ViewLifecycleOwner(this)
        binding?.root?.setOnApplyWindowInsetsCallback { view, windowInsets ->
            val insets = windowInsets.toWindowInsets()?.let {
                WindowInsetsCompat.toWindowInsetsCompat(it)
                    .getInsets(WindowInsetsCompat.Type.statusBars())
            }
            val inset = (insets?.top ?: 0) + dpToPx(context, 4f)
            if (inset != topInset) {
                topInset = inset
                viewController.applyInsets()
            }
            if (view is ViewGroup) {
                view.children.forEach {
                    it.dispatchApplyWindowInsets(windowInsets.toWindowInsets())
                }
            }
            windowInsets
        }
    }

    private lateinit var viewLifecycleOwner: ViewLifecycleOwner

    private var binding: ViewBannerContainerBinding? =
        ViewBannerContainerBinding.inflate(LayoutInflater.from(context), this, true)

    private val viewController: BannerViewController by viewControllers()

    var bannerEventListener: BannerEventListener? = null

    private var topInset: Int? = null

    fun update(items: Collection<Banner>) {
        viewController.update(items.toSet())
    }

    override fun renderState(state: BannerState) {
        if (state.currentItem != null && (binding?.root?.childCount ?: -1) == 0)
            binding.redraw(state.currentItem)
        state.events.forEach { event ->
            if (event is BannerEvent.Show) {
                binding.redraw(event.currentItem)
            }
        }
    }

    private fun ViewBannerContainerBinding?.redraw(banner: Banner?) {
        this?.apply {
            if (banner != null || (binding?.root?.childCount ?: -1) != 0) {
                root.removeAllViews()
                banner?.let { createView(it) }
                invalidate()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun ViewBannerContainerBinding.createView(item: Banner) {
        bannerEventListener?.let { item.register(it) }
        if (item is BannerItem<out ViewBinding>) {
            item as BannerItem<ViewBinding>
            val itemBinding = item.initializeViewBinding(LayoutInflater.from(context), root, true)
            topInset?.let { item.applyTopInset(it, itemBinding) }
            item.bind(itemBinding)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        registerViewController(viewController)
        onResume()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lifecycle.removeObserver(viewController)
        onPause()
        onDestroy()
    }

    //region ==================== Lifecycle ====================

    override fun onDestroy() {
        viewLifecycleOwner.destroyListening()
        binding = null
    }

    override fun onResume() {
        viewLifecycleOwner.resumeListening()
    }

    override fun onPause() {
        viewLifecycleOwner.pauseListening()
    }

    //endregion

    override val lifecycle = viewLifecycleOwner.lifecycle
}