# Banner

### Библиотека для показа и контролирования порядка баннеров в приложении.

на экране всегда показывается только один баннер с наивысшим приоритетом, при добавлении нового, с еще более высоким приоритетом, он будет сразу отображен

## Использование
Указать в разметке контейнер
```xml
<ru.fabit.banner.presentation.view.BannerContainerView
    android:id="@+id/bannerContainerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:clickable="false"
    android:focusable="false" />
```
Создать разметку баннера
```xml
<FrameLayout
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:clickable="true"
    android:focusable="true"/>
```
Создать класс баннера. Порядок показа определяется значением переменной zIndex
```kotlin
class MainNotificationBanner(
    private val title: CharSequence
) : BannerItem<YourLayoutBinding>(0) {
    override fun bind(binding: YourlayoutBinding) {
        binding.textViewTitle.text = title
        binding.textViewMore.setOnClickListener {
            listener?.positive(this)
        }
        binding.imageViewClose.setOnClickListener {
            listener?.negative(this)
        }
    }

    override fun initializeViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attachToParent: Boolean
    ): YourLayoutBinding = YourLayoutBinding.inflate(inflater, parent, attachToParent)
}
```
В классе представления установить слушателя и вызвать обновление контента контейнера
```kotlin
    bannerContainerView.bannerEventListener = object : BannerEventListener {
        override fun negative(banner: Banner) {
        }
    
        override fun positive(banner: Banner) {
        }
    }

    bannerContainerView.update(banners)
```
Чтобы добавить SwipeDismiss эффект, нужно обернуть баннер в `SwipeDismissBannerWrapper`