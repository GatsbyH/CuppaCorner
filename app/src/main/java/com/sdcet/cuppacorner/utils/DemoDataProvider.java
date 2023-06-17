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

package com.sdcet.cuppacorner.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.xuexiang.cuppacorner.R;
import com.sdcet.cuppacorner.adapter.entity.NewInfo;
import com.sdcet.cuppacorner.adapter.entity.NewsInfo;
import com.xuexiang.xaop.annotation.MemoryCache;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.XHttpSDK;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示数据
 *
 * @author zhen
 * @since 2018/11/23 下午5:52
 */
public class DemoDataProvider {

    public static String[] titles = new String[]{
            "伪装者:胡歌演绎'痞子特工'",
            "无心法师:生死离别!月牙遭虐杀",
            "花千骨:尊上沦为花千骨",
            "综艺饭:胖轩偷看夏天洗澡掀波澜",
            "碟中谍4:阿汤哥高塔命悬一线,超越不可能",
    };

    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160323071011277.jpg",//伪装者:胡歌演绎"痞子特工"
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144158380433341332.jpg",//无心法师:生死离别!月牙遭虐杀
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160286644953923.jpg",//花千骨:尊上沦为花千骨
            "http://photocdn.sohu.com/tvmobilemvms/20150902/144115156939164801.jpg",//综艺饭:胖轩偷看夏天洗澡掀波澜
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144159406950245847.jpg",//碟中谍4:阿汤哥高塔命悬一线,超越不可能
    };

