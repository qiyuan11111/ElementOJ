/*
 Navicat Premium Data Transfer

 Source Server         : 116
 Source Server Type    : MongoDB
 Source Server Version : 50002
 Source Host           : 116.62.103.194:27017
 Source Schema         : eloj

 Target Server Type    : MongoDB
 Target Server Version : 50002
 File Encoding         : 65001

 Date: 06/09/2021 18:24:30
*/
use eloj;
// ----------------------------
// Collection structure for contest_enter
// ----------------------------
db.getCollection("contest_enter").drop();
db.createCollection("contest_enter");

// ----------------------------
// Collection structure for contest_problem
// ----------------------------
db.getCollection("contest_problem").drop();
db.createCollection("contest_problem");
