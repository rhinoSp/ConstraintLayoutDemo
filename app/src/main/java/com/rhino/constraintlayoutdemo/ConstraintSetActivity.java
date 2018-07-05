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

