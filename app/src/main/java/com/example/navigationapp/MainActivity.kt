package com.example.navigationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.navigationapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val list = listOf<Int>(
            R.drawable.apple,
            R.drawable.banana,
            R.drawable.uzum,
            R.drawable.grusha,
            R.drawable.peach
        )

        val title = listOf<String>(
            "Olma",
            "Banan",
            "Uzum",
            "Nok",
            "Shaftoli"
        )*/

        val data = listOf<CustomDataModel>(
            CustomDataModel(R.drawable.ic_baseline_home_24,R.drawable.apple,"Olma"),
            CustomDataModel(R.drawable.ic_baseline_info_24,R.drawable.banana,"Banan"),
            CustomDataModel(R.drawable.ic_baseline_filter_list_24,R.drawable.uzum,"Uzum"),
            CustomDataModel(R.drawable.ic_baseline_filter_list_24,R.drawable.grusha,"Nok"),
            CustomDataModel(R.drawable.ic_baseline_closed_caption_24,R.drawable.peach,"Shaftoli")
        )

        val customAdapter = CustomViewPagerAdapter(this,data.map {
            it.image
        })
        binding.viewPager.adapter = customAdapter

        TabLayoutMediator(binding.tab,binding.viewPager){tab,position ->
            tab.text = data[position].title
            tab.setIcon(data[position].icon)
            val badge = tab.orCreateBadge
            badge.number = Random.nextInt(1000)
        }.attach()

        binding.viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3

            setPageTransformer(CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
                addTransformer { page, position ->
                    page.apply {
                        scaleY = 0.85F + 0.15F * (1 - abs(position))

                        when{
                            position < -1 -> alpha = 0.1F
                            position <= 1 -> alpha = max(0.2F, 1 - abs(position))
                            position > 1 -> alpha = 0.1F
                        }

                        translationY = abs(position)*100
                    }
                }
            })

            /*setPageTransformer { page, position ->
                page.apply {
                    when{
                        position < -1 -> alpha = 0.1F
                        position <= 1 -> alpha = max(0.2F, 1 - abs(position))
                        position > 1 -> alpha = 0.1F
                    }

                    translationY = abs(position)*500
                }
            }*/
        }

    }
}