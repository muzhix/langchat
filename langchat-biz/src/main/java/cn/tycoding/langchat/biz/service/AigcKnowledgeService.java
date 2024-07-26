/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcDocsSlice;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @since 2024/4/15
 */
public interface AigcKnowledgeService extends IService<AigcKnowledge> {

    /**
     * 添加文档数据
     */
    void addDocs(AigcDocs data);

    void updateDocs(AigcDocs data);

    /**
     * 在指定文档中添加Embedding后的切片数据
     */
    void addDocsSlice(AigcDocsSlice data);

    void updateDocsSlice(AigcDocsSlice data);
}