//    @MemoryCache
//    public static List<BannerItem> getBannerList() {
//        List<BannerItem> list = new ArrayList<>();
//        for (int i = 0; i < urls.length; i++) {
//            BannerItem item = new BannerItem();
//            item.imgUrl = urls[i];
//            item.title = titles[i];
//            list.add(item);
//        }
//        return list;
//    }
//   @MemoryCache(value = "bannerList")
//    public static void getBannerList() {
//        List<BannerItem> list = new ArrayList<>();
//        XHttpSDK.setSuccessCode(200);
//        XHttp.get("wxapi/loadBaneer")
//                .syncRequest(false) //异步请求
//                .onMainThread(true) //回到主线程
//                .execute(new SimpleCallBack<List<BannerItem>>() {
//                    @Override
//                    public void onSuccess(List<BannerItem> response) throws Exception {
//                        Log.e("response",response.toString());
//                        Log.e("size",response.size()+"");
//                        if (response != null && response.size() > 0) {
//                            Log.e("item",response.get(0).toString());
//                            for (BannerItem item : response) {
//                                Log.e("item",item.getImgUrl());
//                                item.imgUrl = "http://10.10.12.155:8083" + item.getImgUrl();
//                                item.title = item.getTitle();
//                                list.add(item);
//                                Log.e("item.imgUrl", item.getImgUrl());
//                                Log.e("item.title", item.getTitle());
//                            }
//                            Log.e("listSize", list.size() + "");
//                            MyUtil.list = list;
//                            Log.e("utilListSize", MyUtil.list.size() + "");
//                        }
//                    }
//                    @Override
//                    public void onError(ApiException e) {
//                        e.printStackTrace();
//                    }
//                });
//    }
//
private static boolean mBannerListReady;
private static List<BannerItem> mBannerList;
//    @MemoryCache(value = "bannerList")
    public static void getBannerList() {
        XHttpSDK.setSuccessCode(200);
        List<BannerItem> list = new ArrayList<>();
        String token = TokenUtils.getToken(); // 获取JWT令牌
        XHttp.get("androidapi/loadBaneer")
                .headers("Authorization", "Bearer " + token)
                .syncRequest(false) //异步请求
                .onMainThread(true) //回到主线程
                .execute(new SimpleCallBack<List<com.sdcet.cuppacorner.adapter.entity.BannerItem>>() {
                    @Override
                    public void onSuccess(List<com.sdcet.cuppacorner.adapter.entity.BannerItem> response) throws Throwable {
                        if (response != null && response.size() > 0) {
                            for (com.sdcet.cuppacorner.adapter.entity.BannerItem item : response) {
                                BannerItem bannerItem = new BannerItem();
                                bannerItem.imgUrl = "http://10.10.12.155:8080" + item.getBannerImg();
                                bannerItem.title = item.getBannerTitle();
                                list.add(bannerItem);
                                Log.e("list",list.toString());
                            }
                            mBannerList = list;
                            mBannerListReady = true;
                            Log.e("YourClass", "getBannerList success, response: " + response.toString());
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        e.printStackTrace();
                        Log.e("YourClass", "getBannerList error: " + e.getMessage());
                    }
                });
    }

    public List<BannerItem> getBannerListSync() {
        if (mBannerListReady&& mBannerList != null) {
            Log.e("轮播图",mBannerList.toString());
            return mBannerList;
        } else {
            return null;
        }
    }
    /**
     * 用于占位的空信息
     *
     * @return
     */
//    @MemoryCache
//    public static List<NewInfo> getDemoNewInfos() {
//        List<NewInfo> list = new ArrayList<>();
//        list.add(new NewInfo("咖啡", "咖啡生豆")
//                .setSummary("咖啡生豆指的就是未经过烘焙的咖啡豆。无法直接冲煮，必须经过烘焙，研磨成粉才能有效萃取出里面的“精华”\n")
//                .setDetailUrl("http://www.coffeesalon.com/?p=4648")
//                .setImageUrl("http://www.coffeesalon.com/wp-content/uploads/2023/04/31681285410.jpg"));
//
//        list.add(new NewInfo("手冲咖啡", "云南咖啡豆品种类别主要特点和产区 云南小粒咖啡的起源和风味口感描述")
//                .setSummary("大家现在在市面上听到人们常说起云南小粒咖啡豆，它属于铁皮卡咖啡种，由于卡蒂姆咖啡豆种凭着自己的产量就占据了云南咖啡豆产量的大部分，所以大家平时讲到的云南小粒咖啡豆，其实就是云南卡蒂姆。在云南咖啡产区里，种植的99.99%的咖啡种都是卡蒂姆。\n")
//                .setDetailUrl("https://m.gafei.com/views-133496")
//                .setImageUrl("https://mgfcdn.coffeecdn.com/uploadfile/ueditor/image/202211/16697224056b8328.jpg"));
//
//        list.add(new NewInfo("Android", "XUpdate 一个轻量级、高可用性的Android版本更新框架")
//                .setSummary("XUpdate 一个轻量级、高可用性的Android版本更新框架。本框架借鉴了AppUpdate中的部分思想和UI界面，将版本更新中的各部分环节抽离出来，形成了如下几个部分：")
//                .setDetailUrl("https://juejin.im/post/5b480b79e51d45190905ef44")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2018/7/13/16492d9b7877dc21?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//        list.add(new NewInfo("Android/HTTP", "XHttp2 一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp进行组装")
//                .setSummary("一个功能强悍的网络请求库，使用RxJava2 + Retrofit2 + OKHttp组合进行封装。还不赶紧点击使用说明文档，体验一下吧！")
//                .setDetailUrl("https://juejin.im/post/5b6b9b49e51d4576b828978d")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2018/8/9/1651c568a7e30e02?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//
//        list.add(new NewInfo("源码", "Android源码分析--Android系统启动")
//                .setSummary("其实Android系统的启动最主要的内容无非是init、Zygote、SystemServer这三个进程的启动，他们一起构成的铁三角是Android系统的基础。")
//                .setDetailUrl("https://juejin.im/post/5c6fc0cdf265da2dda694f05")
//                .setImageUrl("https://user-gold-cdn.xitu.io/2019/2/22/16914891cd8a950a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1"));
//        return list;
//    }

//    @MemoryCache
//    public static List<NewInfo> getDemoNewInfos() {
//        List<NewInfo> list = new ArrayList<>();
//        list.add(new NewInfo("咖啡", "咖啡生豆")
//                .setSummary("咖啡生豆指的就是未经过烘焙的咖啡豆。无法直接冲煮，必须经过烘焙，研磨成粉才能有效萃取出里面的“精华”\n")
//                .setDetailUrl("http://www.coffeesalon.com/?p=4648")
//                .setImageUrl("http://www.coffeesalon.com/wp-content/uploads/2023/04/31681285410.jpg"));
//
//        list.add(new NewInfo("手冲咖啡", "云南咖啡豆品种类别主要特点和产区 云南小粒咖啡的起源和风味口感描述")
//                .setSummary("大家现在在市面上听到人们常说起云南小粒咖啡豆，它属于铁皮卡咖啡种，由于卡蒂姆咖啡豆种凭着自己的产量就占据了云南咖啡豆产量的大部分，所以大家平时讲到的云南小粒咖啡豆，其实就是云南卡蒂姆。在云南咖啡产区里，种植的99.99%的咖啡种都是卡蒂姆。\n")
//                .setDetailUrl("https://m.gafei.com/views-133496")
//                .setImageUrl("https://mgfcdn.coffeecdn.com/uploadfile/ueditor/image/202211/16697224056b8328.jpg"));
//
//        list.add(new NewInfo("精品咖啡", "果丁丁风味有莓果茶感 水洗果丁丁冲煮教你怎么冲出咖啡好风味")
//                .setSummary("在埃塞俄比亚，位于耶加雪菲东南端的沃卡产区，有一个成熟的咖啡豆生产团队，名叫「果丁丁合作社」。这个团队拥有成员约300名农民社员。")
//                .setDetailUrl("https://m.gafei.com/views-137430")
//                .setImageUrl("https://m.gafei.com/uploadfile/ueditor/image/202305/1685269952bedae1.png"));
//
//        list.add(new NewInfo("精品咖啡", "奶咖的奶泡要打发的绵绸度 奶泡的最佳打发温度60℃左右是最适合")
//                .setSummary("如果你打奶泡是为了拉制热的花式咖啡， 当然要先将牛奶加热至60°, (可选用全脂牛奶或高脂牛奶， 含脂量高的牛奶乳蛋白含量也高， 打出的奶泡更结实绵密， 含脂量高的奶， 口感非常润滑， 脂香气也高， 其奶味很香醇， 非常适合制作卡布基诺、摩卡、拿铁等花式咖啡。")
//                .setDetailUrl("https://m.gafei.com/views-130233")
//                .setImageUrl("https://m.gafei.com/uploads/allimg/190224/17-1Z224133124445.jpg"));
//
//        list.add(new NewInfo("手冲咖啡", "手冲咖啡教程新手手冲咖啡操作步骤 冲煮水温闷蒸时间研磨粉水比")
//                .setSummary("咖啡豆的新鲜度，研磨度，粉水比例，冲煮水温，冲煮手法，萃取时间。这六项步骤其中一项有变动，都会影响这咖啡最后的萃取风味。")
//                .setDetailUrl("https://m.gafei.com/views-130012")
//                .setImageUrl("https://m.gafei.com/uploads/allimg/210317/18-21031G53313204.jpg"));
//
//        return list;
//    }
    @MemoryCache
    public static List<NewInfo> getDemoNewInfos() {
        List<NewInfo> list = new ArrayList<>();
        XHttpSDK.setSuccessCode(200);
        String token = TokenUtils.getToken(); // 获取JWT令牌
        XHttp.get("androidapi/loadNews")
                .headers("Authorization", "Bearer " + token)
                .syncRequest(false) //异步请求
                .onMainThread(true) //回到主线程
                .execute(new SimpleCallBack<List<NewsInfo>>() {
                    @Override
                    public void onSuccess(List<NewsInfo> response) throws Throwable {
                        if (response != null && response.size() > 0) {
                            for (NewsInfo item : response) {
                                NewInfo newInfo = new NewInfo();
                                newInfo.setTag(item.getNewsTag());
                                newInfo.setTitle(item.getNewsTitle());
                                newInfo.setSummary(item.getNewsSummary());
                                newInfo.setDetailUrl(item.getNewsDetailurl());
                                newInfo.setImageUrl(item.getNewsImageurl());
                                list.add(newInfo);
                            }
                            Log.e("YourClass", "getDemoNewInfos success, response: " + response.toString());
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        e.printStackTrace();
                        Log.e("YourClass", "getDemoNewInfos error: " + e.getMessage());
                    }
                });
        return list;
    }
    public static List<AdapterItem> getGridItems(Context context) {
        return getGridItems(context, R.array.grid_titles_entry, R.array.grid_icons_entry);
    }


    private static List<AdapterItem> getGridItems(Context context, int titleArrayId, int iconArrayId) {
        List<AdapterItem> list = new ArrayList<>();
        String[] titles = ResUtils.getStringArray(titleArrayId);
        Drawable[] icons = ResUtils.getDrawableArray(context, iconArrayId);
        for (int i = 0; i < titles.length; i++) {
            list.add(new AdapterItem(titles[i], icons[i]));
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
    @MemoryCache
    public static List<NewInfo> getEmptyNewInfo() {
        List<NewInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new NewInfo());
        }
        return list;
    }


}
