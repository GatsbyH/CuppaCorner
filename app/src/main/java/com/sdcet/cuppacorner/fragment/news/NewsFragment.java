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

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sdcet.cuppacorner.core.BaseFragment;
import com.sdcet.cuppacorner.fragment.FoodFragment;
import com.sdcet.cuppacorner.fragment.MusicalFragment;
import com.sdcet.cuppacorner.utils.DemoDataProvider;
import com.sdcet.cuppacorner.utils.Utils;
import com.xuexiang.cuppacorner.R;
import com.sdcet.cuppacorner.adapter.base.broccoli.BroccoliSimpleDelegateAdapter;
import com.sdcet.cuppacorner.adapter.base.delegate.SimpleDelegateAdapter;
import com.sdcet.cuppacorner.adapter.base.delegate.SingleDelegateAdapter;
import com.sdcet.cuppacorner.adapter.entity.NewInfo;
import com.xuexiang.cuppacorner.databinding.FragmentNewsBinding;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.utils.XToastUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleImageBanner;
import com.xuexiang.xui.widget.imageview.ImageLoader;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import me.samlss.broccoli.Broccoli;

/**
 * 首页动态
 *
 * @author zhen
 * @since 2019-10-30 00:15
 */
@Page(anim = CoreAnim.none)
public class NewsFragment extends BaseFragment<FragmentNewsBinding> {

    private SimpleDelegateAdapter<NewInfo> mNewsAdapter;

