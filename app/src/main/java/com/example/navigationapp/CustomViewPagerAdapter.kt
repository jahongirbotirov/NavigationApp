package com.example.navigationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomViewPagerAdapter(fragment: FragmentActivity,val imageList: List<Int>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = imageList.size

    override fun createFragment(position: Int): Fragment {
        return SampleFragment().apply {
            arguments = Bundle().apply {
                putInt("key",imageList[position])
            }
        }
    }
}