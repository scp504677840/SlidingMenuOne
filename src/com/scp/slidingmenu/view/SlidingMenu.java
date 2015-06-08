package com.scp.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * �໬�˵�
 * 
 * @author �δ���
 *
 */
public class SlidingMenu extends HorizontalScrollView {
	private ViewGroup mMenu;// �˵�����
	private ViewGroup mContent;// ��������
	private LinearLayout mWapper;
	private int mScreenWidth;// ��Ļ�Ŀ��
	private int mMenuWidth;// �໬�˵����
	private int mMenuRightPadding = 50;// �໬�˵�����Ļ�Ҳ�ľ��룬��λdp
	private boolean once;// ֻ����һ����View�Ŀ�͸�

	/**
	 * δʹ���Զ�������ʱ�Զ�����
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// ��ȡ��Ļ�Ŀ�͸�
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
		// dpת��Ϊ����
		mMenuRightPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources()
						.getDisplayMetrics());
	}

	/**
	 * ������View�Ŀ�͸ߣ������Լ��Ŀ�͸�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!once) {
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			// ���ò໬�˵��Ŀ��
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			// �������ݵĿ��
			mContent.getLayoutParams().width = mScreenWidth;
			once = true;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * ͨ������ƫ������Menu����
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}
	}

	/**
	 * ������ָ�����¼�
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			// �жϵ�ǰ�໬�˵��Ŀ���Ƿ��������������������Ĳ��֣�������ʾ�໬�˵�����������
			int scrollX = getScrollX();// ����������������Ĳ���
			if (scrollX >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
			} else {
				this.smoothScrollTo(0, 0);
			}
			return true;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

}
