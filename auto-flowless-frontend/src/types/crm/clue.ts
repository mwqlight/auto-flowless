/**
 * 线索信息类型定义
 * @author flyflow
 * @since 2024-05-20
 */
export interface CrmClue {
  /**
   * 主键ID
   */
  id?: string
  /**
   * 线索名称
   */
  clueName?: string
  /**
   * 线索来源
   */
  source?: string
  /**
   * 线索状态
   */
  status?: string
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
   * 是否为公海池线索
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
