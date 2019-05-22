package io.igaryhe.yabc

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlin.math.abs

class VerticalSwipeRefreshLayout(ctx: Context, attrs: AttributeSet) : SwipeRefreshLayout(ctx, attrs) {
    private var startY = 0.0f
    private var startX = 0.0f
    private var mIsVpDragger = true
    private val mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    override fun onInterceptTouchEvent(ev: MotionEvent) : Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                // 记录手指按下的位置
                startY = ev.y
                startX = ev.x
                // 初始化标记
                mIsVpDragger = false
            }
            MotionEvent.ACTION_MOVE -> {
                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
                if (mIsVpDragger) { return false }
                // 如果X轴位移大于Y轴位移，那么将事件交给viewPager处理。
                if (abs(ev.x - startX) > mTouchSlop &&
                    abs(ev.x - startX) > abs(ev.y - startY)) {
                    mIsVpDragger = true
                    return false
                }
            }
            MotionEvent.ACTION_UP -> { mIsVpDragger = false }
            MotionEvent.ACTION_CANCEL -> { mIsVpDragger = false }
        }
        // 如果是Y轴位移大于X轴，事件交给swipeRefreshLayout处理。
        return super.onInterceptTouchEvent(ev)
    }
}