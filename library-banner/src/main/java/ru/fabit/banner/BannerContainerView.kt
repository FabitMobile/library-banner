package ru.fabit.banner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.viewbinding.ViewBinding
import ru.fabit.banner.databinding.ViewBannerContainerBinding

class BannerContainerView : CoordinatorLayout {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (isInEditMode) {
            val style = context.obtainStyledAttributes(attrs, R.styleable.BannerContainerView)
            val layout = style.getResourceId(R.styleable.BannerContainerView_android_layout, 0)
            style.recycle()
            if (layout > 0)
                View.inflate(context, layout, this)
        }

        binding?.root?.setOnApplyWindowInsetsCallback { view, windowInsets ->
            val insets = windowInsets.toWindowInsets()?.let {
                WindowInsetsCompat.toWindowInsetsCompat(it)
                    .getInsets(WindowInsetsCompat.Type.statusBars())
            }
            val inset = (insets?.top ?: 0) + dpToPx(context, 4f)
            if (inset != topInset) {
                topInset = inset
                redrawCurrent()
            }
            if (view is ViewGroup) {
                view.children.forEach {
                    it.dispatchApplyWindowInsets(windowInsets.toWindowInsets())
                }
            }
            windowInsets
        }
    }

    private var binding: ViewBannerContainerBinding? =
        ViewBannerContainerBinding.inflate(LayoutInflater.from(context), this, true)

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        findViewTreeViewModelStoreOwner()?.let { ViewModelProvider(it)[BannerViewModel::class.java] }
    }

    var bannerListener: BannerListener? = null

    private var topInset: Int? = null

    fun setBannerListener(listener: BannerListener) {
        bannerListener = listener
    }

    fun update(items: Collection<Banner>) {
        val current = items.maxByOrNull { it.zIndex }
        viewModel?.apply {
            this.items = items.toList()
            if (current == null || currentItem == null || current != currentItem) {
                currentItem = current
                binding.redraw(current)
            }
        }
    }

    fun remove(item: Banner) {
        viewModel?.let {
            update(it.items - item)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        redrawCurrent()
    }

    private fun redrawCurrent() = viewModel?.let {
        binding.redraw(it.currentItem)
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
        bannerListener?.let { item.register(it) }
        if (item is BannerItem<out ViewBinding>) {
            item as BannerItem<ViewBinding>
            val itemBinding = item.initializeViewBinding(LayoutInflater.from(context), root, true)
            topInset?.let { item.applyTopInset(it, itemBinding) }
            item.bind(itemBinding)
        }
    }
}