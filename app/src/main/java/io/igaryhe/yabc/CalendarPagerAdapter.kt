package io.igaryhe.yabc

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CalendarPagerAdapter(fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> CalendarFragment(0)
            1 -> CalendarFragment(1)
            2 -> CalendarFragment(2)
            3 -> CalendarFragment(3)
            4 -> CalendarFragment(4)
            5 -> CalendarFragment(5)
            6 -> CalendarFragment(6)
            else -> CalendarFragment(0)
        }
    }
    override fun getCount(): Int = 7

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Monday"
            1 -> "Tuesday"
            2 -> "Wednesday"
            3 -> "Thursday"
            4 -> "Friday"
            5 -> "Saturday"
            6 -> "Sunday"
            else -> null
        }
    }
}