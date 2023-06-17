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


/**
 * 音乐剧对象 musical
 * 
 * @author zhen
 * @date 2023-06-11
 */
public class Musical
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long musicalId;

    /** 音乐剧名称 */
    private String musicalName;

    /** 音乐剧图片地址 */
    private String musicalImageurl;

    /** 音乐剧详情地址 */
    private String musicalDetailurl;

    private int Comment;
    /**
     * 阅读量
     */
    private int Read;

    private int Praise;

    public int getComment() {
        return Comment;
    }

    public void setComment(int comment) {
        Comment = comment;
    }

    public int getRead() {
        return Read;
    }

    public void setRead(int read) {
        Read = read;
    }

    public int getPraise() {
        return Praise;
    }

    public void setPraise(int praise) {
        Praise = praise;
    }

    public void setMusicalId(Long musicalId)
    {
        this.musicalId = musicalId;
    }

    public Long getMusicalId() 
    {
        return musicalId;
    }
    public void setMusicalName(String musicalName) 
    {
        this.musicalName = musicalName;
    }

    public String getMusicalName() 
    {
        return musicalName;
    }
    public void setMusicalImageurl(String musicalImageurl) 
    {
        this.musicalImageurl = musicalImageurl;
    }

    public String getMusicalImageurl() 
    {
        return musicalImageurl;
    }
    public void setMusicalDetailurl(String musicalDetailurl) 
    {
        this.musicalDetailurl = musicalDetailurl;
    }

    public String getMusicalDetailurl() 
    {
        return musicalDetailurl;
    }

    @Override
    public String toString() {
        return "Musical{" +
                "musicalId=" + musicalId +
                ", musicalName='" + musicalName + '\'' +
                ", musicalImageurl='" + musicalImageurl + '\'' +
                ", musicalDetailurl='" + musicalDetailurl + '\'' +
                ", Comment=" + Comment +
                ", Read=" + Read +
                ", Praise=" + Praise +
                '}';
    }
}
