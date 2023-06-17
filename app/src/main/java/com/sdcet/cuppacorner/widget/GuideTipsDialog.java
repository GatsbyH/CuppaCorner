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

package com.sdcet.cuppacorner.widget;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.sdcet.cuppacorner.adapter.entity.TipInfo;
import com.sdcet.cuppacorner.utils.TokenUtils;
import com.xuexiang.cuppacorner.R;
import com.sdcet.cuppacorner.utils.MMKVUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.XHttpSDK;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.dialog.BaseDialog;
import com.xuexiang.xutil.app.AppUtils;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

/**
 * 小贴士弹窗
 *
 * @author zhen
 * @since 2019-08-22 17:02
 */
public class GuideTipsDialog extends BaseDialog implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String KEY_IS_IGNORE_TIPS = "com.xuexiang.templateproject.widget.key_is_ignore_tips_";

    private List<com.sdcet.cuppacorner.core.http.entity.TipInfo> mTips;
    private int mIndex = -1;

    private TextView mTvPrevious;
    private TextView mTvNext;

    private TextView mTvTitle;
    private TextView mTvContent;

    /**
     * 显示提示
     *
     * @param context 上下文
     */
    public static void showTips(final Context context) {
        if (!isIgnoreTips()) {
            showTipsForce(context);
        }
    }

    /**
     * 强制显示提示
     *
     * @param context 上下文
     */
    public static void showTipsForce(Context context) {
//        CustomRequest request = XHttp.custom().cacheMode(CacheMode.FIRST_CACHE).cacheTime(TimeConstants.DAY).cacheKey("getTips");
//        request.apiCall(request.create(ApiService.IGetService.class).getTips(), new NoTipCallBack<List<com.xuexiang.cuppacorner.adapter.entity.TipInfo>>() {
//            @Override
//            public void onSuccess(List<com.xuexiang.cuppacorner.adapter.entity.TipInfo> response) throws Throwable {
//                if (response != null && response.size() > 0) {
//                    List<TipInfo> list = new ArrayList<>();
//                    for (com.xuexiang.cuppacorner.adapter.entity.TipInfo item : response) {
//                        TipInfo tipInfo = new TipInfo();
//                        tipInfo.setTitle(item.getTipTitle());
//                        tipInfo.setContent(item.getTipContent());
//                        list.add(tipInfo);
//                        Log.e("list",list.toString());
//                    }
//                    new GuideTipsDialog(context, list).show();
//                }
//            }
//        });
        XHttpSDK.setSuccessCode(200);
        String token = TokenUtils.getToken(); // 获取JWT令牌
        XHttp.get("/androidapi/loadTip")
                .headers("Authorization", "Bearer " + token)
                .syncRequest(false) //异步请求
                .onMainThread(true) //回到主线程
                .execute(new SimpleCallBack<List<TipInfo>>() {
                    @Override
                    public void onSuccess(List<TipInfo> response) throws Throwable {
                        if (response != null && response.size() > 0) {
                            List<com.sdcet.cuppacorner.core.http.entity.TipInfo> list = new ArrayList<>();
                            for (TipInfo item : response) {
                                com.sdcet.cuppacorner.core.http.entity.TipInfo tipInfo = new com.sdcet.cuppacorner.core.http.entity.TipInfo();
                                tipInfo.setTitle(item.getTipTitle());
                                tipInfo.setContent(item.getTipContent());
                                list.add(tipInfo);
                                Log.e("list",list.toString());
                            }
                            new GuideTipsDialog(context, list).show();
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        e.printStackTrace();
                        Log.e("YourClass", "getBannerList error: " + e.getMessage());
                    }
                });
    }

    public GuideTipsDialog(Context context, @NonNull List<com.sdcet.cuppacorner.core.http.entity.TipInfo> tips) {
        super(context, R.layout.dialog_guide_tips);
        initViews();
        updateTips(tips);
    }

    /**
     * 初始化弹窗
     */
    private void initViews() {
        mTvTitle = findViewById(R.id.tv_title);
        mTvContent = findViewById(R.id.tv_content);
        AppCompatCheckBox cbIgnore = findViewById(R.id.cb_ignore);
        ImageView ivClose = findViewById(R.id.iv_close);

        mTvPrevious = findViewById(R.id.tv_previous);
        mTvNext = findViewById(R.id.tv_next);

        if (cbIgnore != null) {
            cbIgnore.setChecked(isIgnoreTips());
            cbIgnore.setOnCheckedChangeListener(this);
        }
        if (ivClose != null) {
            ivClose.setOnClickListener(this);
        }
        mTvPrevious.setOnClickListener(this);
        mTvNext.setOnClickListener(this);
        mTvPrevious.setEnabled(false);
        mTvNext.setEnabled(true);
        setCancelable(false);
        setCanceledOnTouchOutside(true);
    }

    /**
     * 更新提示信息
     *
     * @param tips 提示信息
     */
    private void updateTips(List<com.sdcet.cuppacorner.core.http.entity.TipInfo> tips) {
        mTips = tips;
        if (mTips != null && mTips.size() > 0 && mTvContent != null) {
            mIndex = 0;
            showRichText(mTips.get(mIndex));
        }
    }

    /**
     * 切换提示信息
     *
     * @param index 索引
     */
    private void switchTipInfo(int index) {
        if (mTips != null && mTips.size() > 0 && mTvContent != null) {
            if (index >= 0 && index <= mTips.size() - 1) {
                showRichText(mTips.get(index));
                if (index == 0) {
                    mTvPrevious.setEnabled(false);
                    mTvNext.setEnabled(true);
                } else if (index == mTips.size() - 1) {
                    mTvPrevious.setEnabled(true);
                    mTvNext.setEnabled(false);
                } else {
                    mTvPrevious.setEnabled(true);
                    mTvNext.setEnabled(true);
                }
            }
        }
    }

    /**
     * 显示富文本
     *
     * @param tipInfo 提示信息
     */
    private void showRichText(com.sdcet.cuppacorner.core.http.entity.TipInfo tipInfo) {
        mTvTitle.setText(tipInfo.getTitle());
        RichText.fromHtml(tipInfo.getContent())
                .bind(this)
                .into(mTvContent);
    }


    @SingleClick(300)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_close) {
            dismiss();
        } else if (id == R.id.tv_previous) {
            if (mIndex > 0) {
                mIndex--;
                switchTipInfo(mIndex);
            }
        } else if (id == R.id.tv_next) {
            if (mIndex < mTips.size() - 1) {
                mIndex++;
                switchTipInfo(mIndex);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setIsIgnoreTips(isChecked);
    }

    @Override
    public void onDetachedFromWindow() {
        RichText.clear(this);
        super.onDetachedFromWindow();
    }


    public static boolean setIsIgnoreTips(boolean isIgnore) {
        return MMKVUtils.put(KEY_IS_IGNORE_TIPS + AppUtils.getAppVersionCode(), isIgnore);
    }

    public static boolean isIgnoreTips() {
        return MMKVUtils.getBoolean(KEY_IS_IGNORE_TIPS + AppUtils.getAppVersionCode(), false);
    }

}
