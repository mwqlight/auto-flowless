package cc.flyflow.biz.strategy.node.impl;

import cc.flyflow.biz.strategy.node.NodeStrategy;
import cc.flyflow.biz.vo.node.NodeFormatUserVo;
import cc.flyflow.biz.vo.node.NodeShowVo;
import cc.flyflow.common.constants.NodeTypeEnum;
import cc.flyflow.common.dto.flow.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Component
public class ApprovalNodeStrategy implements NodeStrategy, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(ApprovalNodeStrategy.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(NodeTypeEnum.APPROVAL.getValue());
    }

    @Override
    @Override
    public void handleNodeShow(Map<String, Object> paramMap, String nodeId, List<NodeFormatUserVo> nodeFormatUserVoList,
                               String processInstanceId, Node node, NodeShowVo nodeVo, List<String> selectUserNodeIdList) {
        handleUserNode(node, nodeVo, selectUserNodeIdList, paramMap, processInstanceId,
                NodeTypeEnum.APPROVAL.getValue(), nodeFormatUserVoList);
    }

    @Override
    public void buildProcessNodeList(cc.flyflow.core.dto.vo.UserTaskVO userTask, List<cc.flyflow.biz.dto.req.NodeUserReqDTO> nodeUserList) {
        // 审批节点处理逻辑
        log.info("ApprovalNodeStrategy buildProcessNodeList start, userTask:{}, nodeUserList:{}", userTask, nodeUserList);
        // TODO: 实现审批节点的具体逻辑
        log.info("ApprovalNodeStrategy buildProcessNodeList end");
    }
}
