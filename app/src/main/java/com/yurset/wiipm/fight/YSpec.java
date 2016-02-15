package com.yurset.wiipm.fight;

import com.yurset.wiipm.main.IGame;

public class YSpec {
	// 处理"特殊"
	public static void callEffect(int cate, int moveH, int moveZ) {
		int tempV = 0;
		if (cate == 0) {
			switch (XInfo.teamHMoveIndex[IGame.pmSeq][moveH]) {
				case 13:// 镰鼬风
					break;
				case 16:// 起风
					break;
				case 49:// 音波爆
					break;
				case 51:// 溶解液
					break;
				case 52:// 火苗
					break;
				case 53:// 火焰放射
					break;
				case 55:// 水枪
					break;
				case 56:// 水压
					break;
				case 57:// 冲浪
					break;
				case 58:// 冷冻光线
					break;
				case 59:// 暴风雪
					break;
				case 60:// 精神光线
					break;
				case 61:// 泡沫光线
					break;
				case 62:// 极光光线
					break;
				case 63:// 破坏光线
					break;
				case 71:// 吸取
					break;
				case 72:// 百万吸取
					break;
				case 76:// 太阳光线
					break;
				case 80:// 花瓣舞
					break;
				case 82:// 龙之怒
					break;
				case 83:// 火旋涡
					break;
				case 84:// 电击
					break;
				case 85:// 十万伏特
					break;
				case 87:// 雷电
					break;
				case 93:// 念力
					break;
				case 94:// 精神干扰
					break;
				case 101:// 黑夜魔影
					break;
				case 123:// 毒雾
					break;
				case 124:// 淤泥攻击
					break;
				case 126:// 大字火
					break;
				case 129:// 迅星
					break;
				case 138:// 食梦
					break;
				case 145:// 水泡
					break;
				case 149:// 精神波动
					break;
				case 161:// 三角攻击
					break;
				case 173:// 鼾声
					break;
				case 177:// 空中爆破
					break;
				case 181:// 细雪
					break;
				case 188:// 淤泥爆弹
					break;
				case 189:// 扔泥
					break;
				case 190:// 墨汁炮
					break;
				case 192:// 电磁炮
					break;
				case 196:// 冰冻之风
					break;
				case 202:// 亿万吸取
					break;
				case 225:// 龙之吐息
					break;
				case 237:// 觉醒力量
					break;
				case 239:// 龙卷风
					break;
				case 243:// 镜面反射
					break;
				case 246:// 原始力量
					break;
				case 247:// 阴影球
					break;
				case 248:// 预知未来
					break;
				case 250:// 漩涡
					break;
				case 253:// 吵闹
					break;
				case 255:// 能量释放
					break;
				case 257:// 热风
					break;
				case 284:// 喷火
					break;
				case 295:// 光栅净化
					break;
				case 296:// 迷雾球
					break;
				case 304:// 高级噪音
					break;
				case 307:// 爆裂燃烧
					break;
				case 308:// 水压加农
					break;
				case 311:// 气象球
					break;
				case 314:// 空气刃
					break;
				case 315:// 燃烧殆尽
					break;
				case 318:// 银色之风
					break;
				case 323:// 喷水
					break;
				case 324:// 信号光线
					break;
				case 326:// 神通力
					break;
				case 329:// 绝对零度
					break;
				case 330:// 浊流
					break;
				case 338:// 硬化植物
					break;
				case 341:// 泥浆喷射
					break;
				case 345:// 魔叶斩
					break;
				case 351:// 电击波
					break;
				case 352:// 水之波动
					break;
				case 353:// 破灭之愿
					break;
				case 354:// 精神增压
					break;
				case 362:// 潮水
					break;
				case 376:// 王牌
					break;
				case 378:// 绞紧
					break;
				case 396:// 波导弹
					break;
				case 399:// 恶之波动
					break;
				case 403:// 空气切割
					break;
				case 405:// 虫鸣
					break;
				case 406:// 龙之波动
					break;
				case 408:// 力量宝石
					break;
				case 410:// 真空波
					break;
				case 411:// 气合弹
					break;
				case 412:// 能量球
					break;
				case 414:// 大地之力
					break;
				case 426:// 泥爆弹
					break;
				case 429:// 镜面射击
					break;
				case 430:// 光栅加农
					break;
				case 434:// 龙星群
					break;
				case 435:// 放电
					break;
				case 436:// 喷烟
					break;
				case 437:// 飞叶风暴
					break;
				case 447:// 草绳结
					break;
				case 448:// 喋喋不休
					break;
				case 449:// 制裁之砾
					break;
				case 451:// 充电光线
					break;
				case 459:// 时之咆哮
					break;
				case 460:// 亚空切断
					break;
				case 463:// 熔岩风暴
					break;
				case 465:// 闪耀种子
					break;
				case 466:// 妖风
					break;
				case 473:// 精神冲击
					break;
				case 474:// 毒液冲击
					break;
				case 481:// 爆裂火焰
					break;
				case 482:// 淤泥波
					break;
				case 485:// 同调噪音
					break;
				case 486:// 电球
					break;
				case 491:// 酸性炸弹
					break;
				case 496:// 轮唱
					break;
				case 497:// 回音
					break;
				case 499:// 净化之雾
					break;
				case 500:// 援助力量
					break;
				case 503:// 沸水
					break;
				case 506:// 厄运
					break;
				case 510:// 燃尽
					break;
				case 515:// 豁命攻击
					break;
				case 517:// 炼狱
					break;
				case 518:// 水之誓
					break;
				case 519:// 火之誓
					break;
				case 520:// 草之誓
					break;
				case 521:// 闪电交替
					break;
				case 522:// 虫之抵抗
					break;
				case 524:// 冰之吐息
					break;
				case 527:// 电网
					break;
				case 536:// 草旋风
					break;
				case 539:// 暗夜爆破
					break;
				case 540:// 精神破坏
					break;
				case 542:// 暴风
					break;
				case 545:// 火焰弹
					break;
				case 546:// 科技爆破
					break;
				case 547:// 古代之歌
					break;
				case 548:// 神秘之剑
					break;
				case 549:// 冰封世界
					break;
				case 551:// 青色火焰
					break;
				case 552:// 火焰之舞
					break;
				case 554:// 冰冷闪光
					break;
				case 555:// 大喊
					break;
				case 558:// 交织火焰
					break;
			}
		} else if (cate == 1) {
			switch (XInfo.teamZMoveIndex[IGame.currPM][moveZ]) {
				case 13:// 镰鼬风
					break;
				case 16:// 起风
					break;
				case 49:// 音波爆
					break;
				case 51:// 溶解液
					break;
				case 52:// 火苗
					break;
				case 53:// 火焰放射
					break;
				case 55:// 水枪
					break;
				case 56:// 水压
					break;
				case 57:// 冲浪
					break;
				case 58:// 冷冻光线
					break;
				case 59:// 暴风雪
					break;
				case 60:// 精神光线
					break;
				case 61:// 泡沫光线
					break;
				case 62:// 极光光线
					break;
				case 63:// 破坏光线
					break;
				case 71:// 吸取
					break;
				case 72:// 百万吸取
					break;
				case 76:// 太阳光线
					break;
				case 80:// 花瓣舞
					break;
				case 82:// 龙之怒
					break;
				case 83:// 火旋涡
					break;
				case 84:// 电击
					break;
				case 85:// 十万伏特
					break;
				case 87:// 雷电
					break;
				case 93:// 念力
					break;
				case 94:// 精神干扰
					break;
				case 101:// 黑夜魔影
					break;
				case 123:// 毒雾
					break;
				case 124:// 淤泥攻击
					break;
				case 126:// 大字火
					break;
				case 129:// 迅星
					break;
				case 138:// 食梦
					break;
				case 145:// 水泡
					break;
				case 149:// 精神波动
					break;
				case 161:// 三角攻击
					break;
				case 173:// 鼾声
					break;
				case 177:// 空中爆破
					break;
				case 181:// 细雪
					break;
				case 188:// 淤泥爆弹
					break;
				case 189:// 扔泥
					break;
				case 190:// 墨汁炮
					break;
				case 192:// 电磁炮
					break;
				case 196:// 冰冻之风
					break;
				case 202:// 亿万吸取
					break;
				case 225:// 龙之吐息
					break;
				case 237:// 觉醒力量
					break;
				case 239:// 龙卷风
					break;
				case 243:// 镜面反射
					break;
				case 246:// 原始力量
					break;
				case 247:// 阴影球
					break;
				case 248:// 预知未来
					break;
				case 250:// 漩涡
					break;
				case 253:// 吵闹
					break;
				case 255:// 能量释放
					break;
				case 257:// 热风
					break;
				case 284:// 喷火
					break;
				case 295:// 光栅净化
					break;
				case 296:// 迷雾球
					break;
				case 304:// 高级噪音
					break;
				case 307:// 爆裂燃烧
					break;
				case 308:// 水压加农
					break;
				case 311:// 气象球
					break;
				case 314:// 空气刃
					break;
				case 315:// 燃烧殆尽
					break;
				case 318:// 银色之风
					break;
				case 323:// 喷水
					break;
				case 324:// 信号光线
					break;
				case 326:// 神通力
					break;
				case 329:// 绝对零度
					break;
				case 330:// 浊流
					break;
				case 338:// 硬化植物
					break;
				case 341:// 泥浆喷射
					break;
				case 345:// 魔叶斩
					break;
				case 351:// 电击波
					break;
				case 352:// 水之波动
					break;
				case 353:// 破灭之愿
					break;
				case 354:// 精神增压
					break;
				case 362:// 潮水
					break;
				case 376:// 王牌
					break;
				case 378:// 绞紧
					break;
				case 396:// 波导弹
					break;
				case 399:// 恶之波动
					break;
				case 403:// 空气切割
					break;
				case 405:// 虫鸣
					break;
				case 406:// 龙之波动
					break;
				case 408:// 力量宝石
					break;
				case 410:// 真空波
					break;
				case 411:// 气合弹
					break;
				case 412:// 能量球
					break;
				case 414:// 大地之力
					break;
				case 426:// 泥爆弹
					break;
				case 429:// 镜面射击
					break;
				case 430:// 光栅加农
					break;
				case 434:// 龙星群
					break;
				case 435:// 放电
					break;
				case 436:// 喷烟
					break;
				case 437:// 飞叶风暴
					break;
				case 447:// 草绳结
					break;
				case 448:// 喋喋不休
					break;
				case 449:// 制裁之砾
					break;
				case 451:// 充电光线
					break;
				case 459:// 时之咆哮
					break;
				case 460:// 亚空切断
					break;
				case 463:// 熔岩风暴
					break;
				case 465:// 闪耀种子
					break;
				case 466:// 妖风
					break;
				case 473:// 精神冲击
					break;
				case 474:// 毒液冲击
					break;
				case 481:// 爆裂火焰
					break;
				case 482:// 淤泥波
					break;
				case 485:// 同调噪音
					break;
				case 486:// 电球
					break;
				case 491:// 酸性炸弹
					break;
				case 496:// 轮唱
					break;
				case 497:// 回音
					break;
				case 499:// 净化之雾
					break;
				case 500:// 援助力量
					break;
				case 503:// 沸水
					break;
				case 506:// 厄运
					break;
				case 510:// 燃尽
					break;
				case 515:// 豁命攻击
					break;
				case 517:// 炼狱
					break;
				case 518:// 水之誓
					break;
				case 519:// 火之誓
					break;
				case 520:// 草之誓
					break;
				case 521:// 闪电交替
					break;
				case 522:// 虫之抵抗
					break;
				case 524:// 冰之吐息
					break;
				case 527:// 电网
					break;
				case 536:// 草旋风
					break;
				case 539:// 暗夜爆破
					break;
				case 540:// 精神破坏
					break;
				case 542:// 暴风
					break;
				case 545:// 火焰弹
					break;
				case 546:// 科技爆破
					break;
				case 547:// 古代之歌
					break;
				case 548:// 神秘之剑
					break;
				case 549:// 冰封世界
					break;
				case 551:// 青色火焰
					break;
				case 552:// 火焰之舞
					break;
				case 554:// 冰冷闪光
					break;
				case 555:// 大喊
					break;
				case 558:// 交织火焰
					break;
			}
		}
	}
}