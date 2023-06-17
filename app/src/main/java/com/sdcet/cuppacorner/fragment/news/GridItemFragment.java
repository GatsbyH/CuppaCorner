/*
 * Copyright (C) 2023 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sdcet.cuppacorner.fragment.news;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.sdcet.cuppacorner.core.BaseFragment;
import com.sdcet.cuppacorner.adapter.entity.Food;
import com.xuexiang.cuppacorner.databinding.FragmentGridItemBinding;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhen
 * @since 2021/6/30 1:21 AM
 */
@Page
public class GridItemFragment extends BaseFragment<FragmentGridItemBinding> {

    public static final String KEY_TITLE_NAME = "title_name";

    /**
     * 自动注入参数，不能是private
     */
    @AutoWired(name = KEY_TITLE_NAME)
    String title;

    RecyclerView recyclerView;
    private List<Food> foodList = new ArrayList<>();

    @NonNull
    @Override
    protected FragmentGridItemBinding viewBindingInflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToRoot) {
        return FragmentGridItemBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initArgs() {
        // 自动注入参数必须在initArgs里进行注入
        XRouter.getInstance().inject(this);
    }

    @Override
    protected String getPageTitle() {
        return title;
    }





    @Override
    protected void initViews() {
        if ("美食".equals(title)) {
//            recyclerView = getView().findViewById(R.id.case56_rv);
            if (recyclerView == null) {
                Log.e("GridItemFragment", "recyclerView is null!");
            }
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
//            foodList.add(new Food("美食1", R.drawable.ic_app_slogon));
//            foodList.add(new Food("美食2", R.drawable.ic_app_slogon));
//            foodList.add(new Food("美食3", R.drawable.ic_app_slogon));
        } else if ("视频".equals(title)) {
            // 展示视频相关的内容
        } else if ("图片".equals(title)) {
            // 展示图片相关的内容
        }
        Log.d("GridItemFragment", "initViews() executed successfully");
    }


}
