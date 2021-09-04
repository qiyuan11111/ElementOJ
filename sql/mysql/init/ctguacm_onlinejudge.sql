/*
Navicat MySQL Data Transfer

Source Server         : mydatabase
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : ctguacm_onlinejudge

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2021-01-26 19:02:42
*/
DROP USER IF EXISTS 'ctguacm'@'%';
CREATE user 'ctguacm'@'%' IDENTIFIED by 'ctguacm1234@';
ALTER user 'ctguacm'@'%' IDENTIFIED WITH mysql_native_password BY 'ctguacm1234@';

CREATE DATABASE IF NOT EXISTS `ctguacm_onlinejudge`
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

USE `ctguacm_onlinejudge`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ban_ip_list
-- ----------------------------
DROP TABLE IF EXISTS `ban_ip_list`;
CREATE TABLE `ban_ip_list` (
  `list_id` int NOT NULL AUTO_INCREMENT COMMENT '条目id',
  `ban_ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '封禁ip',
  `start_time` datetime NOT NULL COMMENT '起始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `admin_id` int NOT NULL COMMENT '操作员id',
  `admin_ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作员ip',
  `reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原因',
  PRIMARY KEY (`list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='ip黑名单表';

-- ----------------------------
-- Table structure for ban_list
-- ----------------------------
DROP TABLE IF EXISTS `ban_list`;
CREATE TABLE `ban_list` (
  `list_id` int NOT NULL AUTO_INCREMENT COMMENT '条目id',
  `banner_id` int NOT NULL COMMENT '封禁用户id',
  `start_time` datetime NOT NULL COMMENT '起始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `reason` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '原因',
  `admin_id` int NOT NULL COMMENT '操作员id',
  `admin_ip` varchar(46) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作员ip',
  PRIMARY KEY (`list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户黑名单表';

-- ----------------------------
-- Table structure for bug_report
-- ----------------------------
DROP TABLE IF EXISTS `bug_report`;
CREATE TABLE `bug_report` (
  `detail_id` int NOT NULL AUTO_INCREMENT COMMENT '明细id',
  `reporter` int NOT NULL COMMENT '汇报用户id',
  `content` text COLLATE utf8mb4_general_ci COMMENT '内容',
  `report_time` datetime NOT NULL COMMENT '汇报时间',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态（0-“未受理”，1-“已受理”）',
  `reply` text COLLATE utf8mb4_general_ci COMMENT '回复',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Bug反馈表';

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cate_id` int NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `top_cate_id` int NOT NULL COMMENT '上级分类编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类名称',
  `rank_num` int NOT NULL DEFAULT '1' COMMENT '目录层级',
  `child_problem_num` int NOT NULL DEFAULT '0' COMMENT '此分类题目数量',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`cate_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='分类表';

-- ----------------------------
-- Table structure for compile_information
-- ----------------------------
DROP TABLE IF EXISTS `compile_information`;
CREATE TABLE `compile_information` (
  `solution_id` int NOT NULL AUTO_INCREMENT COMMENT '运行信息id',
  `compile_infor` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '编译信息',
  PRIMARY KEY (`solution_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='编译信息表';

-- ----------------------------
-- Table structure for contest
-- ----------------------------
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest` (
  `contest_id` int NOT NULL AUTO_INCREMENT COMMENT '比赛id',
  `title` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '比赛标题',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `ban_time` datetime NOT NULL COMMENT '封榜时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `contest_description` text COLLATE utf8mb4_general_ci COMMENT '比赛描述',
  `language_limit` int NOT NULL COMMENT '语言限制',
  `is_used` int DEFAULT '1' COMMENT '是否显示（1-显示；0-不显示）',
  `contest_type` int DEFAULT '0' COMMENT '比赛赛制（0-ACM；1-OI；2-IOI）',
  `allow_p_or_t` int DEFAULT '0' COMMENT '比赛队伍/个人限制（0-个人；1-团体；2-个人/团体）',
  `submit_times_limit` int NOT NULL DEFAULT '-1' COMMENT '提交次数限制（-1-无限提交）',
  `is_public` int DEFAULT '1' COMMENT '是否公开（0-私密；1-公开）',
  `password` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '比赛密码',
  `is_inter` int DEFAULT '1' COMMENT '是否为内部比赛（0-内部；1-非内部）',
  `is_check_test` int DEFAULT '0' COMMENT '是否启用作弊检测（0-不启用；1-启用）',
  `score` float NOT NULL DEFAULT '100' COMMENT '比赛分数',
  `last_revise_time` datetime NOT NULL COMMENT '上次修改时间',
  `last_revise_user_id` int NOT NULL COMMENT '上次修改用户id',
  PRIMARY KEY (`contest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='比赛表';

-- ----------------------------
-- Table structure for contest_banner
-- ----------------------------
DROP TABLE IF EXISTS `contest_banner`;
CREATE TABLE `contest_banner` (
  `contest_id` int NOT NULL COMMENT '比赛id',
  `banner_id` int NOT NULL COMMENT '封禁用户id',
  `reason` text COLLATE utf8mb4_general_ci COMMENT '原因',
  `ban_time` datetime NOT NULL COMMENT '封禁时间',
  PRIMARY KEY (`contest_id`,`banner_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='比赛用户封禁表';

-- ----------------------------
-- Table structure for contest_enter
-- ----------------------------
DROP TABLE IF EXISTS `contest_enter`;
CREATE TABLE `contest_enter` (
  `contest_id` int NOT NULL COMMENT '比赛id',
  `user_id` int NOT NULL COMMENT '用户id',
  `enter_time` datetime NOT NULL COMMENT '报名时间',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '报名ip地址',
  PRIMARY KEY (`contest_id`,`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='比赛报名表';

-- ----------------------------
-- Table structure for contest_message
-- ----------------------------
DROP TABLE IF EXISTS `contest_message`;
CREATE TABLE `contest_message` (
  `msg_id` int NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `sender` int NOT NULL COMMENT '发送方（0-管理员）',
  `receiver` int NOT NULL COMMENT '接收方（0-管理员）',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `contest_id` int NOT NULL COMMENT '比赛id',
  `is_receive` int NOT NULL DEFAULT '0' COMMENT '是否收到（0-未收到，1-收到）',
  `content` text COLLATE utf8mb4_general_ci COMMENT '内容',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='比赛消息表';

-- ----------------------------
-- Table structure for contest_news
-- ----------------------------
DROP TABLE IF EXISTS `contest_news`;
CREATE TABLE `contest_news` (
  `news_id` int NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `releaser_id` int NOT NULL COMMENT '发布人id',
  `contest_id` int NOT NULL COMMENT '比赛id',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `content` text COLLATE utf8mb4_general_ci COMMENT '内容',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不显示）',
  `release_object` int NOT NULL DEFAULT '0' COMMENT '发布对象（0-全体）',
  PRIMARY KEY (`news_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='比赛公告表';

-- ----------------------------
-- Table structure for contest_problem
-- ----------------------------
DROP TABLE IF EXISTS `contest_problem`;
CREATE TABLE `contest_problem` (
  `contest_id` int NOT NULL COMMENT '比赛id',
  `rank_num` int NOT NULL COMMENT '比赛题目编号',
  `problem_id` int NOT NULL COMMENT '题目id',
  `problem_score` float NOT NULL DEFAULT '100' COMMENT '题目总分',
  `problem_score_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '题目测试数据分数分配',
  `can_privade_data` int NOT NULL DEFAULT '0' COMMENT '是否可提供数据（0-不提供；1-提供）',
  `can_show_in_time` int NOT NULL DEFAULT '0' COMMENT '是否实时显示判题情况（0-不显示；1-显示）',
  `last_revise_time` datetime NOT NULL COMMENT '上次修改时间',
  `last_revise_user_id` int NOT NULL COMMENT '上次修改用户id',
  `all_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT '提交次数',
  `ac_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'AC次数',
  `pe_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'PE次数',
  `wa_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'WA次数',
  `tle_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'TLE次数',
  `mle_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'MLE次数',
  `ole_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'OLE次数',
  `re_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'RE次数',
  PRIMARY KEY (`contest_id`,`problem_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='比赛题目表';

-- ----------------------------
-- Table structure for contest_problem_usage
-- ----------------------------
DROP TABLE IF EXISTS `contest_problem_usage`;
CREATE TABLE `contest_problem_usage` (
  `contest_id` int NOT NULL COMMENT '比赛id',
  `problem_id` int NOT NULL COMMENT '题目id',
  `all_submit_times` int NOT NULL DEFAULT '0' COMMENT '提交次数',
  `ac_submit_times` int NOT NULL DEFAULT '0' COMMENT 'AC次数',
  `pe_submit_times` int NOT NULL DEFAULT '0' COMMENT 'PE次数',
  `wa_submit_times` int NOT NULL DEFAULT '0' COMMENT 'WA次数',
  `tle_submit_times` int NOT NULL DEFAULT '0' COMMENT 'TLE次数',
  `mle_submit_times` int NOT NULL DEFAULT '0' COMMENT 'MLE次数',
  `ole_submit_times` int NOT NULL DEFAULT '0' COMMENT 'OLE次数',
  `re_submit_times` int NOT NULL DEFAULT '0' COMMENT 'RE次数',
  PRIMARY KEY (`contest_id`,`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='比赛题目提交表';

-- ----------------------------
-- Table structure for daily_problem
-- ----------------------------
DROP TABLE IF EXISTS `daily_problem`;
CREATE TABLE `daily_problem` (
  `user_id` int NOT NULL COMMENT '用户id',
  `problem_id` int NOT NULL COMMENT '题目id',
  `create_time` date NOT NULL COMMENT '创建时间',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  PRIMARY KEY (`user_id`,`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='每日推荐题目表';

-- ----------------------------
-- Table structure for honor
-- ----------------------------
DROP TABLE IF EXISTS `honor`;
CREATE TABLE `honor` (
  `honor_id` int NOT NULL AUTO_INCREMENT COMMENT '称号id',
  `honor_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '称号名称',
  `color` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '显示颜色',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  PRIMARY KEY (`honor_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='称号表';

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `log_id` int NOT NULL AUTO_INCREMENT COMMENT '日志明细',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `user_id` int NOT NULL DEFAULT '0' COMMENT '用户id',
  `status` int NOT NULL COMMENT '登录状态（0-密码错误，1-登录成功，2-验证码错误，3-账号被封禁，4-IP被封禁）',
  `log_time` datetime NOT NULL COMMENT '登录时间',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志';

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `news_id` int NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `publisher_id` int NOT NULL COMMENT '发布人id',
  `publisher_nick` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '发布人nick',
  `title` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `content` text COLLATE utf8mb4_general_ci COMMENT '内容',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  `importance` int NOT NULL DEFAULT '0' COMMENT '重要度（值越高越重要）',
  PRIMARY KEY (`news_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公告表';

-- ----------------------------
-- Records of news
-- ----------------------------

-- ----------------------------
-- Table structure for password_status
-- ----------------------------
DROP TABLE IF EXISTS `password_status`;
CREATE TABLE `password_status` (
  `action_id` int NOT NULL AUTO_INCREMENT COMMENT '操作记录id',
  `used_id` int NOT NULL COMMENT '用户id',
  `old_password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '原密码',
  `new_password` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '新密码',
  `action_time` datetime NOT NULL COMMENT '操作时间',
  `ip` varchar(46) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作ip',
  PRIMARY KEY (`action_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户密码记录表';

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `user_id` int NOT NULL COMMENT '用户id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
  `object` int DEFAULT NULL COMMENT '权限对象',
  `action` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限动作',
  `type` int NOT NULL COMMENT '权限类型（1-用户，2-权限）',
  `end_time` datetime NOT NULL COMMENT '截止时间',
  PRIMARY KEY (`user_id`,`name`,`end_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `problem_id` int NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `creator_nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者nick',
  `creator_id` int NOT NULL COMMENT '创建者用户id',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `problem_describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '题目描述',
  `input_describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '输入描述',
  `output_describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '输出描述',
  `is_spj` int NOT NULL DEFAULT '0' COMMENT '是否特判',
  `hint` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '提示',
  `category` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '题目分类',
  `source` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '题目来源',
  `type` int NOT NULL DEFAULT '0' COMMENT '题目类型（0-代码题；1-补全代码题；2-填空题）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `time_limit` int unsigned NOT NULL DEFAULT '1000' COMMENT '时间限制',
  `memory_limit` int unsigned NOT NULL DEFAULT '268435456' COMMENT '空间限制',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否显示',
  `score` float NOT NULL DEFAULT '100' COMMENT '题目分数',
  `can_privade_data` int NOT NULL DEFAULT '0' COMMENT '是否可提供数据',
  `can_show_in_time` int NOT NULL DEFAULT '0' COMMENT '是否实时显示判题情况',
  `last_revise_user_id` int NOT NULL COMMENT '上次修改用户id',
  `last_revise_time` datetime NOT NULL COMMENT '上次修改时间',
  PRIMARY KEY (`problem_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题目表';

-- ----------------------------
-- Table structure for problem_list
-- ----------------------------
DROP TABLE IF EXISTS `problem_list`;
CREATE TABLE `problem_list` (
  `list_id` int NOT NULL AUTO_INCREMENT COMMENT '题单id',
  `user_id` int NOT NULL COMMENT '创建用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `problem_num` int NOT NULL DEFAULT '0' COMMENT '题目数量',
  `is_public` int NOT NULL DEFAULT '1' COMMENT '是否公开（0-私密；1-公开）',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  PRIMARY KEY (`list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题单表';

-- ----------------------------
-- Table structure for problem_list_detail
-- ----------------------------
DROP TABLE IF EXISTS `problem_list_detail`;
CREATE TABLE `problem_list_detail` (
  `detail_id` int NOT NULL AUTO_INCREMENT COMMENT '明细id',
  `problem_id` int NOT NULL COMMENT '题目id',
  `create_time` datetime NOT NULL COMMENT '加入时间',
  `is_used` char(1) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  PRIMARY KEY (`detail_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题单明细表';

-- ----------------------------
-- Table structure for problem_usage
-- ----------------------------
DROP TABLE IF EXISTS `problem_usage`;
CREATE TABLE `problem_usage` (
  `problem_id` int NOT NULL COMMENT '题目id',
  `all_submit_times` int NOT NULL DEFAULT '0' COMMENT '提交次数',
  `ac_submit_times` int NOT NULL DEFAULT '0' COMMENT 'AC次数',
  `pe_submit_times` int NOT NULL DEFAULT '0' COMMENT 'PE次数',
  `wa_submit_times` int NOT NULL DEFAULT '0' COMMENT 'WA次数',
  `tle_submit_times` int NOT NULL DEFAULT '0' COMMENT 'TLE次数',
  `mle_submit_times` int NOT NULL DEFAULT '0' COMMENT 'MLE次数',
  `ole_submit_times` int NOT NULL DEFAULT '0' COMMENT 'OLE次数',
  `re_submit_times` int NOT NULL DEFAULT '0' COMMENT 'RE次数',
  PRIMARY KEY (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='题目提交情况表';

-- ----------------------------
-- Table structure for re_contest
-- ----------------------------
DROP TABLE IF EXISTS `re_contest`;
CREATE TABLE `re_contest` (
  `recontest_id` int NOT NULL AUTO_INCREMENT COMMENT '重现赛id',
  `contest_id` int NOT NULL COMMENT '比赛id',
  `creator_id` int NOT NULL COMMENT '创建者id',
  `ip` varchar(46) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建ip',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `ban_time` datetime NOT NULL COMMENT '封榜时间',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不显示）',
  PRIMARY KEY (`recontest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='重现赛表';

-- ----------------------------
-- Table structure for re_contest_application
-- ----------------------------
DROP TABLE IF EXISTS `re_contest_application`;
CREATE TABLE `re_contest_application` (
  `applica_id` int NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `recontest_id` int NOT NULL COMMENT '重现赛id',
  `user_id` int NOT NULL COMMENT '用户id',
  `allow_time` datetime NOT NULL COMMENT '创建时间',
  `applica_condition` int NOT NULL DEFAULT '0' COMMENT '状态（0-待确认；1-接受；2-拒绝；3-取消）',
  `process_time` datetime DEFAULT NULL COMMENT '处理时间',
  `create_ip` varchar(46) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建ip',
  `process_ip` varchar(46) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理ip',
  PRIMARY KEY (`applica_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='重现赛邀请表';

-- ----------------------------
-- Table structure for re_contest_user
-- ----------------------------
DROP TABLE IF EXISTS `re_contest_user`;
CREATE TABLE `re_contest_user` (
  `recontest_id` int NOT NULL COMMENT '重现赛id',
  `participant_id` int NOT NULL COMMENT '参与者id',
  `participant_time` datetime NOT NULL COMMENT '参与时间',
  `ip` varchar(46) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ip',
  PRIMARY KEY (`recontest_id`,`participant_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='重现赛参赛用户表';

-- ----------------------------
-- Table structure for solution
-- ----------------------------
DROP TABLE IF EXISTS `solution`;
CREATE TABLE `solution` (
  `solution_id` int NOT NULL AUTO_INCREMENT COMMENT '运行信息id',
  `team_id` int NOT NULL DEFAULT '-1' COMMENT '队伍id',
  `user_id` int NOT NULL DEFAULT '-1' COMMENT '用户id',
  `problem_id` int NOT NULL COMMENT '题目id',
  `p_or_t` int NOT NULL DEFAULT '0' COMMENT '个人或者队伍提交（0-个人；1-队伍）',
  `nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `time` int NOT NULL DEFAULT '-1' COMMENT '时间',
  `memory` int NOT NULL DEFAULT '-1' COMMENT '内存',
  `create_time` datetime NOT NULL COMMENT '加入时间',
  `result` int NOT NULL COMMENT '运行结果',
  `language` int NOT NULL COMMENT '语言',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ip',
  `contest_id` int NOT NULL DEFAULT '-1' COMMENT '比赛编号',
  `rank_num` int NOT NULL DEFAULT '-1' COMMENT '比赛题目编号',
  `code_length` int NOT NULL DEFAULT '0' COMMENT '代码长度',
  `score` float NOT NULL DEFAULT '0' COMMENT '得分',
  `pass_rate` float NOT NULL DEFAULT '0' COMMENT '通过率',
  PRIMARY KEY (`solution_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='提交信息表';

-- ----------------------------
-- Table structure for solution_code
-- ----------------------------
DROP TABLE IF EXISTS `solution_code`;
CREATE TABLE `solution_code` (
  `solution_id` int NOT NULL COMMENT '运行信息id',
  `code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '代码',
  PRIMARY KEY (`solution_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='运行代码表';

-- ----------------------------
-- Table structure for solution_testcase
-- ----------------------------
DROP TABLE IF EXISTS `solution_testcase`;
CREATE TABLE `solution_testcase` (
  `solution_id` int NOT NULL AUTO_INCREMENT COMMENT '运行信息id',
  `judge_information` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '判题数据信息',
  `wa_information` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'wa具体信息',
  PRIMARY KEY (`solution_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='判题测试数据表';

-- ----------------------------
-- Table structure for source
-- ----------------------------
DROP TABLE IF EXISTS `source`;
CREATE TABLE `source` (
  `source_id` int NOT NULL AUTO_INCREMENT COMMENT '来源编号',
  `top_source_id` int NOT NULL COMMENT '上级来源编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源名称',
  `rank_num` int NOT NULL DEFAULT '1' COMMENT '目录层级',
  `child_problem_num` int NOT NULL DEFAULT '0' COMMENT '此来源题目数量',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`source_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='来源表';

-- ----------------------------
-- Records of source
-- ----------------------------
INSERT INTO `source` VALUES ('1', '0', '你好', '1', '0', '2020-09-07 14:26:56');
INSERT INTO `source` VALUES ('2', '0', '2', '1', '0', '2020-09-07 14:27:08');
INSERT INTO `source` VALUES ('3', '1', '不和', '2', '0', '2020-09-07 14:27:25');
INSERT INTO `source` VALUES ('4', '1', '4', '2', '0', '2020-09-07 14:27:36');
INSERT INTO `source` VALUES ('5', '6', '88', '3', '0', '2020-09-07 14:27:57');
INSERT INTO `source` VALUES ('6', '2', '6', '2', '0', '2020-09-07 14:28:13');
INSERT INTO `source` VALUES ('7', '6', '7', '3', '0', '2020-09-07 14:28:24');
INSERT INTO `source` VALUES ('8', '6', '8', '3', '0', '2020-09-07 14:43:47');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `team_id` int NOT NULL AUTO_INCREMENT COMMENT '队伍id',
  `captain_id` int NOT NULL COMMENT '队长id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '队伍名称',
  `team_description` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '队伍介绍',
  `num` int NOT NULL DEFAULT '0' COMMENT '队伍人数',
  `score` float NOT NULL DEFAULT '0' COMMENT '队伍分数',
  `rating` int NOT NULL DEFAULT '1500' COMMENT '队伍rating',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `dismiss_time` datetime DEFAULT NULL COMMENT '解散时间',
  `ip` varchar(46) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建ip',
  `is_used` int DEFAULT '1' COMMENT '是否使用（1-使用；0-不显示）',
  PRIMARY KEY (`team_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='队伍表';

-- ----------------------------
-- Table structure for team_application
-- ----------------------------
DROP TABLE IF EXISTS `team_application`;
CREATE TABLE `team_application` (
  `applica_id` int NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `team_id` int NOT NULL COMMENT '队伍id',
  `user_id` int NOT NULL COMMENT '用户id',
  `invite_or_apply` int NOT NULL COMMENT '邀请or申请（0-邀请；1-申请）',
  `applica_condition` int DEFAULT '0' COMMENT '状态（0-待确认；1-接受；2-拒绝；3-取消）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `process_time` datetime DEFAULT NULL COMMENT '处理时间',
  `create_ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建ip',
  `process_ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '处理ip',
  PRIMARY KEY (`applica_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='队伍申请表';

-- ----------------------------
-- Table structure for team_member
-- ----------------------------
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member` (
  `team_id` int NOT NULL COMMENT '队伍id',
  `user_id` int NOT NULL COMMENT '用户id',
  `is_used` int DEFAULT '1' COMMENT '是否使用（1-使用；0-不显示）',
  `join_time` datetime NOT NULL COMMENT '加入时间',
  PRIMARY KEY (`user_id`,`team_id`),
  KEY `fk_Team_member_1` (`team_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='队伍成员表';

-- ----------------------------
-- Table structure for testcase
-- ----------------------------
DROP TABLE IF EXISTS `testcase`;
CREATE TABLE `testcase` (
  `testcase_rank` int NOT NULL COMMENT '测试数据序号',
  `problem_id` int NOT NULL COMMENT '题目id',
  `hash_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '唯一hash码',
  `is_used` int DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  `can_download` int DEFAULT '0' COMMENT '是否能下载（1-能；0-不能）',
  `download_times` int NOT NULL DEFAULT '0' COMMENT '下载次数',
  `in_path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'in文件路径',
  `out_path` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'out文件路径',
  `score` float NOT NULL DEFAULT '100' COMMENT '分数',
  PRIMARY KEY (`testcase_rank`,`problem_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='测试数据表';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户姓名',
  `head_image_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '头像文件url',
  `email` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `student_id` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学号',
  `gender` int DEFAULT NULL COMMENT '性别（0-男；1-女）',
  `telephone` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `school` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学校',
  `personal_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '个人说明',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不显示）',
  `ip` varchar(46) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '注册ip',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `password` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户加密密码',
  `salt` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐值',
  `is_email_check` int NOT NULL DEFAULT '0' COMMENT '是否完成邮箱检测（1-完成；0-未完成）',
  `is_info_finish` int NOT NULL DEFAULT '0' COMMENT '是否完成信息录入（1-完成；0-未完成）',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户基本信息表';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1000', 'qiyuan', 'qiyuan', null, null, '1746257500@qq.com', null, null, null, null, null, '1', '27.23.213.83', '2021-02-16 04:48:34', '9cab30d5891247b27f9859d8e1bab53a', 'eM2zpURplsT0rghCx3WJ', '0', '0');

-- ----------------------------
-- Table structure for users_favorites
-- ----------------------------
DROP TABLE IF EXISTS `users_favorites`;
CREATE TABLE `users_favorites` (
  `user_id` int NOT NULL COMMENT '用户id',
  `favorite_user_id` int NOT NULL COMMENT '被关注用户id',
  `create_time` datetime NOT NULL COMMENT '关注时间',
  `is_focus` int NOT NULL DEFAULT '1' COMMENT '是否关注动态（0-"不关注"；1-"关注"）',
  PRIMARY KEY (`user_id`,`favorite_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户关注表';

-- ----------------------------
-- Table structure for users_honor
-- ----------------------------
DROP TABLE IF EXISTS `users_honor`;
CREATE TABLE `users_honor` (
  `user_id` int NOT NULL COMMENT '用户id',
  `honor_id` int NOT NULL COMMENT '称号id',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `is_show` int NOT NULL DEFAULT '1' COMMENT '是否显示（1-显示；0-不显示）',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  PRIMARY KEY (`user_id`,`honor_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户称号获得表';

-- ----------------------------
-- Table structure for users_usage
-- ----------------------------
DROP TABLE IF EXISTS `users_usage`;
CREATE TABLE `users_usage` (
  `user_id` int NOT NULL COMMENT '用户id',
  `score` double NOT NULL DEFAULT '0' COMMENT '个人分数',
  `rating` int NOT NULL DEFAULT '1500' COMMENT 'rating分',
  `download_times` int NOT NULL DEFAULT '0' COMMENT '下载次数',
  `page_index` int NOT NULL DEFAULT '1' COMMENT '页码',
  `user_level` int NOT NULL DEFAULT '1' COMMENT '等级',
  `language` int NOT NULL DEFAULT '0' COMMENT '常用语言',
  `all_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT '提交次数',
  `ac_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'AC次数',
  `pe_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'PE次数',
  `wa_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'WA次数',
  `tle_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'TLE次数',
  `mle_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'MLE次数',
  `ole_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'OLE次数',
  `re_submit_times` int unsigned NOT NULL DEFAULT '0' COMMENT 'RE次数',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户使用信息表';

-- ----------------------------
-- Table structure for users_wronged_probelm_list
-- ----------------------------
DROP TABLE IF EXISTS `users_wronged_probelm_list`;
CREATE TABLE `users_wronged_probelm_list` (
  `list_id` int NOT NULL AUTO_INCREMENT COMMENT '明细id',
  `user_id` int NOT NULL COMMENT '用户id',
  `problem_id` int NOT NULL COMMENT '题目id',
  `contest_id` int NOT NULL COMMENT '比赛id',
  `rank_num` int NOT NULL COMMENT '比赛题目编号',
  `result` int NOT NULL COMMENT '运行结果',
  `is_used` int NOT NULL DEFAULT '1' COMMENT '是否使用（1-使用；0-不使用）',
  PRIMARY KEY (`list_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户错题表';

GRANT all privileges on ctguacm_onlinejudge.* to 'ctguacm'@'%';
flush privileges;

DROP TRIGGER IF EXISTS `add_new_problem_usage`;
DELIMITER ;;
CREATE TRIGGER `add_new_problem_usage` AFTER INSERT ON `problem` FOR EACH ROW begin
	DECLARE now_problem_id int;
	set now_problem_id = new.problem_id;
	insert into problem_usage (problem_id) value (now_problem_id);
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `add_new_user_usage`;
DELIMITER ;;
CREATE TRIGGER `add_new_user_usage` AFTER INSERT ON `users` FOR EACH ROW begin
	DECLARE now_user_id int;
	set now_user_id = new.user_id;
	insert into users_usage (user_id) value (now_user_id);
end
;;
DELIMITER ;