    @NonNull
    @Override
    protected FragmentNewsBinding viewBindingInflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, boolean attachToRoot) {
        return FragmentNewsBinding.inflate(inflater, container, attachToRoot);
    }

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        binding.recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        DemoDataProvider.getBannerList();

        //轮播条
        SingleDelegateAdapter bannerAdapter = new SingleDelegateAdapter(R.layout.include_head_view_banner) {
            @Override
            public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
                DemoDataProvider provider = new DemoDataProvider();
                List<BannerItem> bannerList = provider.getBannerListSync();
                if (bannerList != null&& !bannerList.isEmpty()) {
                    Log.e("YourClass", "bannerItemList: " + bannerList.get(0).getImgUrl());
                    SimpleImageBanner banner = holder.findViewById(R.id.sib_simple_usage);
                    banner.setSource(bannerList)
                            .setOnItemClickListener((view, item, position1) -> XToastUtils.toast(bannerList.get(position1).getTitle())).startScroll();
                }
            }
        };

        //九宫格菜单
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setPadding(0, 16, 0, 0);
        gridLayoutHelper.setVGap(10);
        gridLayoutHelper.setHGap(0);
        SimpleDelegateAdapter<AdapterItem> commonAdapter = new SimpleDelegateAdapter<AdapterItem>(R.layout.adapter_common_grid_item, gridLayoutHelper, DemoDataProvider.getGridItems(getContext())) {
            @Override
            protected void bindData(@NonNull RecyclerViewHolder holder, int position, AdapterItem item) {
                if (item != null) {
                    RadiusImageView imageView = holder.findViewById(R.id.riv_item);
                    imageView.setCircle(true);
                    ImageLoader.get().loadImage(imageView, item.getIcon());
                    holder.text(R.id.tv_title, item.getTitle().toString().substring(0, 1));
                    holder.text(R.id.tv_sub_title, item.getTitle());

                    holder.click(R.id.ll_container, v -> {
                        XToastUtils.toast("点击了：" + item.getTitle());
                        // 注意: 这里由于NewsFragment是使用Viewpager加载的，并非使用XPage加载的，因此没有承载Activity， 需要使用openNewPage。
                        if (item.getTitle().equals("美食")) {
                            openNewPage(FoodFragment.class, FoodFragment.KEY_TITLE_NAME, item.getTitle());
                        } else if (item.getTitle().equals("电影演出")) {
                            Utils.goWeb(holder.itemView.getContext(), "https://dianying.taobao.com/");
//                            openNewPage(TravelFragment.class, TravelFragment.KEY_TITLE_NAME, item.getTitle());
                        } else if (item.getTitle().equals("购物")) {
                            Utils.goWeb(holder.itemView.getContext(), "https://www.taobao.com/");
//                            openNewPage(ShoppingFragment.class, ShoppingFragment.KEY_TITLE_NAME, item.getTitle());
                        } else if (item.getTitle().equals("带专")) {
                            Utils.goWeb(holder.itemView.getContext(), "https://www.sdcet.edu.cn/");
//                            openNewPage(EntertainmentFragment.class, EntertainmentFragment.KEY_TITLE_NAME, item.getTitle());
                        } else if (item.getTitle().equals("音乐剧")) {
                            openNewPage(MusicalFragment.class, MusicalFragment.KEY_TITLE_NAME, item.getTitle());
                        } else if (item.getTitle().equals("外卖")) {
                            Utils.goWeb(holder.itemView.getContext(), "https://h5.waimai.meituan.com/waimai/mindex/home?type=main_page&utm_source=60030&channel=mtib");
//                            openNewPage(MedicalFragment.class, MedicalFragment.KEY_TITLE_NAME, item.getTitle());
                        }else if (item.getTitle().equals("动漫")) {
                            Utils.goWeb(holder.itemView.getContext(), "http://kudm8.com/");
//                            openNewPage(MedicalFragment.class, MedicalFragment.KEY_TITLE_NAME, item.getTitle());
                        }else if (item.getTitle().equals("音乐")) {
                            Utils.goWeb(holder.itemView.getContext(), "https://music.163.com/");
//                            openNewPage(MedicalFragment.class, MedicalFragment.KEY_TITLE_NAME, item.getTitle());
                        }
                    });
                }
            }
        };

        //资讯的标题
        SingleDelegateAdapter titleAdapter = new SingleDelegateAdapter(R.layout.adapter_title_item) {
            @Override
            public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
                holder.text(R.id.tv_title, "资讯");
                holder.text(R.id.tv_action, "更多");
                holder.click(R.id.tv_action, v -> XToastUtils.toast("更多"));
            }
        };

        //资讯
        mNewsAdapter = new BroccoliSimpleDelegateAdapter<NewInfo>(R.layout.adapter_news_card_view_list_item, new LinearLayoutHelper(), DemoDataProvider.getEmptyNewInfo()) {
            @Override
            protected void onBindData(RecyclerViewHolder holder, NewInfo model, int position) {
                if (model != null) {
                    holder.text(R.id.tv_user_name, model.getUserName());
                    holder.text(R.id.tv_tag, model.getTag());
                    holder.text(R.id.tv_title, model.getTitle());
                    holder.text(R.id.tv_summary, model.getSummary());
                    holder.text(R.id.tv_praise, model.getPraise() == 0 ? "点赞" : String.valueOf(model.getPraise()));
                    holder.text(R.id.tv_comment, model.getComment() == 0 ? "评论" : String.valueOf(model.getComment()));
                    holder.text(R.id.tv_read, "阅读量 " + model.getRead());
                    holder.image(R.id.iv_image, model.getImageUrl());
                    holder.click(R.id.card_view, v -> Utils.goWeb(getContext(), model.getDetailUrl()));
                }
            }

            @Override
            protected void onBindBroccoli(RecyclerViewHolder holder, Broccoli broccoli) {
                broccoli.addPlaceholders(
                        holder.findView(R.id.tv_user_name),
                        holder.findView(R.id.tv_tag),
                        holder.findView(R.id.tv_title),
                        holder.findView(R.id.tv_summary),
                        holder.findView(R.id.tv_praise),
                        holder.findView(R.id.tv_comment),
                        holder.findView(R.id.tv_read),
                        holder.findView(R.id.iv_image)
                );
            }
        };

        DelegateAdapter delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        delegateAdapter.addAdapter(bannerAdapter);
        delegateAdapter.addAdapter(commonAdapter);
        delegateAdapter.addAdapter(titleAdapter);
        delegateAdapter.addAdapter(mNewsAdapter);
        mNewsAdapter.refresh(DemoDataProvider.getDemoNewInfos());
        binding.recyclerView.setAdapter(delegateAdapter);
    }

    @Override
    protected void initListeners() {
        //下拉刷新
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mNewsAdapter.refresh(DemoDataProvider.getDemoNewInfos());
                refreshLayout.finishRefresh();
            }, 1000);
        });
        //上拉加载
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            // TODO: 2020-02-25 这里只是模拟了网络请求
            refreshLayout.getLayout().postDelayed(() -> {
                mNewsAdapter.loadMore(DemoDataProvider.getDemoNewInfos());
                refreshLayout.finishLoadMore();
            }, 1000);
        });
        binding.refreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
    }
}
