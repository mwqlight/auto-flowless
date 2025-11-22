/**
 * 商机信息类型定义
 * @author flyflow
 * @since 2024-05-20
 */
export interface CrmOpportunity {
  /**
   * 主键ID
   */
  id?: string
  /**
   * 商机名称
   */
  opportunityName?: string
  /**
   * 关联线索ID
   */
  clueId?: string
  /**
   * 关联客户ID
   */
  customerId?: string
  /**
   * 商机阶段
   */
  stage?: string
  /**
   * 预计金额
   */
  estimatedAmount?: number
  /**
   * 预计成交时间
   */
  estimatedCloseTime?: string
  /**
   * 客户经理ID
   */
  accountManagerId?: string
  /**
   * 客户经理姓名
   */
  accountManagerName?: string
  /**
   * 最后跟进时间
   */
  lastFollowUpTime?: string
  /**
   * 是否为公海池商机
   */
  isPublicPool?: boolean
  /**
   * 创建时间
   */
  createTime?: string
  /**
   * 更新时间
   */
  updateTime?: string
}
