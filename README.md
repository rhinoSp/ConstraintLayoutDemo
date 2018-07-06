# ConstraintLayoutDemo
The library of ConstraintLayout.
# 一、概述
[ConstraintLayout](https://developer.android.google.cn/training/constraint-layout/)，即约束布局, 已经推出很久了。布局方式与RelativeLayout有点类似，但可以说是RelativeLayout的升级版，ConstraintLayout可以完全代替其他布局, 减少布局的层级, 优化渲染性能。在新版Android Studio中, ConstraintLayout已替代RelativeLayout, 成为HelloWorld项目的默认布局。
# 二、添加依赖
新版本的Android studio新建工程的时候，默认会添加依赖，并且默认的布局activity_main.xml中的根布局RelativeLayout已被替换为ConstraintLayout。如果没有依赖ConstraintLayout，就需要手动引入ConstraintLayout，在build.gradle中加入：
```
dependencies {
    ...
    implementation 'com.android.support.constraint:constraint-layout:1.1.0'
}
```
# 三、属性介绍
## 1. 常用属性
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
Barrier是一个看不见的视图，如果其引用形成Barrier的视图的大小或位置发生变化，则Barrier将其大小调整为所引用视图的最大高度或宽度。就像一个屏障一样，阻止一个或者多个控件越过自己，当某个控件要越过自己的时候，Barrier会自动移动，避免自己被覆盖。Barrier可以是垂直或水平的，并且可以创建到引用视图的顶部、底部、左侧或右侧。以下示例可以看出，当调整控件flow1和flow2的大小或位置时，左侧Barrier（竖线阴影）调整其位置。这里的控件see约束在一左一右两个Barrier的正中，为了更好地体现Barrier的位置变化。
![barrier](https://github.com/rhinoSp/ConstraintLayoutDemo/tree/master/app/ScreenCapture/barrier.gif)
```xml
<android.support.constraint.Barrier
        android:id="@+id/id_barrier1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:barrierAllowsGoneWidgets="true"
        app:barrierDirection="right"
        app:constraint_referenced_ids="flow1,flow2" />
```
这是Barrier的用法，这里就不贴详细代码了，避免篇幅过长不好阅读，详细代码见barrier.xml。下面是这里用到的attr介绍：
```xml
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
