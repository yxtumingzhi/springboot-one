/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hope.one.mapper;


import com.hope.one.common.Metrics;
import com.hope.one.entity.BladeNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通知公告表 Mapper 接口
 *
 * @author tumingzhi
 * @since 2021-02-02
 */
public interface BladeNoticeMapper {

    /**
     * 查询
     *
     * @param id
     * @return
     */
    BladeNotice selectById(@Param("id") Long id);

    void update(@Param("id") Long id, @Param("title") String title);

    void insert(@Param("bladeNotice") BladeNotice bladeNotice);

    void insertBatch(@Param("list") List<BladeNotice> list);

    List<Metrics> metrics();

}
