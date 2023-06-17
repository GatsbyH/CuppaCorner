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
 * 小贴士对象 tip_info
 * 
 * @author zhen
 * @date 2023-06-12
 */
public class TipInfo
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 小贴士标题 */
    private String tipTitle;

    /** 小贴士内容 */

    private String tipContent;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTipTitle(String tipTitle) 
    {
        this.tipTitle = tipTitle;
    }

    public String getTipTitle() 
    {
        return tipTitle;
    }
    public void setTipContent(String tipContent) 
    {
        this.tipContent = tipContent;
    }

    public String getTipContent() 
    {
        return tipContent;
    }


    @Override
    public String toString() {
        return "TipInfo{" +
                "id=" + id +
                ", tipTitle='" + tipTitle + '\'' +
                ", tipContent='" + tipContent + '\'' +
                '}';
    }
}
