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

package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcDocsSlice;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.biz.mapper.AigcDocsMapper;
import cn.tycoding.langchat.biz.mapper.AigcDocsSliceMapper;
import cn.tycoding.langchat.biz.mapper.AigcKnowledgeMapper;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AigcKnowledgeServiceImpl extends ServiceImpl<AigcKnowledgeMapper, AigcKnowledge> implements AigcKnowledgeService {

    private final AigcDocsMapper aigcDocsMapper;
    private final AigcDocsSliceMapper aigcDocsSliceMapper;

    @Override
    public void addDocs(AigcDocs data) {
        data.setCreateTime(new Date());
        aigcDocsMapper.insert(data);
    }

    @Override
    public void updateDocs(AigcDocs data) {
        aigcDocsMapper.updateById(data);
    }

    @Override
    public void addDocsSlice(AigcDocsSlice data) {
        data.setCreateTime(new Date())
                .setWordNum(data.getContent().length())
                .setStatus(true)
        ;
        aigcDocsSliceMapper.insert(data);
    }

    @Override
    public void updateDocsSlice(AigcDocsSlice data) {
        aigcDocsSliceMapper.updateById(data);
    }

    @Override
    public List<String> listSliceVectorIdsOfDoc(String docsId) {
        LambdaQueryWrapper<AigcDocsSlice> selectWrapper = Wrappers.<AigcDocsSlice>lambdaQuery()
                .select(AigcDocsSlice::getVectorId)
                .eq(AigcDocsSlice::getDocsId, docsId);
        List<String> vectorIds = aigcDocsSliceMapper.selectList(selectWrapper)
                .stream()
                .map(AigcDocsSlice::getVectorId)
                .toList();
        log.debug("slices of doc: [{}], count: [{}]", docsId, vectorIds.size());
        return vectorIds;
    }

    @Override
    public void removeSlicesOfDoc(String docsId) {
        LambdaQueryWrapper<AigcDocsSlice> deleteWrapper = Wrappers.<AigcDocsSlice>lambdaQuery()
                .eq(AigcDocsSlice::getDocsId, docsId);
        int count = aigcDocsSliceMapper.delete(deleteWrapper);
        log.debug("remove all slices of doc: [{}], count: [{}]", docsId, count);
    }
}

