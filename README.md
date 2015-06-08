# SlidingMenuOne
最简单的侧滑菜单

1.自定义View使其继承HorizontalScrollView

2.HorizontalScrollView里面是一个LinearLayout,方向是横向的，因为我们的菜单和内容是横向放置的。

3.设置自己的宽和高和子View的宽和高

4.侧滑菜单与屏幕右侧的距离为50像素(通过辅助工具类将dp转换为像素值TypedValue.applyDimension

(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());)

5.设置侧滑菜单的宽度就是屏幕的宽度 - 侧滑菜单与屏幕右侧的距离

6.设置内容的宽度就是屏幕的宽度

7.接下来就是设置我们的自己的宽和高，也就是包裹菜单和内容的LinearLayout的宽和高，这里由于是包裹

了菜单区域和内容区域，所以我们不必显示的设置LinearLayout的宽和高。

8.onMeasure这个方法有可能被多次调用，所以我们设置一个布尔值，默认为false；然后在onMeasure这个

方法里进行判断一下，如果为false就设置我们子View的宽和高，最后把布尔值设为true。只设置一次我们

子View的宽和高，不要多次设置。【选】

9.重写onLayout方法，通过设置偏移量将Menu隐藏，this.scrollTo(mMenuWidth, 0);mMenuWidth为侧滑菜

单的宽度。

10.用户手指抬起时，判断侧滑菜单的宽度是否大于内容区域左侧多出来的部分，是则显示侧滑菜单，否则

隐藏。
