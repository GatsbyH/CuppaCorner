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

package com.sdcet.cuppacorner.fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.sdcet.cuppacorner.adapter.base.MusicalRecyclerAdapter;
import com.sdcet.cuppacorner.core.BaseFragment;
import com.sdcet.cuppacorner.adapter.entity.Musical;
import com.sdcet.cuppacorner.utils.TokenUtils;
import com.xuexiang.cuppacorner.databinding.FragmentMusicalBinding;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.XHttpSDK;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xrouter.annotation.AutoWired;
import com.xuexiang.xrouter.launcher.XRouter;

import java.util.ArrayList;
import java.util.List;

@Page
public class MusicalFragment extends BaseFragment<FragmentMusicalBinding> {

    public static final String KEY_TITLE_NAME = "title_name";

    /**
     * 自动注入参数，不能是private
     */
    @AutoWired(name = KEY_TITLE_NAME)
    String title;
    MusicalRecyclerAdapter adapter;
    RecyclerView recyclerView;
    private List<Musical> musicalList = new ArrayList<>();
    @Override
    protected void initArgs() {
        // 自动注入参数必须在initArgs里进行注入
        XRouter.getInstance().inject(this);
    }
    @Override
    protected String getPageTitle() {
        return title;
    }
    @NonNull
    @Override
    protected FragmentMusicalBinding viewBindingInflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToRoot) {
        return FragmentMusicalBinding.inflate(inflater, container, attachToRoot);
    }

    @Override
    protected void initViews() {
        recyclerView = binding.case56Mu;
        if (recyclerView == null) {
            Log.e("MusicalFragment", "RecyclerView is null");
            return;
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        XHttpSDK.setSuccessCode(200);
        String token = TokenUtils.getToken(); // 获取JWT令牌
        XHttp.get("androidapi/loadMusicals")
                .headers("Authorization", "Bearer " + token)
                .syncRequest(false) //异步请求
                .onMainThread(true) //回到主线程
                .execute(new SimpleCallBack<List<Musical>>() {
                    @Override
                    public void onSuccess(List<Musical> response) throws Throwable {
                        if (response != null && response.size() > 0) {
                            for (Musical item : response) {
                                musicalList.add(item);
                                Log.e("foodlist",musicalList.toString());
                            }
                            adapter = new MusicalRecyclerAdapter(musicalList); //重新设置适配器
                            recyclerView.setAdapter(adapter); //重新设置RecyclerView的适配器
                            adapter.notifyDataSetChanged(); //通知适配器数据集已更改
                            Log.e("YourClass", "getDemoNewInfos success, response: " + response.toString());
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        e.printStackTrace();
                        Log.e("YourClass", "getDemoNewInfos error: " + e.getMessage());
                    }
                });
        if (musicalList == null || musicalList.isEmpty()) {
            Log.e("FoodFragment", "FoodList is null or empty");
            return;
        }
//      adapter = new ShequRecyclerAdapter(foodList);
        if (adapter == null) {
            Log.e("FoodFragment", "Adapter is null");
            return;
        }
        recyclerView.setAdapter(adapter);

        Log.d("FoodFragment", "initViews() executed successfully");

    }
    private void refreshData(boolean isLoadMore) {
        XHttpSDK.setSuccessCode(200);
        String token = TokenUtils.getToken(); // 获取JWT令牌
        XHttp.get("androidapi/loadMusicals")
                .headers("Authorization", "Bearer " + token)
                .syncRequest(false) //异步请求
                .onMainThread(true) //回到主线程
                .execute(new SimpleCallBack<List<Musical>>() {
                    @Override
                    public void onSuccess(List<Musical> response) throws Throwable {
                        if (response != null && response.size() > 0) {
                            List<Musical> newMusicalList = new ArrayList<>();
                            for (Musical item : response) {
                                newMusicalList.add(item);
                            }

                            if (isLoadMore) {
                                // 上拉加载时，只添加新数据
                                musicalList.addAll(newMusicalList);
                            } else {
                                // 下拉刷新时，用新数据替换原数据
                                musicalList.clear();
                                musicalList.addAll(newMusicalList);
                            }

                            adapter.notifyDataSetChanged(); //通知适配器数据集已更改
                            Log.e("YourClass", "getDemoNewInfos success, response: " + response.toString());
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        e.printStackTrace();
                        Log.e("YourClass", "getDemoNewInfos error: " + e.getMessage());
                    }
                });

        if (musicalList.isEmpty()) {
            Log.e("FoodFragment", "FoodList is empty");
            return;
        }

        Log.d("FoodFragment", "initViews() executed successfully");
    }
    @Override
    protected void initListeners() {
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshLayout.getLayout().postDelayed(() -> {
                refreshData(false); // 下拉刷新时传入false
                binding.refreshLayout.finishRefresh(true); // 结束下拉刷新
            }, 1000);
        });
        //上拉加载
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            refreshLayout.getLayout().postDelayed(() -> {
                refreshData(true); // 上拉加载时传入true
                binding.refreshLayout.finishLoadMore(true); // 结束上拉加载
            }, 1000);
        });
    }
}