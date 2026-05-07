/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50731 (5.7.31)
 Source Host           : localhost:3306
 Source Schema         : abb_back

 Target Server Type    : MySQL
 Target Server Version : 50731 (5.7.31)
 File Encoding         : 65001

 Date: 28/12/2025 15:16:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `fqc_display` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `fqc_daily_input` bigint DEFAULT NULL COMMENT '每天下线开关柜数量',
                               `fqc_daily_output` bigint DEFAULT NULL COMMENT '每天挂证开关柜数量',
                               `fqc_quantity_instock` bigint DEFAULT NULL COMMENT '历史库存开关柜数量',
                               `fqc_withstand_undistributed` bigint DEFAULT NULL COMMENT '下线待分配数量',
                               `fqc_function_undistributed` bigint DEFAULT NULL COMMENT '功能完成待挂证数量',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint DEFAULT NULL COMMENT '0：未删除， 1 ：已删除',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='开关柜数量展示';

-- ----------------------------
-- Table structure for fqc_cabinet_detail
-- ----------------------------
DROP TABLE IF EXISTS `fqc_cabinet_detail`;
CREATE TABLE `fqc_cabinet_detail` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                      `sn_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开关柜SN号',
                                      `sn_h_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '高压柜SN号',
                                      `h_ncr_discription` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '高压柜NCR描述',
                                      `sn_l_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '低压柜SN号',
                                      `l_ncr_discription` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '低压柜NCR描述',
                                      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除 1-已删除',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      KEY `idx_sn_code` (`sn_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='高低压柜明细';

-- ----------------------------
-- Table structure for fqc_employee_task
-- ----------------------------
DROP TABLE IF EXISTS `fqc_employee_task`;
CREATE TABLE `fqc_employee_task`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `emp_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工ID',
  `emp_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工姓名',
  `task_type` tinyint NOT NULL COMMENT '任务类型:1-耐压,2-功能',
  `sn_code1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分配SN1号',
  `sn_code2` varchar(64) DEFAULT NULL COMMENT '分配SN2号',
  `sn_code3` varchar(64) DEFAULT NULL COMMENT '分配SN3号',
  `sn_code4` varchar(64) DEFAULT NULL COMMENT '分配SN4号',
  `sn_code5` varchar(64) DEFAULT NULL COMMENT '分配SN5号',
  `sn_code6` varchar(64) DEFAULT NULL COMMENT '分配SN6号',
  `sn_code7` varchar(64) DEFAULT NULL COMMENT '分配SN7号',
  `sn_code8` varchar(64) DEFAULT NULL COMMENT '分配SN8号',
  `sn_code9` varchar(64) DEFAULT NULL COMMENT '分配SN9号',
  `sn_code10` varchar(64) DEFAULT NULL COMMENT '分配SN10号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_emp_task` (`emp_id`,`task_type`) USING BTREE COMMENT '员工+类型+序号唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工任务分配表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fqc_schedule_record
