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

package com.sdcet.cuppacorner.adapter.entity;

public class NewsInfo {
    private int id;
    private String newsTag;
    private String newsTitle;
    private String newsSummary;
    private String newsImageurl;
    private String newsDetailurl;
// 省略 getter 和 setter 方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsTag() {
        return newsTag;
    }

    public void setNewsTag(String newsTag) {
        this.newsTag = newsTag;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSummary() {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }

    public String getNewsImageurl() {
        return newsImageurl;
    }

    public void setNewsImageurl(String newsImageurl) {
        this.newsImageurl = newsImageurl;
    }

    public String getNewsDetailurl() {
        return newsDetailurl;
    }

    public void setNewsDetailurl(String newsDetailurl) {
        this.newsDetailurl = newsDetailurl;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "id=" + id +
                ", newsTag='" + newsTag + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsSummary='" + newsSummary + '\'' +
                ", newsImageurl='" + newsImageurl + '\'' +
                ", newsDetailurl='" + newsDetailurl + '\'' +
                '}';
    }
}