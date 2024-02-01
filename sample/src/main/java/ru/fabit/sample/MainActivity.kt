package ru.fabit.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fabit.banner.Banner
import ru.fabit.banner.BannerContainerView
import ru.fabit.banner.BannerEventListener
import ru.fabit.banner.SwipeDismissBannerWrapper

class MainActivity : AppCompatActivity(), BannerEventListener {
    private lateinit var bannerContainerView: BannerContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bannerContainerView = findViewById(R.id.banner)
        bannerContainerView.bannerEventListener = this
        if (updateOnlyOnce) {
            updateOnlyOnce = false
            val banner = MainBanner("У нас в приложении появились крутые баннеры!")
            bannerContainerView.update(listOf(SwipeDismissBannerWrapper(banner)))
        }
    }

    override fun positive(banner: Banner) {
        bannerContainerView.remove(banner)
    }

    override fun negative(banner: Banner) {
        bannerContainerView.remove(banner)
    }

    companion object {
        private var updateOnlyOnce = true
    }
}