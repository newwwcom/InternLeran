# InternLeran
------------------------------------------分割线-------------------------------------------------------
先让我们看一下GridView里面一些“特有”属性吧：
android:numColumns  /*列数，可以直接指定1列，2列---N列   *auto_fit 自动指定列数：columnWidth和Spacing来计算。比如宽100x，columnWidth为列宽10px，Spacing为间隙5px，那么用总的去除以每一个item的宽，就能得到能放下几行，进行一系列计算。*/
android:columnWidth //每列的宽度，也就是Item的宽度
android:verticalSpacing //竖直间距，行间距
android:horizontalSpacing //水平间距，列间距
（即使设置行间距和列间距为0dp，仍然有可能出现图中的情况）
android:stretchMode /*可以理解缩放模式 columnWidth （列宽缩放） spacingWidth（间隙缩放）当我间隙为0dp，成功的没有显示任意一个item，最重要的一点是没有得到期望的效果*/

android:cacheColorHint="#00000000" //去除拖动时默认的黑色背景 （99%会用到）

android:listSelector="#00000000"  //去除选中时的黄色底色 （99%会用到）

android:scrollbars="none" //隐藏GridView的滚动条

android:fadeScrollbars="true" //设置为true就可以实现滚动条的自动隐藏和显示

android:fastScrollEnabled="true" //GridView出现快速滚动的按钮(至少滚动4页才会显示)

android:fadingEdge="none" //GridView衰落(褪去)边缘颜色为空，缺省值是vertical。(可以理解为上下边缘的提示色)

android:fadingEdgeLength="10dip" //定义的衰落(褪去)边缘的长度

android:stackFromBottom="true" //设置为true时，你做好的列表就会显示你列表的最下面

android:transcriptMode="alwaysScroll" //当你动态添加数据时，列表将自动往下滚动最新的条目可以自动滚动到可视范围内

android:drawSelectorOnTop="false"  //点击某条记录不放，颜色会在记录的后面成为背景色,内容的文字可见(缺省为false)
------------------------------------------分割线-------------------------------------------------------
