package cc.flyflow.biz.strategy.node.impl;

import cc.flyflow.biz.dto.req.NodeUserReqDTO;
import cc.flyflow.biz.strategy.node.NodeStrategy;
import cc.flyflow.biz.strategy.node.NodeStrategyFactory;
import cc.flyflow.common.constants.NodeTypeEnum;
import cc.flyflow.core.dto.vo.UserTaskVO;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import cc.flyflow.common.dto.flow.Node;
import cc.flyflow.biz.vo.node.NodeShowVo;
import cc.flyflow.biz.vo.node.NodeFormatUserVo;

/**
 * 路由节点策略类
 */
@Component
public class RouterNodeStrategy implements NodeStrategy {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RouterNodeStrategy.class);

    @Override
    public void afterPropertiesSet(Integer type) {
        NodeStrategyFactory.register(type, this);
    }

    @Override
    public void buildProcessNodeList(UserTaskVO userTask, List<NodeUserReqDTO> nodeUserList) {
        // 路由节点处理逻辑
        logger.info("RouterNodeStrategy buildProcessNodeList start, userTask:{}, nodeUserList:{}", JSON.toJSONString(userTask), JSON.toJSONString(nodeUserList));
        // TODO: 实现路由节点的具体逻辑
        logger.info("RouterNodeStrategy buildProcessNodeList end");
    }

    @Override
    public void handleNodeShow(Map<String, Object> paramMap, String nodeId, List<NodeFormatUserVo> nodeFormatUserVoList, String processInstanceId, Node node, NodeShowVo nodeVo, List<String> selectUserNodeIdList) {
        // 路由节点显示处理逻辑
        logger.info("RouterNodeStrategy handleNodeShow start, paramMap:{}, nodeId:{}, nodeFormatUserVoList:{}, processInstanceId:{}, node:{}, nodeVo:{}, selectUserNodeIdList:{}", JSON.toJSONString(paramMap), nodeId, JSON.toJSONString(nodeFormatUserVoList), processInstanceId, JSON.toJSONString(node), JSON.toJSONString(nodeVo), JSON.toJSONString(selectUserNodeIdList));
        // TODO: 实现路由节点的显示处理逻辑
        logger.info("RouterNodeStrategy handleNodeShow end");
    }
}
