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

package com.sdcet.cuppacorner.adapter.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdcet.cuppacorner.adapter.entity.Food;
import com.sdcet.cuppacorner.utils.Utils;
import com.xuexiang.cuppacorner.R;
import com.xuexiang.xui.widget.imageview.ImageLoader;

import java.util.List;

public class ShequRecyclerAdapter extends RecyclerView.Adapter<ShequRecyclerAdapter.MyViewHolder> {
    private List<Food> foodList;

    public ShequRecyclerAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food_card_item, parent, false);
        final MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Food foodItem = foodList.get(position);

        // 加载图片
        ImageLoader.get().loadImage((ImageView) holder.sgl_image, foodItem.getImageUrl());

        // 设置 ImageView 的高度
        ViewGroup.LayoutParams layoutParams = holder.sgl_image.getLayoutParams();
        layoutParams.height = (int) (600 + Math.random() * 400);
        holder.sgl_image.setLayoutParams(layoutParams);

        // 设置标题、点赞数和详情页面链接
        holder.sgl_title.setText(foodItem.getName());
        holder.detailUrl = foodItem.getDetailUrl();
        holder.sql_praise.setText(foodItem.getPraise() == 0 ? "点赞" : String.valueOf(foodItem.getPraise()));

        // 设置点击事件
        holder.foodView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goWeb(holder.itemView.getContext(), holder.detailUrl);
            }
        });
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View foodView;
        ImageView sgl_image;
        TextView sgl_title;
        TextView sql_praise;
        String detailUrl;

        public MyViewHolder(View itemView) {
            super(itemView);
            foodView = itemView;
            sgl_image = itemView.findViewById(R.id.sgl_image);
            sgl_title = itemView.findViewById(R.id.sgl_title);
            sql_praise=itemView.findViewById(R.id.sql_praise);
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}