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

public class Food2 {
   private String name;
   private String imageUrl;
   private String detailUrl;

   public Food2(String name, String imageUrl, String detailUrl) {
      this.name = name;
      this.imageUrl = imageUrl;
      this.detailUrl = detailUrl;
   }

   public Food2() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public String getDetailUrl() {
      return detailUrl;
   }

   public void setDetailUrl() {
      this.detailUrl = detailUrl;
   }

   @Override
   public String toString() {
      return "Food2{" +
              "name='" + name + '\'' +
              ", imageUrl='" + imageUrl + '\'' +
              ", detailUrl='" + detailUrl + '\'' +
              '}';
   }
}
