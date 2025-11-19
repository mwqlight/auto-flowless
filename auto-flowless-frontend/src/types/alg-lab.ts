/**
 * 算法分类
 */
export interface AlgCategory {
  id?: number;
  categoryName: string;
  categoryCode: string;
  parentId?: number;
  level: number;
  sort: number;
  status: number;
  remark?: string;
  children?: AlgCategory[];
  createdBy?: number;
  createdTime?: Date;
  updatedBy?: number;
  updatedTime?: Date;
}

/**
 * 算法
 */
export interface Algorithm {
  id?: number;
  algName: string;
  algCode: string;
  categoryId?: number;
  timeComplexity?: string;
  spaceComplexity?: string;
  description?: string;
  status: number;
  sort: number;
  createdBy?: number;
  createdTime?: Date;
  updatedBy?: number;
  updatedTime?: Date;
}

/**
 * 算法实现
 */
export interface AlgImplementation {
  id?: number;
  algId?: number;
  implName: string;
  implCode: string;
  codeContent: string;
  animationJson?: string;
  language: string;
  status: number;
  sort: number;
  createdBy?: number;
  createdTime?: Date;
  updatedBy?: number;
  updatedTime?: Date;
}
