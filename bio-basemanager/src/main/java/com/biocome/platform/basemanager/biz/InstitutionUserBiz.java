package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.entity.InstitutionUser;
import com.biocome.platform.basemanager.mapper.InstitutionUserMapper;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.tree.UINode;
import com.biocome.platform.common.tree.UITree;
import com.biocome.platform.common.tree.UITreeUtil;
import com.biocome.platform.common.vo.UINodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 机构用户
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 20:02:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class InstitutionUserBiz extends BaseBiz<InstitutionUserMapper, InstitutionUser> {
    @Autowired
    DictDetailBiz dictDetailBiz;

    public UITree getUserTree() {
        UITree tree = dictDetailBiz.getInstitutionTree();
        List<UINodeVo> list = selectList();
        Map<String, List<UINode>> map = UITreeUtil.list2Map(tree,list);
        tree.addNodes(map);
        return tree;
    }


    public List<UINodeVo> selectList() {
        return mapper.selectList();
    }
}