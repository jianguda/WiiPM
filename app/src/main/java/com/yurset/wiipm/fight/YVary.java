package com.yurset.wiipm.fight;

import com.yurset.wiipm.main.IGame;

public class YVary {
	// 处理"变化"
	public static void callEffect(int cate, int moveH, int moveZ) {
		int tempV = 0;
		if (cate == 0) {
			switch (XInfo.teamHMoveIndex[IGame.pmSeq][moveH]) {
				case 14:// 剑之舞
					XBattle.hTeamS[1] += 2;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					break;
				case 18:// 吹飞
					break;
				case 28:// 扔沙
					break;
				case 39:// 甩尾
					XBattle.zTeamS[2]--;
					if (XBattle.zTeamS[2] < -6)
						XBattle.zTeamS[2] = -6;
					break;
				case 43:// 瞪眼
					XBattle.zTeamS[2]--;
					if (XBattle.zTeamS[2] < -6)
						XBattle.zTeamS[2] = -6;
					break;
				case 45:// 叫声
					XBattle.zTeamS[1]--;
					if (XBattle.zTeamS[1] < -6)
						XBattle.zTeamS[1] = -6;
					break;
				case 46:// 吼叫
					break;
				case 47:// 歌唱
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 5;
					}
					break;
				case 48:// 超音波
					break;
				case 50:// 束缚
					break;
				case 54:// 白雾
					break;
				case 73:// 寄生种子
					break;
				case 74:// 成长
					if (XBattle.weather == 1) {
						XBattle.hTeamS[1] += 2;
						XBattle.hTeamS[3] += 2;
					} else {
						XBattle.hTeamS[1]++;
						XBattle.hTeamS[3]++;
					}
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					if (XBattle.hTeamS[3] > 6)
						XBattle.hTeamS[3] = 6;
					break;
				case 77:// 毒粉
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 4;
					}
					break;
				case 78:// 麻痹粉
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 3;
					}
					break;
				case 79:// 催眠粉
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 5;
					}
					break;
				case 81:// 吐丝
					XBattle.zTeamS[0] -= 2;
					if (XBattle.zTeamS[0] < -6)
						XBattle.zTeamS[0] = -6;
					break;
				case 86:// 电磁波
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						if (XInfo.teamHAbility[IGame.pmSeq].equals("普通皮肤")) {
							if (!(XInfo.teamZType[IGame.currPM][0].equals("鬼") || XInfo.teamZType[IGame.currPM][1]
									.equals("鬼"))) {
								XBattle.abnormalZ[IGame.currPM] = 3;
							}
						} else if (!(XInfo.teamZType[IGame.currPM][0].equals("地") || XInfo.teamZType[IGame.currPM][1]
								.equals("地"))) {
							XBattle.abnormalZ[IGame.currPM] = 3;
						}
					}
					break;
				case 92:// 剧毒
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 4;
					}
					break;
				case 95:// 催眠术
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 5;
					}
					break;
				case 96:// 瑜珈之形
					XBattle.hTeamS[1]++;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					break;
				case 97:// 高速移动
					XBattle.hTeamS[0] += 2;
					if (XBattle.hTeamS[0] > 6)
						XBattle.hTeamS[0] = 6;
					break;
				case 100:// 瞬间移动
					break;
				case 102:// 模仿
					break;
				case 103:// 噪音
					XBattle.zTeamS[2]--;
					if (XBattle.zTeamS[2] < -6)
						XBattle.zTeamS[2] = -6;
					break;
				case 104:// 影分身
					break;
				case 105:// 自我再生
					XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 106:// 变硬
					XBattle.hTeamS[2]++;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					break;
				case 107:// 变小
					break;
				case 108:// 烟幕
					break;
				case 109:// 怪异光线
					break;
				case 110:// 躲进贝壳
					XBattle.hTeamS[2]++;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					break;
				case 111:// 变圆
					break;
				case 112:// 栅栏
					XBattle.hTeamS[2] += 2;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					break;
				case 113:// 光之壁
					break;
				case 114:// 黑雾
					break;
				case 115:// 反射盾
					break;
				case 116:// 蓄气
					break;
				case 118:// 摇手指
					break;
				case 119:// 鹦鹉学舌
					break;
				case 133:// 超级健忘
					XBattle.hTeamS[4] += 2;
					if (XBattle.hTeamS[4] > 6)
						XBattle.hTeamS[4] = 6;
					break;
				case 134:// 弄弯匙子
					break;
				case 135:// 生蛋
					XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 137:// 蛇瞪眼
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 3;
					}
					break;
				case 139:// 毒瓦斯
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 4;
					}
					break;
				case 142:// 恶魔之吻
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 5;
					}
					break;
				case 144:// 变身
					break;
				case 147:// 蘑菇孢子
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 5;
					}
					break;
				case 148:// 闪光
					break;
				case 150:// 弹跳
					break;
				case 151:// 溶化
					XBattle.hTeamS[2] += 2;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					break;
				case 156:// 睡眠
					break;
				case 159:// 变方
					XBattle.hTeamS[1]++;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					break;
				case 160:// 属性切换
					break;
				case 164:// 替身
					break;
				case 166:// 素描
					break;
				case 169:// 蜘蛛网
					break;
				case 170:// 心眼
					break;
				case 171:// 噩梦
					break;
				case 174:// 诅咒
					break;
				case 176:// 属性切换2
					break;
				case 178:// 棉花孢子
					XBattle.zTeamS[0] -= 2;
					if (XBattle.zTeamS[0] < -6)
						XBattle.zTeamS[0] = -6;
					break;
				case 180:// 恨
					break;
				case 182:// 保护
					break;
				case 184:// 恐惧颜
					XBattle.zTeamS[0] -= 2;
					if (XBattle.zTeamS[0] < -6)
						XBattle.zTeamS[0] = -6;
					break;
				case 186:// 天使之吻
					break;
				case 187:// 腹鼓
					break;
				case 191:// 撒菱
					break;
				case 193:// 识破
					break;
				case 194:// 同旅
					break;
				case 195:// 灭亡之歌
					break;
				case 197:// 见切
					break;
				case 199:// 锁定
					break;
				case 201:// 沙暴
					XBattle.weather = 3;
					XBattle.weatherClick = 5;
					break;
				case 203:// 忍耐
					break;
				case 204:// 撒娇
					XBattle.zTeamS[1] -= 2;
					if (XBattle.zTeamS[1] < -6)
						XBattle.zTeamS[1] = -6;
					break;
				case 207:// 虚张声势
					break;
				case 208:// 饮奶
					XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 212:// 黑眼神
					break;
				case 213:// 着迷
					break;
				case 214:// 梦话
					break;
				case 215:// 治愈之铃
					XBattle.abnormalH[IGame.pmSeq] = 0;
					break;
				case 219:// 神秘守护
					break;
				case 220:// 痛苦平分
					int tempHP = (XBattle.hTeamHP[IGame.pmSeq] + XBattle.zTeamHP[IGame.currPM]) / 2;
					XBattle.hTeamHP[IGame.pmSeq] = tempHP;
					XBattle.zTeamHP[IGame.currPM] = tempHP;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 226:// 接力棒
					break;
				case 227:// 鼓掌
					break;
				case 230:// 香甜气息
					break;
				case 234:// 朝之阳
					if (XBattle.weather == 0) {
						XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					} else if (XBattle.weather == 1) {
						XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] * 2 / 3;
					} else {
						XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 4;
					}
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 235:// 光合作用
					if (XBattle.weather == 0) {
						XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					} else if (XBattle.weather == 1) {
						XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] * 2 / 3;
					} else {
						XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 4;
					}
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 236:// 月之光
					XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 240:// 祈雨
					XBattle.weather = 2;
					XBattle.weatherClick = 5;
					break;
				case 241:// 放晴
					XBattle.weather = 1;
					XBattle.weatherClick = 5;
					break;
				case 244:// 自我暗示
					for (int i = 0; i < 5; i++) {
						XBattle.hTeamS[i] = XBattle.zTeamS[i];
					}
					break;
				case 254:// 能量储存
					break;
				case 256:// 能量吸入
					break;
				case 258:// 冰雹
					XBattle.weather = 4;
					XBattle.weatherClick = 5;
					break;
				case 259:// 寻衅
					break;
				case 260:// 煽动
					break;
				case 261:// 鬼火
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 1;
					}
					break;
				case 262:// 临别礼物
					break;
				case 266:// 跟我来
					break;
				case 267:// 自然力量
					break;
				case 268:// 充电
					break;
				case 269:// 挑拨
					break;
				case 270:// 帮手
					break;
				case 271:// 戏法
					break;
				case 272:// 换装
					break;
				case 273:// 许愿
					break;
				case 274:// 猫之手
					break;
				case 275:// 扎根
					break;
				case 277:// 魔装反射
					break;
				case 278:// 道具回收
					break;
				case 281:// 哈欠
					break;
				case 285:// 特性交换
					break;
				case 286:// 封印
					break;
				case 287:// 精神回复
					XBattle.abnormalH[IGame.pmSeq] = 0;
					break;
				case 288:// 怨念
					break;
				case 289:// 抢夺
					break;
				case 293:// 保护色
					break;
				case 294:// 萤火
					XBattle.hTeamS[3] += 3;
					if (XBattle.hTeamS[3] > 6)
						XBattle.hTeamS[3] = 6;
					break;
				case 297:// 羽毛舞
					XBattle.zTeamS[1] -= 2;
					if (XBattle.zTeamS[1] < -6)
						XBattle.zTeamS[1] = -6;
					break;
				case 298:// 草裙舞
					break;
				case 300:// 玩泥
					break;
				case 303:// 偷懒
					XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 312:// 芳香疗法
					XBattle.abnormalH[IGame.pmSeq] = 0;
					break;
				case 313:// 假哭
					XBattle.zTeamS[4] -= 2;
					if (XBattle.zTeamS[4] < -6)
						XBattle.zTeamS[4] = -6;
					break;
				case 316:// 嗅觉
					break;
				case 319:// 金属音
					XBattle.zTeamS[4] -= 2;
					if (XBattle.zTeamS[4] < -6)
						XBattle.zTeamS[4] = -6;
					break;
				case 320:// 草笛
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 5;
					}
					break;
				case 321:// 挠痒
					XBattle.zTeamS[1]--;
					XBattle.zTeamS[2]--;
					if (XBattle.zTeamS[1] < -6)
						XBattle.zTeamS[1] = -6;
					if (XBattle.zTeamS[2] < -6)
						XBattle.zTeamS[2] = -6;
					break;
				case 322:// 宇宙力量
					XBattle.hTeamS[2]++;
					XBattle.hTeamS[4]++;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					if (XBattle.hTeamS[4] > 6)
						XBattle.hTeamS[4] = 6;
					break;
				case 334:// 铁壁
					XBattle.hTeamS[2] += 2;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					break;
				case 335:// 禁止通行
					break;
				case 336:// 远吠
					XBattle.hTeamS[1]++;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					break;
				case 339:// 巨大化
					XBattle.hTeamS[1]++;
					XBattle.hTeamS[2]++;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					break;
				case 346:// 玩水
					break;
				case 347:// 冥想
					XBattle.hTeamS[3]++;
					XBattle.hTeamS[4]++;
					if (XBattle.hTeamS[3] > 6)
						XBattle.hTeamS[3] = 6;
					if (XBattle.hTeamS[4] > 6)
						XBattle.hTeamS[4] = 6;
					break;
				case 349:// 龙之舞
					XBattle.hTeamS[0]++;
					XBattle.hTeamS[1]++;
					if (XBattle.hTeamS[0] > 6)
						XBattle.hTeamS[0] = 6;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					break;
				case 355:// 羽栖
					break;
				case 356:// 重力
					break;
				case 357:// 奇迹之眼
					break;
				case 361:// 治愈之愿
					break;
				case 366:// 顺风
					break;
				case 367:// 点穴
					break;
				case 373:// 扣押
					break;
				case 375:// 精神转移
					break;
				case 377:// 回复封印
					break;
				case 379:// 力量戏法
					break;
				case 380:// 胃液
					break;
				case 381:// 咒语
					break;
				case 382:// 先取
					break;
				case 383:// 效仿
					break;
				case 384:// 力量交换
					tempV = XBattle.hTeamS[1];
					XBattle.hTeamS[1] = XBattle.zTeamS[1];
					XBattle.zTeamS[1] = tempV;
					tempV = XBattle.hTeamS[3];
					XBattle.hTeamS[3] = XBattle.zTeamS[3];
					XBattle.zTeamS[3] = tempV;
					break;
				case 385:// 防御交换
					tempV = XBattle.hTeamS[2];
					XBattle.hTeamS[2] = XBattle.zTeamS[2];
					XBattle.zTeamS[2] = tempV;
					tempV = XBattle.hTeamS[4];
					XBattle.hTeamS[4] = XBattle.zTeamS[4];
					XBattle.zTeamS[4] = tempV;
					break;
				case 388:// 烦恼种子
					break;
				case 390:// 毒菱
					break;
				case 391:// 心灵交换
					for (int i = 0; i < 5; i++) {
						tempV = XBattle.hTeamS[i];
						XBattle.hTeamS[i] = XBattle.zTeamS[i];
						XBattle.zTeamS[i] = tempV;
					}
					break;
				case 392:// 液态圈
					break;
				case 393:// 电磁浮游
					break;
				case 397:// 岩切
					XBattle.hTeamS[0] += 2;
					if (XBattle.hTeamS[0] > 6)
						XBattle.hTeamS[0] = 6;
					break;
				case 415:// 偷换
					break;
				case 417:// 阴谋
					XBattle.hTeamS[3] += 2;
					if (XBattle.hTeamS[3] > 6)
						XBattle.hTeamS[3] = 6;
					break;
				case 432:// 除雾
					break;
				case 433:// 欺骗空间
					break;
				case 445:// 诱惑
					break;
				case 446:// 秘密岩石
					break;
				case 455:// 防御指令
					XBattle.hTeamS[2]++;
					XBattle.hTeamS[4]++;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					if (XBattle.hTeamS[4] > 6)
						XBattle.hTeamS[4] = 6;
					break;
				case 456:// 回复指令
					XBattle.hTeamHP[IGame.pmSeq] += XBattle.hTeamV[IGame.pmSeq][0] / 2;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					break;
				case 461:// 新月之舞
					break;
				case 464:// 黑洞
					if (XBattle.abnormalZ[IGame.currPM] == 0) {
						XBattle.abnormalZ[IGame.currPM] = 5;
					}
					break;
				case 468:// 磨爪
					break;
				case 469:// 广域防御
					break;
				case 470:// 防御平分
					break;
				case 471:// 力量平分
					break;
				case 472:// 神奇空间
					break;
				case 475:// 身体净化
					break;
				case 476:// 愤怒之粉
					break;
				case 477:// 念动力
					break;
				case 478:// 魔术空间
					break;
				case 483:// 蝶之舞
					XBattle.hTeamS[0]++;
					XBattle.hTeamS[3]++;
					XBattle.hTeamS[4]++;
					if (XBattle.hTeamS[0] > 6)
						XBattle.hTeamS[0] = 6;
					if (XBattle.hTeamS[3] > 6)
						XBattle.hTeamS[3] = 6;
					if (XBattle.hTeamS[4] > 6)
						XBattle.hTeamS[4] = 6;
					break;
				case 487:// 浸水
					break;
				case 489:// 盘蜷
					break;
				case 493:// 单纯光线
					break;
				case 494:// 变为同伴
					break;
				case 495:// 您先请
					break;
				case 501:// 快速防御
					break;
				case 502:// 位置交换
					break;
				case 504:// 破壳而出
					XBattle.hTeamS[2]--;
					XBattle.hTeamS[4]--;
					XBattle.hTeamS[0] += 2;
					XBattle.hTeamS[1] += 2;
					XBattle.hTeamS[3] += 2;
					if (XBattle.hTeamS[2] < -6)
						XBattle.hTeamS[2] = -6;
					if (XBattle.hTeamS[4] < -6)
						XBattle.hTeamS[4] = -6;
					if (XBattle.hTeamS[0] > 6)
						XBattle.hTeamS[0] = 6;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					if (XBattle.hTeamS[3] > 6)
						XBattle.hTeamS[3] = 6;
					break;
				case 505:// 治愈波动
					break;
				case 508:// 齿轮变换
					XBattle.hTeamS[0] += 2;
					XBattle.hTeamS[1]++;
					if (XBattle.hTeamS[0] > 6)
						XBattle.hTeamS[0] = 6;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					break;
				case 511:// 押后
					break;
				case 513:// 镜面属性
					break;
				case 516:// 传递礼物
					break;
				case 526:// 鼓舞士气
					XBattle.hTeamS[1]++;
					XBattle.hTeamS[3]++;
					if (XBattle.hTeamS[1] > 6)
						XBattle.hTeamS[1] = 6;
					if (XBattle.hTeamS[3] > 6)
						XBattle.hTeamS[3] = 6;
					break;
				case 538:// 棉花防御
					XBattle.hTeamS[2] += 3;
					if (XBattle.hTeamS[2] > 6)
						XBattle.hTeamS[2] = 6;
					break;
			}
		} else if (cate == 1) {
			switch (XInfo.teamZMoveIndex[IGame.currPM][moveZ]) {
				case 14:// 剑之舞
					XBattle.zTeamS[1] += 2;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					break;
				case 18:// 吹飞
					break;
				case 28:// 扔沙
					break;
				case 39:// 甩尾
					XBattle.hTeamS[2]--;
					if (XBattle.hTeamS[2] < -6)
						XBattle.hTeamS[2] = -6;
					break;
				case 43:// 瞪眼
					XBattle.hTeamS[2]--;
					if (XBattle.hTeamS[2] < -6)
						XBattle.hTeamS[2] = -6;
					break;
				case 45:// 叫声
					XBattle.hTeamS[1]--;
					if (XBattle.hTeamS[1] < -6)
						XBattle.hTeamS[1] = -6;
					break;
				case 46:// 吼叫
					break;
				case 47:// 歌唱
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 5;
					}
					break;
				case 48:// 超音波
					break;
				case 50:// 束缚
					break;
				case 54:// 白雾
					break;
				case 73:// 寄生种子
					break;
				case 74:// 成长
					if (XBattle.weather == 1) {
						XBattle.zTeamS[1] += 2;
						XBattle.zTeamS[3] += 2;
					} else {
						XBattle.zTeamS[1]++;
						XBattle.zTeamS[3]++;
					}
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					if (XBattle.zTeamS[3] > 6)
						XBattle.zTeamS[3] = 6;
					break;
				case 77:// 毒粉
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 4;
					}
					break;
				case 78:// 麻痹粉
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 3;
					}
					break;
				case 79:// 催眠粉
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 5;
					}
					break;
				case 81:// 吐丝
					XBattle.hTeamS[0] -= 2;
					if (XBattle.hTeamS[0] < -6)
						XBattle.hTeamS[0] = -6;
					break;
				case 86:// 电磁波
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						if (XInfo.teamZAbility[IGame.currPM].equals("普通皮肤")) {
							if (!(XInfo.teamHType[IGame.pmSeq][0].equals("鬼") || XInfo.teamHType[IGame.pmSeq][1]
									.equals("鬼"))) {
								XBattle.abnormalH[IGame.pmSeq] = 3;
							}
						} else if (!(XInfo.teamHType[IGame.pmSeq][0].equals("地") || XInfo.teamHType[IGame.pmSeq][1]
								.equals("地"))) {
							XBattle.abnormalH[IGame.pmSeq] = 3;
						}
					}
					break;
				case 92:// 剧毒
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 4;
					}
					break;
				case 95:// 催眠术
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 5;
					}
					break;
				case 96:// 瑜珈之形
					XBattle.zTeamS[1]++;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					break;
				case 97:// 高速移动
					XBattle.zTeamS[0] += 2;
					if (XBattle.zTeamS[0] > 6)
						XBattle.zTeamS[0] = 6;
					break;
				case 100:// 瞬间移动
					break;
				case 102:// 模仿
					break;
				case 103:// 噪音
					XBattle.hTeamS[2]--;
					if (XBattle.hTeamS[2] < -6)
						XBattle.hTeamS[2] = -6;
					break;
				case 104:// 影分身
					break;
				case 105:// 自我再生
					XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 106:// 变硬
					XBattle.zTeamS[2]++;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					break;
				case 107:// 变小
					break;
				case 108:// 烟幕
					break;
				case 109:// 怪异光线
					break;
				case 110:// 躲进贝壳
					XBattle.zTeamS[2]++;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					break;
				case 111:// 变圆
					break;
				case 112:// 栅栏
					XBattle.zTeamS[2] += 2;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					break;
				case 113:// 光之壁
					break;
				case 114:// 黑雾
					break;
				case 115:// 反射盾
					break;
				case 116:// 蓄气
					break;
				case 118:// 摇手指
					break;
				case 119:// 鹦鹉学舌
					break;
				case 133:// 超级健忘
					XBattle.zTeamS[4] += 2;
					if (XBattle.zTeamS[4] > 6)
						XBattle.zTeamS[4] = 6;
					break;
				case 134:// 弄弯匙子
					break;
				case 135:// 生蛋
					XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 137:// 蛇瞪眼
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 3;
					}
					break;
				case 139:// 毒瓦斯
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 4;
					}
					break;
				case 142:// 恶魔之吻
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 5;
					}
					break;
				case 144:// 变身
					break;
				case 147:// 蘑菇孢子
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 5;
					}
					break;
				case 148:// 闪光
					break;
				case 150:// 弹跳
					break;
				case 151:// 溶化
					XBattle.zTeamS[2] += 2;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					break;
				case 156:// 睡眠
					break;
				case 159:// 变方
					XBattle.zTeamS[1]++;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					break;
				case 160:// 属性切换
					break;
				case 164:// 替身
					break;
				case 166:// 素描
					break;
				case 169:// 蜘蛛网
					break;
				case 170:// 心眼
					break;
				case 171:// 噩梦
					break;
				case 174:// 诅咒
					break;
				case 176:// 属性切换2
					break;
				case 178:// 棉花孢子
					XBattle.hTeamS[0] -= 2;
					if (XBattle.hTeamS[0] < -6)
						XBattle.hTeamS[0] = -6;
					break;
				case 180:// 恨
					break;
				case 182:// 保护
					break;
				case 184:// 恐惧颜
					XBattle.hTeamS[0] -= 2;
					if (XBattle.hTeamS[0] < -6)
						XBattle.hTeamS[0] = -6;
					break;
				case 186:// 天使之吻
					break;
				case 187:// 腹鼓
					break;
				case 191:// 撒菱
					break;
				case 193:// 识破
					break;
				case 194:// 同旅
					break;
				case 195:// 灭亡之歌
					break;
				case 197:// 见切
					break;
				case 199:// 锁定
					break;
				case 201:// 沙暴
					XBattle.weather = 3;
					XBattle.weatherClick = 5;
					break;
				case 203:// 忍耐
					break;
				case 204:// 撒娇
					XBattle.hTeamS[1] -= 2;
					if (XBattle.hTeamS[1] < -6)
						XBattle.hTeamS[1] = -6;
					break;
				case 207:// 虚张声势
					break;
				case 208:// 饮奶
					XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 212:// 黑眼神
					break;
				case 213:// 着迷
					break;
				case 214:// 梦话
					break;
				case 215:// 治愈之铃
					XBattle.abnormalZ[IGame.currPM] = 0;
					break;
				case 219:// 神秘守护
					break;
				case 220:// 痛苦平分
					int tempHP = (XBattle.hTeamHP[IGame.pmSeq] + XBattle.zTeamHP[IGame.currPM]) / 2;
					XBattle.hTeamHP[IGame.pmSeq] = tempHP;
					XBattle.zTeamHP[IGame.currPM] = tempHP;
					if (XBattle.hTeamHP[IGame.pmSeq] > XBattle.hTeamV[IGame.pmSeq][0]) {
						XBattle.hTeamHP[IGame.pmSeq] = XBattle.hTeamV[IGame.pmSeq][0];
					}
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 226:// 接力棒
					break;
				case 227:// 鼓掌
					break;
				case 230:// 香甜气息
					break;
				case 234:// 朝之阳
					if (XBattle.weather == 0) {
						XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					} else if (XBattle.weather == 1) {
						XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] * 2 / 3;
					} else {
						XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 4;
					}
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 235:// 光合作用
					if (XBattle.weather == 0) {
						XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					} else if (XBattle.weather == 1) {
						XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] * 2 / 3;
					} else {
						XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 4;
					}
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 236:// 月之光
					XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 240:// 祈雨
					XBattle.weather = 2;
					XBattle.weatherClick = 5;
					break;
				case 241:// 放晴
					XBattle.weather = 1;
					XBattle.weatherClick = 5;
					break;
				case 244:// 自我暗示
					for (int i = 0; i < 5; i++) {
						XBattle.zTeamS[i] = XBattle.hTeamS[i];
					}
					break;
				case 254:// 能量储存
					break;
				case 256:// 能量吸入
					break;
				case 258:// 冰雹
					XBattle.weather = 4;
					XBattle.weatherClick = 5;
					break;
				case 259:// 寻衅
					break;
				case 260:// 煽动
					break;
				case 261:// 鬼火
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 1;
					}
					break;
				case 262:// 临别礼物
					break;
				case 266:// 跟我来
					break;
				case 267:// 自然力量
					break;
				case 268:// 充电
					break;
				case 269:// 挑拨
					break;
				case 270:// 帮手
					break;
				case 271:// 戏法
					break;
				case 272:// 换装
					break;
				case 273:// 许愿
					break;
				case 274:// 猫之手
					break;
				case 275:// 扎根
					break;
				case 277:// 魔装反射
					break;
				case 278:// 道具回收
					break;
				case 281:// 哈欠
					break;
				case 285:// 特性交换
					break;
				case 286:// 封印
					break;
				case 287:// 精神回复
					XBattle.abnormalZ[IGame.currPM] = 0;
					break;
				case 288:// 怨念
					break;
				case 289:// 抢夺
					break;
				case 293:// 保护色
					break;
				case 294:// 萤火
					XBattle.zTeamS[3] += 3;
					if (XBattle.zTeamS[3] > 6)
						XBattle.zTeamS[3] = 6;
					break;
				case 297:// 羽毛舞
					XBattle.hTeamS[1] -= 2;
					if (XBattle.hTeamS[1] < -6)
						XBattle.hTeamS[1] = -6;
					break;
				case 298:// 草裙舞
					break;
				case 300:// 玩泥
					break;
				case 303:// 偷懒
					XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 312:// 芳香疗法
					XBattle.abnormalZ[IGame.currPM] = 0;
					break;
				case 313:// 假哭
					XBattle.hTeamS[4] -= 2;
					if (XBattle.hTeamS[4] < -6)
						XBattle.hTeamS[4] = -6;
					break;
				case 316:// 嗅觉
					break;
				case 319:// 金属音
					XBattle.hTeamS[4] -= 2;
					if (XBattle.hTeamS[4] < -6)
						XBattle.hTeamS[4] = -6;
					break;
				case 320:// 草笛
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 5;
					}
					break;
				case 321:// 挠痒
					XBattle.hTeamS[1]--;
					XBattle.hTeamS[2]--;
					if (XBattle.hTeamS[1] < -6)
						XBattle.hTeamS[1] = -6;
					if (XBattle.hTeamS[2] < -6)
						XBattle.hTeamS[2] = -6;
					break;
				case 322:// 宇宙力量
					XBattle.zTeamS[2]++;
					XBattle.zTeamS[4]++;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					if (XBattle.zTeamS[4] > 6)
						XBattle.zTeamS[4] = 6;
					break;
				case 334:// 铁壁
					XBattle.zTeamS[2] += 2;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					break;
				case 335:// 禁止通行
					break;
				case 336:// 远吠
					XBattle.zTeamS[1]++;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					break;
				case 339:// 巨大化
					XBattle.zTeamS[1]++;
					XBattle.zTeamS[2]++;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					break;
				case 346:// 玩水
					break;
				case 347:// 冥想
					XBattle.zTeamS[3]++;
					XBattle.zTeamS[4]++;
					if (XBattle.zTeamS[3] > 6)
						XBattle.zTeamS[3] = 6;
					if (XBattle.zTeamS[4] > 6)
						XBattle.zTeamS[4] = 6;
					break;
				case 349:// 龙之舞
					XBattle.zTeamS[0]++;
					XBattle.zTeamS[1]++;
					if (XBattle.zTeamS[0] > 6)
						XBattle.zTeamS[0] = 6;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					break;
				case 355:// 羽栖
					break;
				case 356:// 重力
					break;
				case 357:// 奇迹之眼
					break;
				case 361:// 治愈之愿
					break;
				case 366:// 顺风
					break;
				case 367:// 点穴
					break;
				case 373:// 扣押
					break;
				case 375:// 精神转移
					break;
				case 377:// 回复封印
					break;
				case 379:// 力量戏法
					break;
				case 380:// 胃液
					break;
				case 381:// 咒语
					break;
				case 382:// 先取
					break;
				case 383:// 效仿
					break;
				case 384:// 力量交换
					tempV = XBattle.hTeamS[1];
					XBattle.hTeamS[1] = XBattle.zTeamS[1];
					XBattle.zTeamS[1] = tempV;
					tempV = XBattle.hTeamS[3];
					XBattle.hTeamS[3] = XBattle.zTeamS[3];
					XBattle.zTeamS[3] = tempV;
					break;
				case 385:// 防御交换
					tempV = XBattle.hTeamS[2];
					XBattle.hTeamS[2] = XBattle.zTeamS[2];
					XBattle.zTeamS[2] = tempV;
					tempV = XBattle.hTeamS[4];
					XBattle.hTeamS[4] = XBattle.zTeamS[4];
					XBattle.zTeamS[4] = tempV;
					break;
				case 388:// 烦恼种子
					break;
				case 390:// 毒菱
					break;
				case 391:// 心灵交换
					for (int i = 0; i < 5; i++) {
						tempV = XBattle.hTeamS[i];
						XBattle.hTeamS[i] = XBattle.zTeamS[i];
						XBattle.zTeamS[i] = tempV;
					}
					break;
				case 392:// 液态圈
					break;
				case 393:// 电磁浮游
					break;
				case 397:// 岩切
					XBattle.zTeamS[0] += 2;
					if (XBattle.zTeamS[0] > 6)
						XBattle.zTeamS[0] = 6;
					break;
				case 415:// 偷换
					break;
				case 417:// 阴谋
					XBattle.zTeamS[3] += 2;
					if (XBattle.zTeamS[3] > 6)
						XBattle.zTeamS[3] = 6;
					break;
				case 432:// 除雾
					break;
				case 433:// 欺骗空间
					break;
				case 445:// 诱惑
					break;
				case 446:// 秘密岩石
					break;
				case 455:// 防御指令
					XBattle.zTeamS[2]++;
					XBattle.zTeamS[4]++;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					if (XBattle.zTeamS[4] > 6)
						XBattle.zTeamS[4] = 6;
					break;
				case 456:// 回复指令
					XBattle.zTeamHP[IGame.currPM] += XBattle.zTeamV[IGame.currPM][0] / 2;
					if (XBattle.zTeamHP[IGame.currPM] > XBattle.zTeamV[IGame.currPM][0]) {
						XBattle.zTeamHP[IGame.currPM] = XBattle.zTeamV[IGame.currPM][0];
					}
					break;
				case 461:// 新月之舞
					break;
				case 464:// 黑洞
					if (XBattle.abnormalH[IGame.pmSeq] == 0) {
						XBattle.abnormalH[IGame.pmSeq] = 5;
					}
					break;
				case 468:// 磨爪
					break;
				case 469:// 广域防御
					break;
				case 470:// 防御平分
					break;
				case 471:// 力量平分
					break;
				case 472:// 神奇空间
					break;
				case 475:// 身体净化
					break;
				case 476:// 愤怒之粉
					break;
				case 477:// 念动力
					break;
				case 478:// 魔术空间
					break;
				case 483:// 蝶之舞
					XBattle.zTeamS[0]++;
					XBattle.zTeamS[3]++;
					XBattle.zTeamS[4]++;
					if (XBattle.zTeamS[0] > 6)
						XBattle.zTeamS[0] = 6;
					if (XBattle.zTeamS[3] > 6)
						XBattle.zTeamS[3] = 6;
					if (XBattle.zTeamS[4] > 6)
						XBattle.zTeamS[4] = 6;
					break;
				case 487:// 浸水
					break;
				case 489:// 盘蜷
					break;
				case 493:// 单纯光线
					break;
				case 494:// 变为同伴
					break;
				case 495:// 您先请
					break;
				case 501:// 快速防御
					break;
				case 502:// 位置交换
					break;
				case 504:// 破壳而出
					XBattle.zTeamS[2]--;
					XBattle.zTeamS[4]--;
					XBattle.zTeamS[0] += 2;
					XBattle.zTeamS[1] += 2;
					XBattle.zTeamS[3] += 2;
					if (XBattle.zTeamS[2] < -6)
						XBattle.zTeamS[2] = -6;
					if (XBattle.zTeamS[4] < -6)
						XBattle.zTeamS[4] = -6;
					if (XBattle.zTeamS[0] > 6)
						XBattle.zTeamS[0] = 6;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					if (XBattle.zTeamS[3] > 6)
						XBattle.zTeamS[3] = 6;
					break;
				case 505:// 治愈波动
					break;
				case 508:// 齿轮变换
					XBattle.zTeamS[0] += 2;
					XBattle.zTeamS[1]++;
					if (XBattle.zTeamS[0] > 6)
						XBattle.zTeamS[0] = 6;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					break;
				case 511:// 押后
					break;
				case 513:// 镜面属性
					break;
				case 516:// 传递礼物
					break;
				case 526:// 鼓舞士气
					XBattle.zTeamS[1]++;
					XBattle.zTeamS[3]++;
					if (XBattle.zTeamS[1] > 6)
						XBattle.zTeamS[1] = 6;
					if (XBattle.zTeamS[3] > 6)
						XBattle.zTeamS[3] = 6;
					break;
				case 538:// 棉花防御
					XBattle.zTeamS[2] += 3;
					if (XBattle.zTeamS[2] > 6)
						XBattle.zTeamS[2] = 6;
					break;
			}
		}
	}
}