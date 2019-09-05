package com.biocome.platform.common.tree;

import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.common.vo.UINodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UITreeUtil {
    private static Logger log = LoggerFactory.getLogger(UITreeUtil.class);

    /**
     * 从叶子到根节点创建树
     * @param list
     * @return
     */
    public static UITree buildTreeFromLeaf(List<UINodeVo> list) {
        //1.将层级跟节点列表映射
        Map<Integer, List<UINodeVo>> levelMap = new HashMap<Integer, List<UINodeVo>>();
        List<Integer> levels = new ArrayList<Integer>();
        for(UINodeVo entity: list){
            Integer level = entity.getLevel();
            if(ValidateUtils.isEmpty(level) || level.intValue() < 0){
                log.info("错误的层级节点，节点id："+entity.getId()+" , 层级 : "+level);
                continue;
            }
            List<UINodeVo> levelList = levelMap.get(level.intValue());
            if(ValidateUtils.isEmpty(levelList)){
                levelList = new ArrayList<UINodeVo>();
                levelMap.put(level.intValue(), levelList);
            }
            levelList.add(entity);
            if(!levels.contains(level.intValue())){
                levels.add(level.intValue());
            }
        }
        //2.层级排序
        Collections.sort(levels, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.intValue() < o2.intValue() ? -1 : o1.intValue() == o2.intValue() ? 0 : 1;
            }
        });
        //3.根据数据自下而上创建树
        UITree tree = new UITree();
        Map<String, List<UINode>> currentNodeMap = new HashMap<String,  List<UINode>>();
        Map<String, List<UINode>> bottomNodeMap = new HashMap<String,  List<UINode>>();
        for(int i = levels.size() - 1 ; i >= 0 ; i++){
            int lvl = levels.get(i);
            List<UINodeVo> lst = levelMap.get(lvl);
            if(i == 0){
                if(lst.size() != 1){
                    log.info("错误的根节点数，根节点数量"+lst.size() +" , 层级 : "+lvl);
                    break;
                }
                UINodeVo entity = lst.get(0);
                UINode root = new UINode(entity.getName(), entity.getId(), entity.getLevel());
                root.setChildList(bottomNodeMap.get(entity.getId()));
                tree.setRoot(root);
            }else{
                for(UINodeVo entity : lst){
                    String id = entity.getId();
                    int level = entity.getLevel();
                    String name = entity.getName();
                    String parentId = entity.getParentId();
                    UINode node =new UINode(name, id, level);
                    node.setAttach(entity.getAttach());
                    node.setType(entity.getType());
                    if(ValidateUtils.isNotEmpty(entity.getParentId())){
                        node.setParentId(entity.getParentId());
                    }
                    //设置子节点列表
                    if(ValidateUtils.isNotEmpty(bottomNodeMap)){
                        List<UINode> childList = bottomNodeMap.get(id);
                        if(ValidateUtils.isNotEmpty(childList)){
                            node.setChildList(childList);
                        }
                    }
                    List<UINode> nodeList = currentNodeMap.get(parentId);
                    if(ValidateUtils.isEmpty(nodeList)){
                        nodeList = new ArrayList<UINode>();
                        currentNodeMap.put(parentId, nodeList);
                    }
                    nodeList.add(node);
                }
            }
            //bottomNodeMap,并将当前currentNodeMap设置为bottomNodeMap
            copyMap(bottomNodeMap, currentNodeMap);
        }
        return tree;
    }

    /**
     * 数据拷贝
     * @param targetMap
     * @param srcMap
     */
    private static void copyMap(Map<String, List<UINode>> targetMap, Map<String, List<UINode>> srcMap) {
        targetMap.clear();
        for(Iterator<String> it = srcMap.keySet().iterator(); it.hasNext();){
            String key = it.next();
            targetMap.put(key, srcMap.get(key));
        }
        srcMap.clear();
    }

    /**
     * 从根节点创建树
     * @return
     */
    public static UITree buildTreeFromRoot(List<UINodeVo> list){
        UITree tree = new UITree();
        if(ValidateUtils.isNotEmpty(list)){
            //将列表改成应映射结构
            Map<String, List<UINode>> map = new HashMap<String, List<UINode>>();
            List<UINode> lst = new ArrayList<>();
            for(UINodeVo vo : list){
                UINode node = new UINode();
                node.setId(vo.getId());
                node.setName(vo.getName());
                node.setAttach(vo.getAttach());
                node.setType(vo.getType());
                if(ValidateUtils.isNotEmpty(vo.getParentId())){
                    node.setParentId(vo.getParentId());
                }
                String parentId = vo.getParentId();
                if(ValidateUtils.isNotEmpty(parentId)){
                    List<UINode> nodeList = map.get(parentId);
                    if(ValidateUtils.isEmpty(nodeList)){
                        nodeList = new ArrayList<UINode>();
                        map.put(parentId, nodeList);
                    }
                    nodeList.add(node);
                }else{
                    lst.add(node);
                }
            }
            if(lst.size() != 1){
                log.info("错误的根节点数，根节点数量："+lst.size());
            }
            int level = 1;
            UINode root = lst.get(0);
            setChildList(root, level, map);
            tree.setRoot(root);
        }
        return tree;
    }

    /**
     * 迭代设置子节点
     * @param parent
     * @param level
     * @param map
     */
    public static void setChildList(UINode parent, int level, Map<String, List<UINode>> map) {
        parent.setLevel(level);
        List<UINode> childList = map.get(parent.getId());
        if(ValidateUtils.isNotEmpty(childList)){
            if(ValidateUtils.isEmpty(parent.getChildList())){
                parent.setChildList(new ArrayList<UINode>());
            }
            parent.getChildList().addAll(childList);
            if(ValidateUtils.isNotEmpty(childList)){
                for(UINode node : childList){
                    setChildList(node, level+1, map);
                }
            }
        }
    }

    /**
     * 从根节点开始遍历树上挂接子节点
     * @param parent
     * @param level
     * @param map
     */
    public static void addNodes(UINode parent, int level, Map<String, List<UINode>> map) {
        if(map.size() == 0){
            return;
        }
        List<UINode> list = parent.getChildList();
        if(ValidateUtils.isNotEmpty(list)){
            for(UINode node : list){
                String nodeId = node.getId();
                List<UINode> addNodes = map.get(nodeId);
                if(ValidateUtils.isNotEmpty(addNodes)){
                    List<UINode> childList = node.getChildList();
                    if(ValidateUtils.isEmpty(childList)){
                        childList = new ArrayList<UINode>();
                        node.setChildList(childList);
                    }
                    childList.addAll(addNodes);
                    map.remove(nodeId);
                }
            }
            for(UINode node : list){
                addNodes(node, level+1, map);
            }
        }
        /*parent.setLevel(level);
        List<UINode> childList = map.get(parent.getId());
        if(ValidateUtils.isEmpty(childList)){
            List<UINode> nodes = parent.getChildList();
            if(ValidateUtils.isEmpty(nodes)){
                return;
            }
            for(UINode node : nodes){
                addNodes(node, level+1, map);
            }
        }else {
            for(UINode node : childList){
                addNodes(node, level+1, map);
            }
            if(ValidateUtils.isEmpty(parent.getChildList())){
                parent.setChildList(new ArrayList<UINode>());
            }
            parent.getChildList().addAll(childList);
            //parent.setChildList(childList);
        }*/
    }

    public static Map<String, List<UINode>> list2Map(UITree tree, List<UINodeVo> list) {
        Map<String, List<UINode>> map = new HashMap<String, List<UINode>>();
        for (int i = 0 ; i < list.size() ; i++){
            UINodeVo vo = list.get(i);
            UINode node = new UINode();
            node.setId(String.valueOf(vo.getId()));
            node.setName(vo.getName());
            node.setAttach(vo.getAttach());
            node.setType(vo.getType());
            if(ValidateUtils.isNotEmpty(vo.getParentId())){
                node.setParentId(vo.getParentId());
            }
            String parentId = vo.getParentId();
            //如果parentId为空，则默认为根节点的子节点
            if(ValidateUtils.isEmpty(parentId)){
                parentId = tree.getRoot().getId();
            }
            List<UINode> nodeList = map.get(parentId);
            if(ValidateUtils.isEmpty(nodeList)){
                nodeList = new ArrayList<UINode>();
                map.put(parentId,  nodeList);
            }
            nodeList.add(node);
        }
        return map;
    }
}
