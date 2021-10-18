package com.votenoid.votenoid.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    var fragmentList=ArrayList<Fragment>()

    override fun getItemCount(): Int =fragmentList.size

    override fun createFragment(position: Int): Fragment {

        return fragmentList.get(position)
    }
}