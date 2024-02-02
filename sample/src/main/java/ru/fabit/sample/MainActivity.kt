package ru.fabit.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.fabit.banner.Banner
import ru.fabit.banner.BannerAction
import ru.fabit.banner.BannerContainerView
import ru.fabit.banner.BannerListener
import ru.fabit.banner.SwipeDismissBannerWrapper

class MainActivity : AppCompatActivity(), BannerListener {
    private lateinit var bannerContainerView: BannerContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bannerContainerView = findViewById(R.id.banner)
        bannerContainerView.setBannerListener { action, banner ->
            when(action) {

            }
        }
        if (updateOnlyOnce) {
            updateOnlyOnce = false
            val banner = MainBanner("У нас в приложении появились крутые баннеры!")
            bannerContainerView.update(listOf(SwipeDismissBannerWrapper(banner)))
        }
    }

    companion object {
        private var updateOnlyOnce = true
    }

    override fun perform(action: BannerAction, banner: Banner) {
        when (action) {

            BannerAction.More,
            BannerAction.Close -> bannerContainerView.remove(banner)

            MyBannerAction -> Toast.makeText(this, "toast", Toast.LENGTH_LONG).show()
        }
    }
}