-- ----------------------------
DROP TABLE IF EXISTS `fqc_schedule_record`;
CREATE TABLE `fqc_schedule_record`  (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `sn_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SN号',
    `operate_type` tinyint NOT NULL COMMENT '操作类型 1-保存耐压员工 2-保存功能员工 3-状态更新',
    `before_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改前值',
    `after_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改后值',
    `operate_user` bigint NULL DEFAULT NULL COMMENT '操作人ID',
    `operate_user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '操作人姓名',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sn_code`(`sn_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '排程操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for fqc_switch_cabinet
-- ----------------------------
DROP TABLE IF EXISTS `fqc_switch_cabinet`;
CREATE TABLE `fqc_switch_cabinet`  (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `sn_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开关柜SN号',
   `production_line` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '产线',
   `offline_time` datetime DEFAULT NULL COMMENT '下线时间',
   `function_starttime` datetime DEFAULT NULL COMMENT '功能检测开始时间',
   `function_endtime` datetime DEFAULT NULL COMMENT '功能检测结束时间',
   `function_emp_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '功能员工姓名',
   `area` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '检测区域',
   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `is_deleted` tinyint DEFAULT '0' COMMENT '0-未删除 1-已删除',
   PRIMARY KEY (`id`) USING BTREE,
   UNIQUE KEY `uk_sn_code` (`sn_code`) USING BTREE COMMENT 'SN唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '开关柜主表' ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for ai_command_record
-- ----------------------------
DROP TABLE IF EXISTS `ai_command_record`;
CREATE TABLE `ai_command_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `original_command` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '原始命令',
  `provider` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'AI供应商(qwen/openai/deepseek/gemini等)',
  `model` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'AI模型(qwen-plus/qwen-max/gpt-4-turbo等)',
  `parse_success` tinyint(1) NULL DEFAULT NULL COMMENT '解析是否成功(0-失败, 1-成功)',
  `function_calls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '解析出的函数调用列表(JSON)',
  `explanation` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'AI的理解说明',
  `confidence` decimal(3, 2) NULL DEFAULT NULL COMMENT '置信度(0.00-1.00)',
  `parse_error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '解析错误信息',
  `input_tokens` int(11) NULL DEFAULT NULL COMMENT '输入Token数量',
  `output_tokens` int(11) NULL DEFAULT NULL COMMENT '输出Token数量',
  `total_tokens` int(11) NULL DEFAULT NULL COMMENT '总Token数量',
  `parse_time` bigint(20) NULL DEFAULT NULL COMMENT '解析耗时(毫秒)',
  `function_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行的函数名称',
  `function_arguments` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '函数参数(JSON)',
  `execute_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行状态(pending-待执行, success-成功, failed-失败)',
  `execute_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行结果(JSON)',
  `execute_error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行错误信息',
  `affected_rows` int(11) NULL DEFAULT NULL COMMENT '影响的记录数',
  `is_dangerous` tinyint(1) NULL DEFAULT 0 COMMENT '是否危险操作(0-否, 1-是)',
  `requires_confirmation` tinyint(1) NULL DEFAULT 0 COMMENT '是否需要确认(0-否, 1-是)',
  `user_confirmed` tinyint(1) NULL DEFAULT NULL COMMENT '用户是否确认(0-否, 1-是)',
  `idempotency_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '幂等性令牌(防止重复执行)',
  `execution_time` bigint(20) NULL DEFAULT NULL COMMENT '执行耗时(毫秒)',
  `ip_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户代理',
  `current_route` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前页面路由',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_idempotency_key`(`idempotency_key`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE,
  INDEX `idx_provider`(`provider`) USING BTREE,
  INDEX `idx_model`(`model`) USING BTREE,
  INDEX `idx_parse_success`(`parse_success`) USING BTREE,
  INDEX `idx_execute_status`(`execute_status`) USING BTREE,
  INDEX `idx_is_dangerous`(`is_dangerous`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'AI命令记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ai_command_record
-- ----------------------------

-- ----------------------------
-- Table structure for gen_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_config`;
CREATE TABLE `gen_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名',
  `module_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块名',
  `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '包名',
  `business_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务名',
  `entity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实体类名',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `parent_menu_id` bigint(20) NULL DEFAULT NULL COMMENT '上级菜单ID，对应sys_menu的id ',
  `remove_table_prefix` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '要移除的表前缀，如: sys_',
  `page_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面类型(classic|curd)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tablename`(`table_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成基础配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_config
-- ----------------------------

-- ----------------------------
-- Table structure for gen_field_config
-- ----------------------------
DROP TABLE IF EXISTS `gen_field_config`;
CREATE TABLE `gen_field_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_id` bigint(20) NOT NULL COMMENT '关联的配置ID',
  `column_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `column_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `column_length` int(11) NULL DEFAULT NULL,
  `field_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `field_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段类型',
  `field_sort` int(11) NULL DEFAULT NULL COMMENT '字段排序',
  `field_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字段描述',
  `max_length` int(11) NULL DEFAULT NULL,
  `is_required` tinyint(1) NULL DEFAULT NULL COMMENT '是否必填',
  `is_show_in_list` tinyint(1) NULL DEFAULT 0 COMMENT '是否在列表显示',
  `is_show_in_form` tinyint(1) NULL DEFAULT 0 COMMENT '是否在表单显示',
  `is_show_in_query` tinyint(1) NULL DEFAULT 0 COMMENT '是否在查询条件显示',
  `query_type` tinyint(4) NULL DEFAULT NULL COMMENT '查询方式',
  `form_type` tinyint(4) NULL DEFAULT NULL COMMENT '表单类型',
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `config_id`(`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成字段配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_field_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '配置名称',
  `config_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '配置key',
  `config_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '配置值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '系统限流QPS', 'IP_QPS_THRESHOLD_LIMIT', '10', '单个IP请求的最大每秒查询数（QPS）阈值Key', '2025-12-27 10:36:48', 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编号',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父节点id',
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父节点id路径',
  `sort` smallint(6) NULL DEFAULT 0 COMMENT '显示顺序',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态(1-正常 0-禁用)',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_code`(`code`) USING BTREE COMMENT '部门编号唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, '有来技术', 'YOULAI', 0, '0', 1, 1, 1, NULL, 1, '2025-12-27 10:36:47', 0);
INSERT INTO `sys_dept` VALUES (2, '研发部门', 'RD001', 1, '0,1', 1, 1, 2, NULL, 2, '2025-12-27 10:36:47', 0);
INSERT INTO `sys_dept` VALUES (3, '测试部门', 'QA001', 1, '0,1', 1, 1, 2, NULL, 2, '2025-12-27 10:36:47', 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态(0:正常;1:禁用)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除(1-删除，0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, 'gender', '性别', 1, NULL, '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1, 0);
INSERT INTO `sys_dict` VALUES (2, 'notice_type', '通知类型', 1, NULL, '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1, 0);
INSERT INTO `sys_dict` VALUES (3, 'notice_level', '通知级别', 1, NULL, '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1, 0);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联字典编码，与sys_dict表中的dict_code对应',
  `value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典项值',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典项标签',
  `tag_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签类型，用于前端样式展示（如success、warning等）',
  `status` tinyint(4) NULL DEFAULT 0 COMMENT '状态（1-正常，0-禁用）',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 'gender', '1', '男', 'primary', 1, 1, NULL, '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (2, 'gender', '2', '女', 'danger', 1, 2, NULL, '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (3, 'gender', '0', '保密', 'info', 1, 3, NULL, '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (4, 'notice_type', '1', '系统升级', 'success', 1, 1, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (5, 'notice_type', '2', '系统维护', 'primary', 1, 2, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (6, 'notice_type', '3', '安全警告', 'danger', 1, 3, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (7, 'notice_type', '4', '假期通知', 'success', 1, 4, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (8, 'notice_type', '5', '公司新闻', 'primary', 1, 5, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (9, 'notice_type', '99', '其他', 'info', 1, 99, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (10, 'notice_level', 'L', '低', 'info', 1, 1, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (11, 'notice_level', 'M', '中', 'warning', 1, 2, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);
INSERT INTO `sys_dict_item` VALUES (12, 'notice_level', 'H', '高', 'danger', 1, 3, '', '2025-12-27 10:36:47', 1, '2025-12-27 10:36:47', 1);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志模块',
  `request_method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数(批量请求参数可能会超过text)',
  `response_content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回参数',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志内容',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `ip` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `province` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `execution_time` bigint(20) NULL DEFAULT NULL COMMENT '执行时间(ms)',
  `browser` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `browser_version` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器版本',
  `os` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '终端系统',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '逻辑删除标识(1-已删除 0-未删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'LOGIN', 'POST', 'admin 123456', NULL, '登录', '/abb/api/v1/auth/login', 'login', '192.168.0.103', '0', '内网IP', 1058, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', NULL, '2025-12-27 11:44:24', 0);
INSERT INTO `sys_log` VALUES (2, 'USER', 'GET', '', NULL, '获取当前登录用户信息', '/abb/api/v1/users/me', 'getCurrentUser', '192.168.0.103', '0', '内网IP', 26, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 11:44:24', 0);
INSERT INTO `sys_log` VALUES (3, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 64, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 11:44:47', 0);
INSERT INTO `sys_log` VALUES (4, 'MENU', 'GET', '{}', NULL, '菜单列表', '/abb/api/v1/menus', 'getMenus', '192.168.0.103', '0', '内网IP', 73, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:49', 0);
INSERT INTO `sys_log` VALUES (5, 'USER', 'GET', '{\"deptId\":2,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 74, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:51', 0);
INSERT INTO `sys_log` VALUES (6, 'USER', 'GET', '{\"deptId\":3,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 30, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:52', 0);
INSERT INTO `sys_log` VALUES (7, 'USER', 'GET', '{\"deptId\":2,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 31, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:53', 0);
INSERT INTO `sys_log` VALUES (8, 'USER', 'GET', '{\"deptId\":1,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 35, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:54', 0);
INSERT INTO `sys_log` VALUES (9, 'USER', 'GET', '{\"deptId\":2,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 42, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:55', 0);
INSERT INTO `sys_log` VALUES (10, 'USER', 'GET', '{\"deptId\":1,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 43, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:56', 0);
INSERT INTO `sys_log` VALUES (11, 'USER', 'GET', '{\"deptId\":2,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 40, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:56', 0);
INSERT INTO `sys_log` VALUES (12, 'USER', 'GET', '{\"deptId\":2,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 39, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:57', 0);
INSERT INTO `sys_log` VALUES (13, 'USER', 'GET', '{\"deptId\":3,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 31, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:00:58', 0);
INSERT INTO `sys_log` VALUES (14, 'ROLE', 'GET', '{\"pageNum\":1,\"pageSize\":10}', NULL, '角色分页列表', '/abb/api/v1/roles/page', 'getRolePage', '192.168.0.103', '0', '内网IP', 28, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:01:05', 0);
INSERT INTO `sys_log` VALUES (15, 'USER', 'GET', '{\"deptId\":3,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 47, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:02:22', 0);
INSERT INTO `sys_log` VALUES (16, 'USER', 'GET', '{\"deptId\":2,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 36, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:02:22', 0);
INSERT INTO `sys_log` VALUES (17, 'USER', 'GET', '{\"deptId\":1,\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 35, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:02:24', 0);
INSERT INTO `sys_log` VALUES (18, 'USER', 'GET', '{}', NULL, '用户表单数据', '/abb/api/v1/users/2/form', 'getUserForm', '192.168.0.103', '0', '内网IP', 12, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:02:28', 0);
INSERT INTO `sys_log` VALUES (19, 'USER', 'PUT', '{} {\"id\":2,\"username\":\"admin\",\"nickname\":\"admin name\",\"mobile\":\"18812345678\",\"gender\":1,\"avatar\":\"https://foruda.gitee.com/images/1723603502796844527/03cdca2a_716974.gif\",\"email\":\"youlaitech@163.com\",\"status\":1,\"deptId\":1,\"roleIds\":[2]}', NULL, '修改用户', '/abb/api/v1/users/2', 'updateUser', '192.168.0.103', '0', '内网IP', 63, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:02:48', 0);
INSERT INTO `sys_log` VALUES (20, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 38, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:02:48', 0);
INSERT INTO `sys_log` VALUES (21, 'USER', 'GET', '{}', NULL, '用户表单数据', '/abb/api/v1/users/2/form', 'getUserForm', '192.168.0.103', '0', '内网IP', 7, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:02:54', 0);
INSERT INTO `sys_log` VALUES (22, 'DICT', 'GET', '{\"pageNum\":1,\"pageSize\":10}', NULL, '字典分页列表', '/abb/api/v1/dicts/page', 'getDictPage', '192.168.0.103', '0', '内网IP', 15, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:05:11', 0);
INSERT INTO `sys_log` VALUES (23, 'SETTING', 'GET', '{\"keywords\":\"\",\"pageNum\":1,\"pageSize\":10}', NULL, '系统配置分页列表', '/abb/api/v1/config/page', 'page', '192.168.0.103', '0', '内网IP', 26, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:05:16', 0);
INSERT INTO `sys_log` VALUES (24, 'DEPT', 'GET', '{}', NULL, '部门列表', '/abb/api/v1/dept', 'getDeptList', '192.168.0.103', '0', '内网IP', 14, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:05:25', 0);
INSERT INTO `sys_log` VALUES (25, 'OTHER', 'GET', '{\"excludeTables\":[\"gen_config\",\"gen_field_config\"],\"pageNum\":1,\"pageSize\":10}', NULL, '代码生成分页列表', '/abb/api/v1/codegen/table/page', 'getTablePage', '192.168.0.103', '0', '内网IP', 327, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:14:28', 0);
INSERT INTO `sys_log` VALUES (26, 'OTHER', 'GET', '{\"excludeTables\":[\"gen_config\",\"gen_field_config\"],\"pageNum\":1,\"pageSize\":10}', NULL, '代码生成分页列表', '/abb/api/v1/codegen/table/page', 'getTablePage', '192.168.0.103', '0', '内网IP', 32, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:14:45', 0);
INSERT INTO `sys_log` VALUES (27, 'ROLE', 'GET', '{\"pageNum\":1,\"pageSize\":10}', NULL, '角色分页列表', '/abb/api/v1/roles/page', 'getRolePage', '192.168.0.103', '0', '内网IP', 25, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:15:11', 0);
INSERT INTO `sys_log` VALUES (28, 'SETTING', 'GET', '{\"keywords\":\"\",\"pageNum\":1,\"pageSize\":10}', NULL, '系统配置分页列表', '/abb/api/v1/config/page', 'page', '192.168.0.103', '0', '内网IP', 16, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:22:55', 0);
INSERT INTO `sys_log` VALUES (29, 'SETTING', 'GET', '{\"keywords\":\"\",\"pageNum\":1,\"pageSize\":10}', NULL, '系统配置分页列表', '/abb/api/v1/config/page', 'page', '192.168.0.103', '0', '内网IP', 22, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:24:09', 0);
INSERT INTO `sys_log` VALUES (30, 'SETTING', 'GET', '{\"keywords\":\"\",\"pageNum\":1,\"pageSize\":10}', NULL, '系统配置分页列表', '/abb/api/v1/config/page', 'page', '192.168.0.103', '0', '内网IP', 15, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 13:27:45', 0);
INSERT INTO `sys_log` VALUES (31, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 7600, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 15:03:49', 0);
INSERT INTO `sys_log` VALUES (32, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 33, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 15:06:18', 0);
INSERT INTO `sys_log` VALUES (33, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 31, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 15:06:20', 0);
INSERT INTO `sys_log` VALUES (34, 'LOGIN', 'DELETE', '{}', NULL, '退出登录', '/abb/api/v1/auth/logout', 'logout', '192.168.0.103', '0', '内网IP', 3, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 15:06:27', 0);
INSERT INTO `sys_log` VALUES (35, 'LOGIN', 'POST', 'admin 123456', NULL, '登录', '/abb/api/v1/auth/login', 'login', '192.168.0.103', '0', '内网IP', 182, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', NULL, '2025-12-27 15:06:34', 0);
INSERT INTO `sys_log` VALUES (36, 'USER', 'GET', '', NULL, '获取当前登录用户信息', '/abb/api/v1/users/me', 'getCurrentUser', '192.168.0.103', '0', '内网IP', 29, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 15:06:34', 0);
INSERT INTO `sys_log` VALUES (37, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 49, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-27 15:06:35', 0);
INSERT INTO `sys_log` VALUES (38, 'LOGIN', 'POST', 'admin 123456', NULL, '登录', '/abb/api/v1/auth/login', 'login', '192.168.0.103', '0', '内网IP', 689, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', NULL, '2025-12-28 12:10:09', 0);
INSERT INTO `sys_log` VALUES (39, 'USER', 'GET', '', NULL, '获取当前登录用户信息', '/abb/api/v1/users/me', 'getCurrentUser', '192.168.0.103', '0', '内网IP', 16, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-28 12:10:09', 0);
INSERT INTO `sys_log` VALUES (40, 'LOGIN', 'DELETE', '{}', NULL, '退出登录', '/abb/api/v1/auth/logout', 'logout', '192.168.0.103', '0', '内网IP', 0, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-28 12:10:39', 0);
INSERT INTO `sys_log` VALUES (41, 'LOGIN', 'POST', 'admin 123456', NULL, '登录', '/abb/api/v1/auth/login', 'login', '192.168.0.103', '0', '内网IP', 75, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', NULL, '2025-12-28 12:10:52', 0);
INSERT INTO `sys_log` VALUES (42, 'USER', 'GET', '', NULL, '获取当前登录用户信息', '/abb/api/v1/users/me', 'getCurrentUser', '192.168.0.103', '0', '内网IP', 5, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-28 12:10:52', 0);
INSERT INTO `sys_log` VALUES (43, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 54, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-28 12:18:38', 0);
INSERT INTO `sys_log` VALUES (44, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 30, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-28 12:21:34', 0);
INSERT INTO `sys_log` VALUES (45, 'USER', 'GET', '{\"isRoot\":false,\"pageNum\":1,\"pageSize\":10}', NULL, '用户分页列表', '/abb/api/v1/users/page', 'getUserPage', '192.168.0.103', '0', '内网IP', 37, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-28 12:22:08', 0);
INSERT INTO `sys_log` VALUES (46, 'MENU', 'GET', '{}', NULL, '菜单列表', '/abb/api/v1/menus', 'getMenus', '192.168.0.103', '0', '内网IP', 104, 'Chrome', '142.0.0.0', 'Windows 10 or Windows Server 2016', 2, '2025-12-28 15:11:52', 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父菜单ID',
  `tree_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点ID路径',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `type` tinyint(4) NOT NULL COMMENT '菜单类型（1-菜单 2-目录 3-外链 4-按钮）',
  `route_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称（Vue Router 中用于命名路由）',
  `route_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由路径（Vue Router 中定义的 URL 路径）',
  `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径（组件页面完整路径，相对于 src/views/，缺省后缀 .vue）',
  `perm` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '【按钮】权限标识',
  `always_show` tinyint(4) NULL DEFAULT 0 COMMENT '【目录】只有一个子路由是否始终显示（1-是 0-否）',
  `keep_alive` tinyint(4) NULL DEFAULT 0 COMMENT '【菜单】是否开启页面缓存（1-是 0-否）',
  `visible` tinyint(1) NULL DEFAULT 1 COMMENT '显示状态（1-显示 0-隐藏）',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `redirect` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '0', '系统管理', 2, '', '/system', 'Layout', NULL, NULL, NULL, 1, 1, 'system', '/system/user', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '0,1', '用户管理', 1, 'User', 'user', 'system/user/index', NULL, NULL, 1, 1, 1, 'el-icon-User', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, '0,1', '角色管理', 1, 'Role', 'role', 'system/role/index', NULL, NULL, 1, 1, 2, 'role', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (4, 1, '0,1', '菜单管理', 1, 'SysMenu', 'menu', 'system/menu/index', NULL, NULL, 1, 1, 3, 'menu', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (5, 1, '0,1', '部门管理', 1, 'Dept', 'dept', 'system/dept/index', NULL, NULL, 1, 1, 4, 'tree', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (6, 1, '0,1', '字典管理', 1, 'Dict', 'dict', 'system/dict/index', NULL, NULL, 1, 1, 5, 'dict', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (20, 0, '0', '多级菜单', 2, NULL, '/multi-level', 'Layout', NULL, 1, NULL, 1, 9, 'cascader', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (21, 20, '0,20', '菜单一级', 2, NULL, 'multi-level1', 'Layout', NULL, 1, NULL, 1, 1, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (22, 21, '0,20,21', '菜单二级', 2, NULL, 'multi-level2', 'Layout', NULL, 0, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (23, 22, '0,20,21,22', '菜单三级-1', 1, NULL, 'multi-level3-1', 'demo/multi-level/children/children/level3-1', NULL, 0, 1, 1, 1, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (24, 22, '0,20,21,22', '菜单三级-2', 1, NULL, 'multi-level3-2', 'demo/multi-level/children/children/level3-2', NULL, 0, 1, 1, 2, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (26, 0, '0', '平台文档', 2, '', '/doc', 'Layout', NULL, NULL, NULL, 1, 8, 'document', 'https://juejin.cn/post/7228990409909108793', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (30, 26, '0,26', '平台文档(外链)', 3, NULL, 'https://juejin.cn/post/7228990409909108793', '', NULL, NULL, NULL, 1, 2, 'document', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (31, 2, '0,1,2', '用户新增', 4, NULL, '', NULL, 'sys:user:add', NULL, NULL, 1, 1, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (32, 2, '0,1,2', '用户编辑', 4, NULL, '', NULL, 'sys:user:edit', NULL, NULL, 1, 2, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (33, 2, '0,1,2', '用户删除', 4, NULL, '', NULL, 'sys:user:delete', NULL, NULL, 1, 3, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (36, 0, '0', '组件封装', 2, NULL, '/component', 'Layout', NULL, NULL, NULL, 1, 10, 'menu', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (37, 36, '0,36', '富文本编辑器', 1, 'WangEditor', 'wang-editor', 'demo/wang-editor', NULL, NULL, 1, 1, 2, '', '', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (38, 36, '0,36', '图片上传', 1, 'Upload', 'upload', 'demo/upload', NULL, NULL, 1, 1, 3, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (39, 36, '0,36', '图标选择器', 1, 'IconSelect', 'icon-select', 'demo/icon-selector', NULL, NULL, 1, 1, 4, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (40, 0, '0', '接口文档', 2, NULL, '/api', 'Layout', NULL, 1, NULL, 1, 7, 'api', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (41, 40, '0,40', 'Apifox', 1, 'Apifox', 'apifox', 'demo/api/apifox', NULL, NULL, 1, 1, 1, 'api', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (70, 3, '0,1,3', '角色新增', 4, NULL, '', NULL, 'sys:role:add', NULL, NULL, 1, 2, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (71, 3, '0,1,3', '角色编辑', 4, NULL, '', NULL, 'sys:role:edit', NULL, NULL, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (72, 3, '0,1,3', '角色删除', 4, NULL, '', NULL, 'sys:role:delete', NULL, NULL, 1, 4, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (73, 4, '0,1,4', '菜单新增', 4, NULL, '', NULL, 'sys:menu:add', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (74, 4, '0,1,4', '菜单编辑', 4, NULL, '', NULL, 'sys:menu:edit', NULL, NULL, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (75, 4, '0,1,4', '菜单删除', 4, NULL, '', NULL, 'sys:menu:delete', NULL, NULL, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (76, 5, '0,1,5', '部门新增', 4, NULL, '', NULL, 'sys:dept:add', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (77, 5, '0,1,5', '部门编辑', 4, NULL, '', NULL, 'sys:dept:edit', NULL, NULL, 1, 2, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (78, 5, '0,1,5', '部门删除', 4, NULL, '', NULL, 'sys:dept:delete', NULL, NULL, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (79, 6, '0,1,6', '字典新增', 4, NULL, '', NULL, 'sys:dict:add', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (81, 6, '0,1,6', '字典编辑', 4, NULL, '', NULL, 'sys:dict:edit', NULL, NULL, 1, 2, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (84, 6, '0,1,6', '字典删除', 4, NULL, '', NULL, 'sys:dict:delete', NULL, NULL, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (88, 2, '0,1,2', '重置密码', 4, NULL, '', NULL, 'sys:user:reset-password', NULL, NULL, 1, 4, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (89, 0, '0', '功能演示', 2, NULL, '/function', 'Layout', NULL, NULL, NULL, 1, 12, 'menu', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (90, 89, '0,89', 'Websocket', 1, 'WebSocket', '/function/websocket', 'demo/websocket', NULL, NULL, 1, 1, 3, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (95, 36, '0,36', '字典组件', 1, 'DictDemo', 'dict-demo', 'demo/dictionary', NULL, NULL, 1, 1, 4, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (97, 89, '0,89', 'Icons', 1, 'IconDemo', 'icon-demo', 'demo/icons', NULL, NULL, 1, 1, 2, 'el-icon-Notification', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (102, 26, '0,26', 'document', 3, NULL, 'internal-doc', 'demo/internal-doc', NULL, NULL, NULL, 1, 1, 'document', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (105, 2, '0,1,2', '用户查询', 4, NULL, '', NULL, 'sys:user:query', 0, 0, 1, 0, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (106, 2, '0,1,2', '用户导入', 4, NULL, '', NULL, 'sys:user:import', NULL, NULL, 1, 5, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (107, 2, '0,1,2', '用户导出', 4, NULL, '', NULL, 'sys:user:export', NULL, NULL, 1, 6, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (108, 36, '0,36', '增删改查', 1, 'Curd', 'curd', 'demo/curd/index', NULL, NULL, 1, 1, 0, '', '', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (109, 36, '0,36', '列表选择器', 1, 'TableSelect', 'table-select', 'demo/table-select/index', NULL, NULL, 1, 1, 1, '', '', NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (110, 0, '0', '路由参数', 2, NULL, '/route-param', 'Layout', NULL, 1, 1, 1, 11, 'el-icon-ElementPlus', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (111, 110, '0,110', '参数(type=1)', 1, 'RouteParamType1', 'route-param-type1', 'demo/route-param', NULL, 0, 1, 1, 1, 'el-icon-Star', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', '{\"type\": \"1\"}');
INSERT INTO `sys_menu` VALUES (112, 110, '0,110', '参数(type=2)', 1, 'RouteParamType2', 'route-param-type2', 'demo/route-param', NULL, 0, 1, 1, 2, 'el-icon-StarFilled', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', '{\"type\": \"2\"}');
INSERT INTO `sys_menu` VALUES (117, 1, '0,1', '系统日志', 1, 'Log', 'log', 'system/log/index', NULL, 0, 1, 1, 6, 'document', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (118, 0, '0', '系统工具', 2, NULL, '/codegen', 'Layout', NULL, 0, 1, 1, 2, 'menu', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (119, 118, '0,118', '代码生成', 1, 'Codegen', 'codegen', 'codegen/index', NULL, 0, 1, 1, 1, 'code', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (120, 1, '0,1', '系统配置', 1, 'Config', 'config', 'system/config/index', NULL, 0, 1, 1, 7, 'setting', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (121, 120, '0,1,120', '系统配置查询', 4, NULL, '', NULL, 'sys:config:query', 0, 1, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (122, 120, '0,1,120', '系统配置新增', 4, NULL, '', NULL, 'sys:config:add', 0, 1, 1, 2, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (123, 120, '0,1,120', '系统配置修改', 4, NULL, '', NULL, 'sys:config:update', 0, 1, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (124, 120, '0,1,120', '系统配置删除', 4, NULL, '', NULL, 'sys:config:delete', 0, 1, 1, 4, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (125, 120, '0,1,120', '系统配置刷新', 4, NULL, '', NULL, 'sys:config:refresh', 0, 1, 1, 5, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (126, 1, '0,1', '通知公告', 1, 'Notice', 'notice', 'system/notice/index', NULL, NULL, NULL, 1, 9, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (127, 126, '0,1,126', '通知查询', 4, NULL, '', NULL, 'sys:notice:query', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (128, 126, '0,1,126', '通知新增', 4, NULL, '', NULL, 'sys:notice:add', NULL, NULL, 1, 2, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (129, 126, '0,1,126', '通知编辑', 4, NULL, '', NULL, 'sys:notice:edit', NULL, NULL, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (130, 126, '0,1,126', '通知删除', 4, NULL, '', NULL, 'sys:notice:delete', NULL, NULL, 1, 4, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (133, 126, '0,1,126', '通知发布', 4, NULL, '', NULL, 'sys:notice:publish', 0, 1, 1, 5, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (134, 126, '0,1,126', '通知撤回', 4, NULL, '', NULL, 'sys:notice:revoke', 0, 1, 1, 6, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (135, 1, '0,1', '字典项', 1, 'DictItem', 'dict-item', 'system/dict/dict-item', NULL, 0, 1, 0, 6, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (136, 135, '0,1,135', '字典项新增', 4, NULL, '', NULL, 'sys:dict-item:add', NULL, NULL, 1, 2, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (137, 135, '0,1,135', '字典项编辑', 4, NULL, '', NULL, 'sys:dict-item:edit', NULL, NULL, 1, 3, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (138, 135, '0,1,135', '字典项删除', 4, NULL, '', NULL, 'sys:dict-item:delete', NULL, NULL, 1, 4, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (139, 3, '0,1,3', '角色查询', 4, NULL, '', NULL, 'sys:role:query', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (140, 4, '0,1,4', '菜单查询', 4, NULL, '', NULL, 'sys:menu:query', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (141, 5, '0,1,5', '部门查询', 4, NULL, '', NULL, 'sys:dept:query', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (142, 6, '0,1,6', '字典查询', 4, NULL, '', NULL, 'sys:dict:query', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (143, 135, '0,1,135', '字典项查询', 4, NULL, '', NULL, 'sys:dict-item:query', NULL, NULL, 1, 1, '', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (144, 26, '0,26', '后端文档', 3, NULL, 'https://youlai.blog.csdn.net/article/details/145178880', '', NULL, NULL, NULL, 1, 3, 'document', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (145, 26, '0,26', '移动端文档', 3, NULL, 'https://youlai.blog.csdn.net/article/details/143222890', '', NULL, NULL, NULL, 1, 4, 'document', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (146, 36, '0,36', '拖拽组件', 1, 'Drag', 'drag', 'demo/drag', NULL, NULL, NULL, 1, 5, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (147, 36, '0,36', '滚动文本', 1, 'TextScroll', 'text-scroll', 'demo/text-scroll', NULL, NULL, NULL, 1, 6, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (148, 89, '0,89', '字典实时同步', 1, 'DictSync', 'dict-sync', 'demo/dict-sync', NULL, NULL, NULL, 1, 3, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (149, 89, '0,89', 'VxeTable', 1, 'VxeTable', 'vxe-table', 'demo/vxe-table/index', NULL, NULL, 1, 1, 0, 'el-icon-MagicStick', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (150, 36, '0,36', '自适应表格操作列', 1, 'AutoOperationColumn', 'operation-column', 'demo/auto-operation-column', NULL, NULL, 1, 1, 1, '', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (151, 89, '0,89', 'CURD单文件', 1, 'CurdSingle', 'curd-single', 'demo/curd-single', NULL, NULL, 1, 1, 7, 'el-icon-Reading', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (152, 0, '0', 'AI助手', 2, NULL, '/platform', 'Layout', NULL, NULL, NULL, 1, 13, 'platform', '', '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);
INSERT INTO `sys_menu` VALUES (153, 152, '0,152', 'AI命令记录', 1, 'AiCommandRecord', 'command-record', 'ai/command-record/index', NULL, NULL, 1, 1, 1, 'document', NULL, '2025-12-27 10:36:47', '2025-12-27 10:36:47', NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通知标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '通知内容',
  `type` tinyint(4) NOT NULL COMMENT '通知类型（关联字典编码：notice_type）',
  `level` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '通知等级（字典code：notice_level）',
  `target_type` tinyint(4) NOT NULL COMMENT '目标类型（1: 全体, 2: 指定）',
  `target_user_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标人ID集合（多个使用英文逗号,分割）',
  `publisher_id` bigint(20) NULL DEFAULT NULL COMMENT '发布人ID',
  `publish_status` tinyint(4) NULL DEFAULT 0 COMMENT '发布状态（0: 未发布, 1: 已发布, -1: 已撤回）',
  `publish_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `revoke_time` datetime NULL DEFAULT NULL COMMENT '撤回时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（0: 未删除, 1: 已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, 'v2.12.0 新增系统日志，访问趋势统计功能。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 1, 'L', 1, '2', 1, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 1, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (2, 'v2.13.0 新增菜单搜索。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 1, 'L', 1, '2', 1, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 1, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (3, 'v2.14.0 新增个人中心。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 1, 'L', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (4, 'v2.15.0 登录页面改造。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 1, 'L', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (5, 'v2.16.0 通知公告、字典翻译组件。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 1, 'L', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (6, '系统将于本周六凌晨 2 点进行维护，预计维护时间为 2 小时。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 2, 'H', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (7, '最近发现一些钓鱼邮件，请大家提高警惕，不要点击陌生链接。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 3, 'L', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (8, '国庆假期从 10 月 1 日至 10 月 7 日放假，共 7 天。', '<p>1. 消息通知</p><p>2. 字典重构</p><p>3. 代码生成</p>', 4, 'L', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (9, '公司将在 10 月 15 日举办新产品发布会，敬请期待。', '公司将在 10 月 15 日举办新产品发布会，敬请期待。', 5, 'H', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);
INSERT INTO `sys_notice` VALUES (10, 'v2.16.1 版本发布。', 'v2.16.1 版本修复了 WebSocket 重复连接导致的后台线程阻塞问题，优化了通知公告。', 1, 'M', 1, '2', 2, 1, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 2, '2025-12-27 10:36:48', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `sort` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '角色状态(1-正常 0-停用)',
  `data_scope` tinyint(4) NULL DEFAULT NULL COMMENT '数据权限(1-所有数据 2-部门及子部门数据 3-本部门数据 4-本人数据)',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人 ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE COMMENT '角色名称唯一索引',
  UNIQUE INDEX `uk_code`(`code`) USING BTREE COMMENT '角色编码唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'ROOT', 1, 1, 1, NULL, '2025-12-27 10:36:47', NULL, '2025-12-27 10:36:47', 0);
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'ADMIN', 2, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (3, '访问游客', 'GUEST', 3, 1, 3, NULL, '2025-12-27 10:36:47', NULL, '2025-12-27 10:36:47', 0);
INSERT INTO `sys_role` VALUES (4, '系统管理员1', 'ADMIN1', 4, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (5, '系统管理员2', 'ADMIN2', 5, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (6, '系统管理员3', 'ADMIN3', 6, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (7, '系统管理员4', 'ADMIN4', 7, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (8, '系统管理员5', 'ADMIN5', 8, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (9, '系统管理员6', 'ADMIN6', 9, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (10, '系统管理员7', 'ADMIN7', 10, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (11, '系统管理员8', 'ADMIN8', 11, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (12, '系统管理员9', 'ADMIN9', 12, 1, 1, NULL, '2025-12-27 10:36:47', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  UNIQUE INDEX `uk_roleid_menuid`(`role_id`, `menu_id`) USING BTREE COMMENT '角色菜单唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 6);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 24);
INSERT INTO `sys_role_menu` VALUES (2, 26);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 31);
INSERT INTO `sys_role_menu` VALUES (2, 32);
INSERT INTO `sys_role_menu` VALUES (2, 33);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (2, 37);
INSERT INTO `sys_role_menu` VALUES (2, 38);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 40);
INSERT INTO `sys_role_menu` VALUES (2, 41);
INSERT INTO `sys_role_menu` VALUES (2, 70);
INSERT INTO `sys_role_menu` VALUES (2, 71);
INSERT INTO `sys_role_menu` VALUES (2, 72);
INSERT INTO `sys_role_menu` VALUES (2, 73);
INSERT INTO `sys_role_menu` VALUES (2, 74);
INSERT INTO `sys_role_menu` VALUES (2, 75);
INSERT INTO `sys_role_menu` VALUES (2, 76);
INSERT INTO `sys_role_menu` VALUES (2, 77);
INSERT INTO `sys_role_menu` VALUES (2, 78);
INSERT INTO `sys_role_menu` VALUES (2, 79);
INSERT INTO `sys_role_menu` VALUES (2, 81);
INSERT INTO `sys_role_menu` VALUES (2, 84);
INSERT INTO `sys_role_menu` VALUES (2, 85);
INSERT INTO `sys_role_menu` VALUES (2, 86);
INSERT INTO `sys_role_menu` VALUES (2, 87);
INSERT INTO `sys_role_menu` VALUES (2, 88);
INSERT INTO `sys_role_menu` VALUES (2, 89);
INSERT INTO `sys_role_menu` VALUES (2, 90);
INSERT INTO `sys_role_menu` VALUES (2, 91);
INSERT INTO `sys_role_menu` VALUES (2, 95);
INSERT INTO `sys_role_menu` VALUES (2, 97);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 117);
INSERT INTO `sys_role_menu` VALUES (2, 118);
INSERT INTO `sys_role_menu` VALUES (2, 119);
INSERT INTO `sys_role_menu` VALUES (2, 120);
INSERT INTO `sys_role_menu` VALUES (2, 121);
INSERT INTO `sys_role_menu` VALUES (2, 122);
INSERT INTO `sys_role_menu` VALUES (2, 123);
INSERT INTO `sys_role_menu` VALUES (2, 124);
INSERT INTO `sys_role_menu` VALUES (2, 125);
INSERT INTO `sys_role_menu` VALUES (2, 126);
INSERT INTO `sys_role_menu` VALUES (2, 127);
INSERT INTO `sys_role_menu` VALUES (2, 128);
INSERT INTO `sys_role_menu` VALUES (2, 129);
INSERT INTO `sys_role_menu` VALUES (2, 130);
INSERT INTO `sys_role_menu` VALUES (2, 131);
INSERT INTO `sys_role_menu` VALUES (2, 132);
INSERT INTO `sys_role_menu` VALUES (2, 133);
INSERT INTO `sys_role_menu` VALUES (2, 134);
INSERT INTO `sys_role_menu` VALUES (2, 135);
INSERT INTO `sys_role_menu` VALUES (2, 136);
INSERT INTO `sys_role_menu` VALUES (2, 137);
INSERT INTO `sys_role_menu` VALUES (2, 138);
INSERT INTO `sys_role_menu` VALUES (2, 139);
INSERT INTO `sys_role_menu` VALUES (2, 140);
INSERT INTO `sys_role_menu` VALUES (2, 141);
INSERT INTO `sys_role_menu` VALUES (2, 142);
INSERT INTO `sys_role_menu` VALUES (2, 143);
INSERT INTO `sys_role_menu` VALUES (2, 144);
INSERT INTO `sys_role_menu` VALUES (2, 145);
INSERT INTO `sys_role_menu` VALUES (2, 146);
INSERT INTO `sys_role_menu` VALUES (2, 147);
INSERT INTO `sys_role_menu` VALUES (2, 148);
INSERT INTO `sys_role_menu` VALUES (2, 149);
INSERT INTO `sys_role_menu` VALUES (2, 150);
INSERT INTO `sys_role_menu` VALUES (2, 151);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(1) NULL DEFAULT 1 COMMENT '性别((1-男 2-女 0-保密)',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态(1-正常 0-禁用)',
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除标识(0-未删除 1-已删除)',
  `openid` char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信 openid',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `login_name`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'root', '有来技术', 0, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', NULL, 'https://foruda.gitee.com/images/1723603502796844527/03cdca2a_716974.gif', '18812345677', 1, 'youlaitech@163.com', '2025-12-27 10:36:47', NULL, '2025-12-27 10:36:47', NULL, 0, NULL);
INSERT INTO `sys_user` VALUES (2, 'admin', 'admin name', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 1, 'https://foruda.gitee.com/images/1723603502796844527/03cdca2a_716974.gif', '18812345678', 1, 'youlaitech@163.com', '2025-12-27 10:36:47', NULL, '2025-12-27 13:02:48', 2, 0, NULL);
INSERT INTO `sys_user` VALUES (3, 'test', '测试小用户', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 3, 'https://foruda.gitee.com/images/1723603502796844527/03cdca2a_716974.gif', '18812345679', 1, 'youlaitech@163.com', '2025-12-27 10:36:47', NULL, '2025-12-27 10:36:47', NULL, 0, NULL);

-- ----------------------------
-- Table structure for sys_user_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_notice`;
CREATE TABLE `sys_user_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `notice_id` bigint(20) NOT NULL COMMENT '公共通知id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `is_read` bigint(20) NULL DEFAULT 0 COMMENT '读取状态（0: 未读, 1: 已读）',
  `read_time` datetime NULL DEFAULT NULL COMMENT '阅读时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '逻辑删除(0: 未删除, 1: 已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_notice
-- ----------------------------
INSERT INTO `sys_user_notice` VALUES (1, 1, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (2, 2, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (3, 3, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (4, 4, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (5, 5, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (6, 6, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (7, 7, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (8, 8, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (9, 9, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);
INSERT INTO `sys_user_notice` VALUES (10, 10, 2, 1, NULL, '2025-12-27 10:36:48', '2025-12-27 10:36:48', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3);

SET FOREIGN_KEY_CHECKS = 1;
