package io.igaryhe.yabc

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CategoryPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CollectionFragment(2)
            1 -> CollectionFragment(1)
            2 -> CollectionFragment(6)
            else -> CollectionFragment(2)
        } }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Anime"
            1 -> "Book"
            2 -> "Drama"
            else -> null
        }
    }
}