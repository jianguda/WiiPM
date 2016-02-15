package com.yurset.wiipm.fight;

import com.yurset.wiipm.main.IGame;

public class YPsys {
	// 处理"物理"
	// 例如己方使用“攻击指令”，卡住
	public static void callEffect(int cate, int moveH, int moveZ) {
		int tempV = 0;
		if (cate == 0) {
			switch (XInfo.teamHMoveIndex[IGame.pmSeq][moveH]) {
				case 1:// 拍打
					//无特效
					break;
				case 2:// 空手刀
					break;
				case 3:// 往复拍打
					break;
				case 4:// 连续拳
					break;
				case 5:// 百万拳击
					break;
				case 6:// 招财猫
					break;
				case 7:// 火焰拳
					break;
				case 8:// 冷冻拳
					break;
				case 9:// 雷电拳
					break;
				case 10:// 抓
					break;
				case 11:// 夹
					break;
				case 12:// 剪刀断头台
					break;
				case 15:// 居合斩
					break;
				case 17:// 翅膀拍击
					break;
				case 19:// 飞空
					break;
				case 20:// 勒住
					break;
				case 21:// 打倒
					break;
				case 22:// 藤鞭
					break;
				case 23:// 践踏
					break;
				case 24:// 连环踢
					break;
				case 25:// 百万腿踢
					break;
				case 26:// 飞踢
					break;
				case 27:// 回旋踢
					break;
				case 29:// 头槌
					break;
				case 30:// 角突
					break;
				case 31:// 乱突
					break;
				case 32:// 尖角钻
					break;
				case 33:// 撞击
					break;
				case 34:// 压制
					break;
				case 35:// 卷紧
					break;
				case 36:// 突进
					break;
				case 37:// 暴走
					break;
				case 38:// 舍身撞
					break;
				case 40:// 毒针
					break;
				case 41:// 双针
					break;
				case 42:// 导弹针
					break;
				case 44:// 啃咬
					break;
				case 64:// 啄
					break;
				case 65:// 钻孔啄
					break;
				case 66:// 地狱车
					break;
				case 67:// 过肩摔
					break;
				case 68:// 反击
					break;
				case 69:// 地球投
					break;
				case 70:// 怪力
					break;
				case 75:// 飞叶斩
					break;
				case 88:// 落石
					break;
				case 89:// 地震
					break;
				case 90:// 地裂
					break;
				case 91:// 挖洞
					break;
				case 98:// 电光石火
					break;
				case 99:// 愤怒
					break;
				case 117:// 克制
					break;
				case 120:// 自爆
					break;
				case 121:// 蛋蛋爆弹
					break;
				case 122:// 舌舔
					break;
				case 125:// 骨头棍
					break;
				case 127:// 登瀑
					break;
				case 128:// 贝壳夹
					break;
				case 130:// 火箭头槌
					break;
				case 131:// 尖刺加农炮
					break;
				case 132:// 缠绕
					break;
				case 136:// 飞膝踢
					break;
				case 140:// 扔蛋
					break;
				case 141:// 吸血
					break;
				case 143:// 神鸟
					break;
				case 146:// 飘飘拳
					break;
				case 152:// 蟹钳锤
					break;
				case 153:// 大爆炸
					break;
				case 154:// 乱抓
					break;
				case 155:// 骨头回旋镖
					break;
				case 157:// 岩崩
					break;
				case 158:// 必杀门牙
					break;
				case 162:// 愤怒门牙
					break;
				case 163:// 切裂
					break;
				case 165:// 拼命
					break;
				case 167:// 三连踢
					break;
				case 168:// 偷盗
					break;
				case 172:// 火焰车
					break;
				case 175:// 垂死挣扎
					break;
				case 179:// 起死回生
					break;
				case 183:// 音速拳
					break;
				case 185:// 暗算
					break;
				case 198:// 骨头冲锋
					break;
				case 200:// 逆鳞
					break;
				case 205:// 滚动
					break;
				case 206:// 刀背打
					break;
				case 209:// 电火花
					break;
				case 210:// 连续切
					break;
				case 211:// 钢之翼
					break;
				case 216:// 报恩
					break;
				case 217:// 礼物
					break;
				case 218:// 撒气
					break;
				case 221:// 神圣火焰
					break;
				case 222:// 震级变化
					break;
				case 223:// 爆裂拳
					break;
				case 224:// 百万角击
					break;
				case 228:// 追击
					break;
				case 229:// 高速旋转
					break;
				case 231:// 铁尾
					break;
				case 232:// 金属爪
					break;
				case 233:// 当身投
					break;
				case 238:// 十字切
					break;
				case 242:// 咬碎
					break;
				case 245:// 神速
					break;
				case 249:// 碎岩
					break;
				case 251:// 围攻
					break;
				case 252:// 下马威
					break;
				case 263:// 空元气
					break;
				case 264:// 气合拳
					break;
				case 265:// 清醒
					break;
				case 276:// 蛮力
					break;
				case 279:// 复仇
					break;
				case 280:// 瓦割
					break;
				case 282:// 打落
					break;
				case 283:// 莽撞
					break;
				case 290:// 秘密力量
					break;
				case 291:// 潜水
					break;
				case 292:// 突张
					break;
				case 299:// 火花踢
					break;
				case 301:// 冰球
					break;
				case 302:// 针刺臂膀
					break;
				case 305:// 剧毒之牙
					break;
				case 306:// 崩击之爪
					break;
				case 309:// 彗星拳
					break;
				case 310:// 恐吓
					break;
				case 317:// 岩石封
					break;
				case 325:// 暗影拳
					break;
				case 327:// 升空拳
					break;
				case 328:// 沙地狱
					break;
				case 331:// 种子机枪
					break;
				case 332:// 燕返
					break;
				case 333:// 冰柱针
					break;
				case 337:// 龙之爪
					break;
				case 340:// 飞跃
					break;
				case 342:// 毒尾
					break;
				case 343:// 索要
					break;
				case 344:// 高压电击
					break;
				case 348:// 刃叶斩
					break;
				case 350:// 岩石爆破
					break;
				case 358:// 清醒拍打
					break;
				case 359:// 臂锤
					break;
				case 360:// 螺旋球
					break;
				case 363:// 自然恩惠
					break;
				case 364:// 佯攻
					break;
				case 365:// 啄食
					break;
				case 368:// 金属爆破
					break;
				case 369:// 蜻蜓回转
					break;
				case 370:// 近战
					break;
				case 371:// 报复
					break;
				case 372:// 连打
					break;
				case 374:// 投掷
					break;
				case 386:// 惩罚
					break;
				case 387:// 最终手段
					break;
				case 389:// 偷袭
					break;
				case 394:// 火焰驱进
					break;
				case 395:// 发劲
					break;
				case 398:// 毒突
					break;
				case 400:// 试刀
					break;
				case 401:// 水之尾
					break;
				case 402:// 种子爆弹
					break;
				case 404:// 十字剪
					break;
				case 407:// 龙之冲锋
					break;
				case 409:// 吸取拳
					break;
				case 413:// 勇鸟
					break;
				case 416:// 亿万冲击
					break;
				case 418:// 子弹拳
					break;
				case 419:// 雪崩
					break;
				case 420:// 冰之砾
					break;
				case 421:// 阴影爪
					break;
				case 422:// 雷之牙
					break;
				case 423:// 冰之牙
					break;
				case 424:// 火之牙
					break;
				case 425:// 影击
					break;
				case 427:// 精神切割
					break;
				case 428:// 思念头槌
					break;
				case 431:// 攀岩
					break;
				case 438:// 强力鞭挞
					break;
				case 439:// 岩石炮
					break;
				case 440:// 毒十字
					break;
				case 441:// 粉尘射击
					break;
				case 442:// 铁头槌
					break;
				case 443:// 磁体炸弹
					break;
				case 444:// 石刃
					break;
				case 450:// 虫食
					break;
				case 452:// 木锤
					break;
				case 453:// 水流喷射
					break;
				case 454:// 攻击指令
					break;
				case 457:// 双刃头槌
					break;
				case 458:// 双重攻击
					break;
				case 462:// 捏碎
					break;
				case 467:// 影袭
					break;
				case 479:// 击坠
					break;
				case 480:// 山岚
					break;
				case 484:// 重量炸弹
					break;
				case 488:// 硝化冲锋
					break;
				case 490:// 下旋
					break;
				case 492:// 诈骗
					break;
				case 498:// 循序渐进
					break;
				case 507:// 自由下落
					break;
				case 509:// 仰投
					break;
				case 512:// 杂耍
					break;
				case 514:// 报仇
					break;
				case 523:// 压路
					break;
				case 525:// 龙之尾
					break;
				case 528:// 野性电击
					break;
				case 529:// 钻头直击
					break;
				case 530:// 连环切击
					break;
				case 531:// 心灵压迫
					break;
				case 532:// 木角
					break;
				case 533:// 神圣之剑
					break;
				case 534:// 贝壳刃
					break;
				case 535:// 热践踏
					break;
				case 537:// 坚硬滚动
					break;
				case 541:// 扫荡拍打
					break;
				case 543:// 阿弗罗撞击
					break;
				case 544:// 齿轮飞盘
					break;
				case 550:// 雷击
					break;
				case 553:// 冰结电击
					break;
				case 556:// 冰柱坠落
					break;
				case 557:// 创造胜利
					break;
				case 559:// 交织闪电
					break;
			}
		} else if (cate == 1) {
			switch (XInfo.teamZMoveIndex[IGame.currPM][moveZ]) {
				case 1:// 拍打
					//无特效
					break;
				case 2:// 空手刀
					break;
				case 3:// 往复拍打
					break;
				case 4:// 连续拳
					break;
				case 5:// 百万拳击
					break;
				case 6:// 招财猫
					break;
				case 7:// 火焰拳
					break;
				case 8:// 冷冻拳
					break;
				case 9:// 雷电拳
					break;
				case 10:// 抓
					break;
				case 11:// 夹
					break;
				case 12:// 剪刀断头台
					break;
				case 15:// 居合斩
					break;
				case 17:// 翅膀拍击
					break;
				case 19:// 飞空
					break;
				case 20:// 勒住
					break;
				case 21:// 打倒
					break;
				case 22:// 藤鞭
					break;
				case 23:// 践踏
					break;
				case 24:// 连环踢
					break;
				case 25:// 百万腿踢
					break;
				case 26:// 飞踢
					break;
				case 27:// 回旋踢
					break;
				case 29:// 头槌
					break;
				case 30:// 角突
					break;
				case 31:// 乱突
					break;
				case 32:// 尖角钻
					break;
				case 33:// 撞击
					break;
				case 34:// 压制
					break;
				case 35:// 卷紧
					break;
				case 36:// 突进
					break;
				case 37:// 暴走
					break;
				case 38:// 舍身撞
					break;
				case 40:// 毒针
					break;
				case 41:// 双针
					break;
				case 42:// 导弹针
					break;
				case 44:// 啃咬
					break;
				case 64:// 啄
					break;
				case 65:// 钻孔啄
					break;
				case 66:// 地狱车
					break;
				case 67:// 过肩摔
					break;
				case 68:// 反击
					break;
				case 69:// 地球投
					break;
				case 70:// 怪力
					break;
				case 75:// 飞叶斩
					break;
				case 88:// 落石
					break;
				case 89:// 地震
					break;
				case 90:// 地裂
					break;
				case 91:// 挖洞
					break;
				case 98:// 电光石火
					break;
				case 99:// 愤怒
					break;
				case 117:// 克制
					break;
				case 120:// 自爆
					break;
				case 121:// 蛋蛋爆弹
					break;
				case 122:// 舌舔
					break;
				case 125:// 骨头棍
					break;
				case 127:// 登瀑
					break;
				case 128:// 贝壳夹
					break;
				case 130:// 火箭头槌
					break;
				case 131:// 尖刺加农炮
					break;
				case 132:// 缠绕
					break;
				case 136:// 飞膝踢
					break;
				case 140:// 扔蛋
					break;
				case 141:// 吸血
					break;
				case 143:// 神鸟
					break;
				case 146:// 飘飘拳
					break;
				case 152:// 蟹钳锤
					break;
				case 153:// 大爆炸
					break;
				case 154:// 乱抓
					break;
				case 155:// 骨头回旋镖
					break;
				case 157:// 岩崩
					break;
				case 158:// 必杀门牙
					break;
				case 162:// 愤怒门牙
					break;
				case 163:// 切裂
					break;
				case 165:// 拼命
					break;
				case 167:// 三连踢
					break;
				case 168:// 偷盗
					break;
				case 172:// 火焰车
					break;
				case 175:// 垂死挣扎
					break;
				case 179:// 起死回生
					break;
				case 183:// 音速拳
					break;
				case 185:// 暗算
					break;
				case 198:// 骨头冲锋
					break;
				case 200:// 逆鳞
					break;
				case 205:// 滚动
					break;
				case 206:// 刀背打
					break;
				case 209:// 电火花
					break;
				case 210:// 连续切
					break;
				case 211:// 钢之翼
					break;
				case 216:// 报恩
					break;
				case 217:// 礼物
					break;
				case 218:// 撒气
					break;
				case 221:// 神圣火焰
					break;
				case 222:// 震级变化
					break;
				case 223:// 爆裂拳
					break;
				case 224:// 百万角击
					break;
				case 228:// 追击
					break;
				case 229:// 高速旋转
					break;
				case 231:// 铁尾
					break;
				case 232:// 金属爪
					break;
				case 233:// 当身投
					break;
				case 238:// 十字切
					break;
				case 242:// 咬碎
					break;
				case 245:// 神速
					break;
				case 249:// 碎岩
					break;
				case 251:// 围攻
					break;
				case 252:// 下马威
					break;
				case 263:// 空元气
					break;
				case 264:// 气合拳
					break;
				case 265:// 清醒
					break;
				case 276:// 蛮力
					break;
				case 279:// 复仇
					break;
				case 280:// 瓦割
					break;
				case 282:// 打落
					break;
				case 283:// 莽撞
					break;
				case 290:// 秘密力量
					break;
				case 291:// 潜水
					break;
				case 292:// 突张
					break;
				case 299:// 火花踢
					break;
				case 301:// 冰球
					break;
				case 302:// 针刺臂膀
					break;
				case 305:// 剧毒之牙
					break;
				case 306:// 崩击之爪
					break;
				case 309:// 彗星拳
					break;
				case 310:// 恐吓
					break;
				case 317:// 岩石封
					break;
				case 325:// 暗影拳
					break;
				case 327:// 升空拳
					break;
				case 328:// 沙地狱
					break;
				case 331:// 种子机枪
					break;
				case 332:// 燕返
					break;
				case 333:// 冰柱针
					break;
				case 337:// 龙之爪
					break;
				case 340:// 飞跃
					break;
				case 342:// 毒尾
					break;
				case 343:// 索要
					break;
				case 344:// 高压电击
					break;
				case 348:// 刃叶斩
					break;
				case 350:// 岩石爆破
					break;
				case 358:// 清醒拍打
					break;
				case 359:// 臂锤
					break;
				case 360:// 螺旋球
					break;
				case 363:// 自然恩惠
					break;
				case 364:// 佯攻
					break;
				case 365:// 啄食
					break;
				case 368:// 金属爆破
					break;
				case 369:// 蜻蜓回转
					break;
				case 370:// 近战
					break;
				case 371:// 报复
					break;
				case 372:// 连打
					break;
				case 374:// 投掷
					break;
				case 386:// 惩罚
					break;
				case 387:// 最终手段
					break;
				case 389:// 偷袭
					break;
				case 394:// 火焰驱进
					break;
				case 395:// 发劲
					break;
				case 398:// 毒突
					break;
				case 400:// 试刀
					break;
				case 401:// 水之尾
					break;
				case 402:// 种子爆弹
					break;
				case 404:// 十字剪
					break;
				case 407:// 龙之冲锋
					break;
				case 409:// 吸取拳
					break;
				case 413:// 勇鸟
					break;
				case 416:// 亿万冲击
					break;
				case 418:// 子弹拳
					break;
				case 419:// 雪崩
					break;
				case 420:// 冰之砾
					break;
				case 421:// 阴影爪
					break;
				case 422:// 雷之牙
					break;
				case 423:// 冰之牙
					break;
				case 424:// 火之牙
					break;
				case 425:// 影击
					break;
				case 427:// 精神切割
					break;
				case 428:// 思念头槌
					break;
				case 431:// 攀岩
					break;
				case 438:// 强力鞭挞
					break;
				case 439:// 岩石炮
					break;
				case 440:// 毒十字
					break;
				case 441:// 粉尘射击
					break;
				case 442:// 铁头槌
					break;
				case 443:// 磁体炸弹
					break;
				case 444:// 石刃
					break;
				case 450:// 虫食
					break;
				case 452:// 木锤
					break;
				case 453:// 水流喷射
					break;
				case 454:// 攻击指令
					break;
				case 457:// 双刃头槌
					break;
				case 458:// 双重攻击
					break;
				case 462:// 捏碎
					break;
				case 467:// 影袭
					break;
				case 479:// 击坠
					break;
				case 480:// 山岚
					break;
				case 484:// 重量炸弹
					break;
				case 488:// 硝化冲锋
					break;
				case 490:// 下旋
					break;
				case 492:// 诈骗
					break;
				case 498:// 循序渐进
					break;
				case 507:// 自由下落
					break;
				case 509:// 仰投
					break;
				case 512:// 杂耍
					break;
				case 514:// 报仇
					break;
				case 523:// 压路
					break;
				case 525:// 龙之尾
					break;
				case 528:// 野性电击
					break;
				case 529:// 钻头直击
					break;
				case 530:// 连环切击
					break;
				case 531:// 心灵压迫
					break;
				case 532:// 木角
					break;
				case 533:// 神圣之剑
					break;
				case 534:// 贝壳刃
					break;
				case 535:// 热践踏
					break;
				case 537:// 坚硬滚动
					break;
				case 541:// 扫荡拍打
					break;
				case 543:// 阿弗罗撞击
					break;
				case 544:// 齿轮飞盘
					break;
				case 550:// 雷击
					break;
				case 553:// 冰结电击
					break;
				case 556:// 冰柱坠落
					break;
				case 557:// 创造胜利
					break;
				case 559:// 交织闪电
					break;
			}
		}
	}
}