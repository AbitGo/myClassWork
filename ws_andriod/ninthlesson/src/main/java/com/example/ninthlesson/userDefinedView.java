package com.example.ninthlesson;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.customview.widget.ViewDragHelper;

public class userDefinedView extends LinearLayout {
    private ViewDragHelper mDragHelper = null;
    private View mDragView;
    private View mHideView;
    private int mDragSlop;//移动距离 小于这个距离就不触发移动控件 恢复到当前位置
    private int mWidth;
    private int mHeight;
    private int mDragDistance;
    private final int STATE_CLOSE = 1001;
    private final int STATE_OPEN = 1002;
    private int mState = STATE_CLOSE;

    public userDefinedView(Context context) {
        this(context, null);
    }

    public userDefinedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public userDefinedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //其中1.0f是敏感度参数参数越大越敏感。第一个参数为this，表示该类生成的对象，
        // 他是ViewDragHelper的拖动处理对象，必须为ViewGroup。
        mDragHelper = ViewDragHelper.create(this, 1.0f, new CallBack());
        //48dp  是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件，
        // 如viewpager就是用这个距离来判断用户是否翻页
        mDragSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(getContext()));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mDragView.layout(getPaddingLeft(), getPaddingTop(), mWidth - getPaddingRight(), mHeight - getPaddingBottom());
        mHideView.layout(mWidth - getPaddingRight(), getPaddingTop(), mWidth - getPaddingRight() + mDragDistance, mHeight - getPaddingBottom());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        try {
            mDragView = getChildAt(0);
            mHideView = getChildAt(1);
        } catch (Exception e) {
            throw new NullPointerException("必须有两个子view");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        mDragDistance = mHideView.getMeasuredWidth();
    }

    /**
     * onInterceptTouchEvent中通过使用mDragger.shouldInterceptTouchEvent(event)来决定我们是否应该拦截当前的事件。
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    /**
     * onTouchEvent中通过mDragger.processTouchEvent(event)处理事件。
     *
     * @param event
     * @return true 时间已消费（交给了mDragHelper）不往下传递
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    /**
     * 这个计算滑动的函数computeScroll()，就是用于判断滚动是否完成的。
     * 在computeScroll方法中判断smoothSlideViewTo触发的continueSettling(boolean)的返回值，来动态刷新界面
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
//            postInvalidate();
        }
    }

    class CallBack extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragView;
        }

        //拖拽的子View在所属方向上移动的位置(这里是水平方向)，child为拖拽的子View，left为子view应该到达的x坐标，dx为挪动差值
        //return left
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //以下两个判断是防止越界（部分View被遮住）
            if (left > getPaddingLeft()) {//向右滑动时 超过了paddingLeft都返回这个值（保持原位）
                return getPaddingLeft();
            }
            if (left < getPaddingLeft() - mDragDistance) {//向左滑动 整个隐藏的View都滑出来了 超过了getPaddingLeft() - mDragDistance都返回这个值（保持原位）
                return getPaddingLeft() - mDragDistance;
            }
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return getPaddingTop();
        }

        //返回拖拽子View在相应方向上可以被拖动的最远距离，默认为0
        @Override
        public int getViewHorizontalDragRange(View child) {
            return mDragDistance;
        }

        //当前拖拽的view松手或者ACTION_CANCEL时调用，xvel、yvel为离开屏幕时的速率
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //getPaddingLeft() - mDragView.getLeft() → 控件不动时为0
            int getPaddingLeft = getPaddingLeft();
            int getmDragViewLeft = mDragView.getLeft();
            int temp = getPaddingLeft() - mDragView.getLeft();
            int tempdragSlop = mDragSlop;
            if (getPaddingLeft() - mDragView.getLeft() < mDragSlop) {//最终位置的判断
                smoothSlideHide();
            } else {
                smoothSlideOpen();
            }
            ViewCompat.postInvalidateOnAnimation(userDefinedView.this);
//            postInvalidate();
        }

        //被拖拽的View位置变化时回调，changedView为位置变化的view，left、top变化后的x、y坐标，dx、dy为新位置与旧位置的偏移量
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            mHideView.layout(mHideView.getLeft() + dx, mHideView.getTop(), mHideView.getRight() + dx, mHideView.getBottom());
            ViewCompat.postInvalidateOnAnimation(userDefinedView.this);
//            postInvalidate();
        }
    }

    private void smoothSlideHide(){
        //smoothSlideViewTo方法某个View自动滚动到指定的位置，如果这个方法返回true，那么在接下来动画移动的每一帧中都会回调continueSettling(boolean)方法，直到结束
        mDragHelper.smoothSlideViewTo(getHideView(), mWidth - getPaddingRight(), getPaddingTop());
        mDragHelper.smoothSlideViewTo(getDragView(), getPaddingLeft(), getPaddingTop());
        mState = STATE_CLOSE;
    }

    private void smoothSlideOpen(){
        mDragHelper.smoothSlideViewTo(getHideView(), mWidth - getPaddingRight() - mDragDistance, getPaddingTop());
        mDragHelper.smoothSlideViewTo(getDragView(), getPaddingLeft() - mDragDistance, getPaddingTop());
        mState = STATE_OPEN;
    }

    public View getDragView() {
        if (getChildCount() == 0) return null;
        return getChildAt(0);
    }

    public View getHideView() {
        if (getChildCount() == 1) return null;
        return getChildAt(1);
    }

    private void setState(int state){
        this.mState = state;
    }

    private int getState(){
        return mState;
    }

    /**
     * 关闭滑动
     */
    public void close(){
        if (mState == STATE_OPEN){
            smoothSlideHide();
            ViewCompat.postInvalidateOnAnimation(userDefinedView.this);
        }
    }
}
