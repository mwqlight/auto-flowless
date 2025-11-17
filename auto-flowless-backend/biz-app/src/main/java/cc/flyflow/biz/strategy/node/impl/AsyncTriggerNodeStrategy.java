package cc.flyflow.biz.strategy.node.impl;

import cc.flyflow.biz.dto.req.NodeUserReqDTO;
import cc.flyflow.biz.strategy.node.NodeStrategy;
import cc.flyflow.biz.strategy.node.NodeStrategyFactory;
import cc.flyflow.common.constants.NodeTypeEnum;

import cc.flyflow.core.dto.vo.UserTaskVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 异步触发器节点策略类
 */
@Component
public class AsyncTriggerNodeStrategy implements NodeStrategy {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AsyncTriggerNodeStrategy.class);

    @Override
    public void afterPropertiesSet(Integer type) {
        NodeStrategyFactory.register(type, this);
    }

    @Override
    public void buildProcessNodeList(UserTaskVO userTask, List<NodeUserReqDTO> nodeUserList) {
        // 异步触发器节点处理逻辑
        logger.info("AsyncTriggerNodeStrategy buildProcessNodeList start, userTask:{}, nodeUserList:{}", userTask, nodeUserList);
        // TODO: 实现异步触发器节点的具体逻辑
        logger.info("AsyncTriggerNodeStrategy buildProcessNodeList end");
    }

    @Override
    public void handleNodeShow(java.util.Map<String, Object> paramMap, String nodeId, java.util.List<cc.flyflow.biz.vo.node.NodeFormatUserVo> nodeFormatUserVoList, String processInstanceId, cc.flyflow.common.dto.flow.Node node, cc.flyflow.biz.vo.node.NodeShowVo nodeVo, java.util.List<String> selectUserNodeIdList) {
        // 异步触发器节点显示处理逻辑
        logger.info("AsyncTriggerNodeStrategy handleNodeShow start, nodeId:{}, processInstanceId:{}, node:{}", nodeId, processInstanceId, node);
        // TODO: 实现异步触发器节点的显示逻辑
        logger.info("AsyncTriggerNodeStrategy handleNodeShow end");
    }
}
