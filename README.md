# 一、概述
[ConstraintLayout](https://developer.android.google.cn/training/constraint-layout/)，即约束布局, 已经推出很久了。布局方式与RelativeLayout有点类似，但可以说是RelativeLayout的升级版，ConstraintLayout可以完全代替其他布局, 减少布局的层级, 优化渲染性能。在新版Android Studio中, ConstraintLayout已替代RelativeLayout, 成为HelloWorld项目的默认布局。
# 二、添加依赖
新版本的Android studio新建工程的时候，默认会添加依赖，并且默认的布局activity_main.xml中的根布局RelativeLayout已被替换为ConstraintLayout。如果没有依赖ConstraintLayout，就需要手动引入ConstraintLayout，在build.gradle中加入：
```
dependencies {
    ...
    implementation 'com.android.support.constraint:constraint-layout:1.1.2'
}
```
# 三、属性介绍
## 1. 常用属性
下面是常用属性介绍，更多参见：[values.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/Other/values.xml)，使用栗子见：[normal.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/normal.xml)
```
layout_constraintLeft_toLeftOf          // 左边左对齐
layout_constraintLeft_toRightOf         // 左边右对齐
layout_constraintRight_toLeftOf         // 右边左对齐
layout_constraintRight_toRightOf        // 右边右对齐
layout_constraintTop_toTopOf            // 上边顶部对齐
layout_constraintTop_toBottomOf         // 上边底部对齐
layout_constraintBottom_toTopOf         // 下边顶部对齐
layout_constraintBottom_toBottomOf      // 下边底部对齐
layout_constraintStart_toEndOf          // 起始边向尾部对齐
layout_constraintStart_toStartOf        // 起始边向起始边对齐
layout_constraintEnd_toStartOf          // 尾部向起始边对齐
layout_constraintEnd_toEndOf            // 尾部向尾部对齐
layout_constraintBaseline_toBaselineOf  // 文字的底部线对齐，用于含文本的控件对齐基线
layout_constraintDimensionRatio         // 宽高比"2:1"、"H,2:1"或"W,2:1"
```
## 2. Barrier
Barrier是一个看不见的视图，如果其引用形成Barrier的视图的大小或位置发生变化，则Barrier将其大小调整为所引用视图的最大高度或宽度。就像一个屏障一样，阻止一个或者多个控件越过自己，当某个控件要越过自己的时候，Barrier会自动移动，避免自己被覆盖。Barrier可以是垂直或水平的，并且可以创建到引用视图的顶部、底部、左侧或右侧。以下示例可以看出，当调整控件flow1和flow2的大小或位置时，左侧Barrier（竖线阴影）调整其位置。这里的控件see约束在一左一右两个Barrier的正中，为了更好地体现Barrier的位置变化。<br>
![barrier](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/ScreenCapture/barrier.gif)<br>
```xml
<android.support.constraint.Barrier
        android:id="@+id/id_barrier1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:barrierAllowsGoneWidgets="true"
        app:barrierDirection="right"
        app:constraint_referenced_ids="flow1,flow2" />
```
这是Barrier的用法，这里就不贴详细代码了，详细代码见[barrier.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/guideline.xml)。下面是这里用到的attr介绍：
```
<attr format="boolean" name="barrierAllowsGoneWidgets"/>  <!-- 定义在引用形成Barrier的视图gone时是否仍然有效 -->
<attr format="enum" name="barrierDirection">  <!-- 定义在引用形成Barrier的视图相对位置 -->
	<enum name="left" value="0"/>
	<enum name="right" value="1"/>
	<enum name="top" value="2"/>
	<enum name="bottom" value="3"/>
	<enum name="start" value="5"/>
	<enum name="end" value="6"/>
</attr>
<attr format="string" name="constraint_referenced_ids"/>  <!-- 是用来包含形成Barrier的视图ID列表 -->
```
## 3. Guideline 
Guidelines可以简化视图布局的对齐方式，特别是如果您在许多元素上重复使用了相同的边界值。Guidelines可以是垂直或水平的，可以指定一个开始的dp值和结束的dp值或者可以相对于屏幕的百分比。使用方法见下面的栗子：<br>
![guideline](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/ScreenCapture/guidelines.png)<br>
```xml
<android.support.constraint.Guideline
        android:id="@+id/guideline1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintGuide_begin="150dp"/>
```
这里用了4个GuideLine，详细代码见[guideline.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/guideline.xml)。下面是这里用到的attr介绍：
```
<attr format="dimension" name="layout_constraintGuide_begin"/> <!-- 距离屏幕开始尺寸，如：100dp -->
<attr format="dimension" name="layout_constraintGuide_end"/> <!-- 距离屏幕结束尺寸，如：100dp -->
<attr format="float" name="layout_constraintGuide_percent"/> <!-- 距离屏幕开始比例，如：0.85 -->
```
## 4. Chains
Chains 链是一种特殊的约束让多个 chains 链连接的 Views 能够平分剩余空间位置。在 Android 传统布局特性里面最相似的应该是 LinearLayout 中的权重比 weight ，但 Chains 链能做到的远远不止权重比 weight 的功能。使用方法见下面的栗子：<br>
![chains](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/ScreenCapture/chains.png)<br>
这里体现了Chains的常规用法，详情见[chains.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/chains.xml)。下面是这里用到的attr介绍：
```
<attr format="enum" name="layout_constraintHorizontal_chainStyle"> <!-- 横向，默认spread -->
	<enum name="spread" value="0"/>
	<enum name="spread_inside" value="1"/>
	<enum name="packed" value="2"/>
</attr>
<attr format="enum" name="layout_constraintVertical_chainStyle"> <!-- 纵向，默认spread -->
	<enum name="spread" value="0"/>
	<enum name="spread_inside" value="1"/>
	<enum name="packed" value="2"/>
</attr>
```
## 5. Group
Group帮助你对一组控件进行设置。最常见的情况是控制一组控件的visibility。你只需把控件的id添加到Group，就能同时对里面的所有控件进行操作。使用方法见下面的栗子：
```
<android.support.constraint.Group
	android:id="@+id/group"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	app:constraint_referenced_ids="flow1,flow2" />
```
通过constraint_referenced_ids指定统一控制的控件，如果设置group不可见，flow1和flow2也将变为不可见，详情见[group.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/group.xml)。
## 6. Placeholder
Placeholder就是用来一个占位的东西，它可以把自己的内容设置为ConstraintLayout内的其它view。因此它用来写布局的模版，也可以用来动态修改UI的内容。
首先编写模板[placeholder_template.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/placeholder_template.xml)，如下：
```xml
<?xml version="1.0" encoding="utf-8"?>
<merge xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
 
    <android.support.constraint.Placeholder
        android:id="@+id/template_banner"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:content="@+id/banner"
        app:layout_constraintDimensionRatio="w,1:3"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent" />
 
</merge>
```
然后编写真正的布局[placeholder.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/placeholder.xml)，在该布局中include模板布局，注意这里的ImageView没有进行任何约束，由模板来控制。
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>
 
    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
 
        <include
            android:id="@+id/template"
            layout="@layout/placeholder_template" />
 
        <ImageView
            android:id="@+id/banner"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@mipmap/ic_launcher" />
 
        <TextView
            android:id="@+id/text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="#1a000000"
            android:text="xxx" />
 
        <Button
            android:id="@+id/change"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:text="change"
            app:layout_constraintBottom_toBottomOf="parent" />
 
    </android.support.constraint.ConstraintLayout>
 
</layout>
```
当点击change的时候在代码里面动态修改PlaceHolder的contentId为text，效果如下：<br>
![placeholder](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/ScreenCapture/placeholder.gif)<br>
## 7. Circular Positioning
Circular Positioning顾名思义，它可以约束一个view相对于另一个view的弧度和半径。我们直接来看使用方法，如下：
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>
 
    <android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
 
        <TextView
            android:id="@+id/center"
            android:layout_width="20dp"
            android:layout_height="20dp"
            android:background="#612"
            android:gravity="center"
            android:textColor="@android:color/white"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"/>
 
        <ImageView
            android:id="@+id/img"
            android:layout_width="30dp"
            android:layout_height="30dp"
            android:src="@mipmap/ic_launcher_round"
            app:layout_constraintCircle="@+id/center"
            app:layout_constraintCircleAngle="120"
            app:layout_constraintCircleRadius="90dp" />
 
    </android.support.constraint.ConstraintLayout>
 
</layout>
```
这里定义了一个ImageView，约束为以TextView为圆心，90dp半径，120度角。然后在activity中不停地改变ImageView的角度，就能看到ImageView绕TextView旋转。如果没有ConstraintLayout的这个新功能的话，你可能会用自定义view来实现。效果如下：<br>
![circularpositioning](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/ScreenCapture/circularpositioning.gif)<br>
## 7. ConstraintSet
ConstraintSet能使我们在代码中轻松地改变控件的位置大小，再也不用LayoutParams了。。我们直接来看使用方法，很简单，我们直接看[ConstraintSetActivity.java](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/java/com/rhino/constraintlayoutdemo/ConstraintSetActivity.java)代码，如下：（这里省略布局代码，详情见：[constraint_set.xml](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/app/src/main/res/layout/constraint_set.xml)）
```java
package com.rhino.constraintlayoutdemo;
 
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
 
import com.rhino.constraintlayoutdemo.databinding.ConstraintSetBinding;
 
/**
 * @author LuoLin
 * @since Create on 2018/7/5.
 */
public class ConstraintSetActivity extends AppCompatActivity {
 
    private ConstraintSetBinding binding;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.constraint_set);
        binding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change();
            }
        });
    }
 
    private void change() {
        // 首先，要声明一下ConstraintSet对象
        ConstraintSet constraintSet = new ConstraintSet();
        // 然后clone，会有四个clone方法,可以任选其一
        // constraintSet.clone(ConstraintLayout constraintLayout);
        // constraintSet.clone(ConstraintSet set);
        // constraintSet.clone(Context context, int constraintLayoutId);
        // constraintSet.clone(Constraints constraints);
        constraintSet.clone(binding.constraintLayout);
 
        // set.connect(int startID, int startSide, int endID, int endSide, int margin);
        // 设置flow1控件的顶边与flow2的底边对齐,且之间margin值是50px:
        constraintSet.connect(binding.flow1.getId(), ConstraintSet.TOP, binding.flow2.getId(), ConstraintSet.BOTTOM, 50);
 
        // set.centerHorizontally(int viewId, int toView)
        // 设置flow2水平剧中于parent
        constraintSet.centerVertically(R.id.flow2, ConstraintSet.PARENT_ID);
 
        // set.constrainHeight(int viewId, int height);
        // 设置flow1的高度为120px
        constraintSet.constrainHeight(R.id.flow1, 300);
 
        // ...还有很多其他方法，可以自行尝试一下
 
        // 最后,apply一下使设置生效
        constraintSet.applyTo(binding.constraintLayout);
    }
}
```
接下来我们看下运行效果：<br>
![constraintset](https://github.com/rhinoSp/ConstraintLayoutDemo/blob/master/ScreenCapture/constraintset.gif)<br>


# Question / Contact Me / Hire Me

Please feel free to ping me at rhino_luo@163.com.

# License
Copyright 2018 rhino luo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
