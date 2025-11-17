package cc.flyflow.biz.strategy.node.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
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
@Slf4j
public class RootNodeStrategy implements NodeStrategy, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(RootNodeStrategy.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        afterPropertiesSet(NodeTypeEnum.ROOT.getValue());
    }

    @Override
    public void handleNodeShow(Map<String, Object> paramMap, String nodeId, List<NodeFormatUserVo> nodeFormatUserVoList,
                               String processInstanceId, Node node, NodeShowVo nodeVo, List<String> selectUserNodeIdList) {
        //发起节点
        if (StrUtil.isBlank(processInstanceId)) {
            NodeFormatUserVo nodeFormatUserVo = buildUser(StpUtil.getLoginIdAsString());

            nodeFormatUserVoList.addAll(CollUtil.newArrayList(nodeFormatUserVo));

        } else {


            buildApproveDesc(node, processInstanceId, nodeVo, nodeFormatUserVoList);

        }
    }

    @Override
    public void buildProcessNodeList(cc.flyflow.core.dto.vo.UserTaskVO userTask, List<cc.flyflow.biz.dto.req.NodeUserReqDTO> nodeUserList) {
        // 根节点处理逻辑
        log.info("RootNodeStrategy buildProcessNodeList start, userTask:{}, nodeUserList:{}", userTask, nodeUserList);
        // TODO: 实现根节点的具体逻辑
        log.info("RootNodeStrategy buildProcessNodeList end");
    }
}
