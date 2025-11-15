# Auto-Flowless
Auto Flowless是一个以流程为基础面向自动化，AI化的智能体项目，力图在流程的思维框架下，来组建智能体的运行环境，从而实现自动化，AI化的智能体。目前项目仅仅只实现了基于flowable的流程的前后端，流程管理，系统管理，人员管理，组织管理，菜单权限管理等基本功能，当然还有很多需要优化和重构和扩展的地方。因此需要分阶段来一步步的完善。

## 项目结构
```
auto-flowless-1/
├── auto-flowless-backend/   # 后端项目
├── auto-flowless-frontend/  # 前端项目
├── stage-dev-1.md           # 第一阶段开发要求
├── stage-dev-2.md           # 第二阶段开发要求
├── start-backend.sh         # 后端启动脚本
├── start-frontend.sh        # 前端启动脚本
└── README.md                # 项目说明文档
```

## 快速开始
### 环境要求
- Node.js 20.10.0+ (前端)
- Java 8+ (后端)
- MySQL 5.7+ (数据库)
- Redis 5.0+ (缓存)

### 启动步骤
1. 初始化数据库
   ```bash
   # 执行后端项目中的sql文件
   mysql -u root -p auto_flowless < auto-flowless-backend/sql/xxx.sql
   ```

2. 启动后端服务
   ```bash
   chmod +x start-backend.sh
   ./start-backend.sh
   ```

3. 启动前端服务
   ```bash
   chmod +x start-frontend.sh
   ./start-frontend.sh
   ```

## 开发说明
项目采用分阶段开发模式，每个阶段的开发要求和任务请参考对应的stage-dev-x.md文件。

## 许可证
MIT

