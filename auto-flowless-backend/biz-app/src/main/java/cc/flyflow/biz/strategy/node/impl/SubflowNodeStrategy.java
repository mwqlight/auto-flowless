package cc.flyflow.biz.strategy.node.impl;

import cc.flyflow.biz.dto.req.NodeUserReqDTO;
import cc.flyflow.biz.strategy.node.NodeStrategy;
import cc.flyflow.biz.strategy.node.NodeStrategyFactory;
import cc.flyflow.common.constants.NodeTypeEnum;
import cc.flyflow.common.utils.LogUtil;
import cc.flyflow.core.dto.vo.UserTaskVO;
import cc.flyflow.biz.vo.node.NodeFormatUserVo;
import cc.flyflow.biz.vo.node.NodeShowVo;
import cc.flyflow.common.dto.flow.Node;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 子流程节点策略类
 */
@Component
public class SubflowNodeStrategy implements NodeStrategy {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SubflowNodeStrategy.class);

    @Override
    public void afterPropertiesSet(Integer type) {
        NodeStrategyFactory.register(type, this);
    }

    @Override
    public void handleNodeShow(Map<String, Object> paramMap, String nodeId, List<NodeFormatUserVo> nodeFormatUserVoList, String processInstanceId, Node node, NodeShowVo nodeVo, List<String> selectUserNodeIdList) {
        // 子流程节点处理逻辑
        logger.info("SubflowNodeStrategy handleNodeShow start, nodeId:{}, processInstanceId:{}, node:{}", nodeId, processInstanceId, node);
        // TODO: 实现子流程节点的具体逻辑
        logger.info("SubflowNodeStrategy handleNodeShow end");
    }

    @Override
    public void buildProcessNodeList(UserTaskVO userTask, List<NodeUserReqDTO> nodeUserList) {
        // 子流程节点处理逻辑
        logger.info("SubflowNodeStrategy buildProcessNodeList start, userTask:{}, nodeUserList:{}", userTask, nodeUserList);
        // TODO: 实现子流程节点的具体逻辑
        logger.info("SubflowNodeStrategy buildProcessNodeList end");
    }
}
