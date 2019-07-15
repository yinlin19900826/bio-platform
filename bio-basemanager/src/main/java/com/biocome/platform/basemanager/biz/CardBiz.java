package com.biocome.platform.basemanager.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.biocome.platform.basemanager.constant.CardStatusEnum;
import com.biocome.platform.basemanager.entity.Card;
import com.biocome.platform.basemanager.entity.Device;
import com.biocome.platform.basemanager.mapper.CardMapper;
import com.biocome.platform.basemanager.vo.resp.card.CardInfoResp;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.github.pagehelper.PageHelper;
import org.apache.tomcat.util.buf.UriUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: hxy
 * @create:2019/5/7 15:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CardBiz extends BaseBiz<CardMapper, Card> {
    private Logger log = LoggerFactory.getLogger(CardBiz.class);

    @Autowired
    private DeviceBiz deviceBiz;

    /**
     * 列表查询
     *
     * @param cardno
     * @param cardType
     * @param cardStatus
     * @param id
     * @return
     * @throws Exception
     */
    public List<CardInfoResp> selectByAdditionForCardList(String cardno, String cardType, String cardStatus, Integer id) throws Exception {
        return mapper.selectByAdditionForCardList(cardno, cardType, cardStatus, id);
    }

    /**
     * 批量删卡
     *
     * @param card
     * @return
     * @throws Exception
     */
    public int deleteCard(List<Integer> card) throws Exception {
        return mapper.deleteCard(card);
    }
}
