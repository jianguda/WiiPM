package com.yurset.wiipm.fight;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.yurset.wiipm.logi.VData;
import com.yurset.wiipm.main.IGame;

public class XCost {
	private static int moveH = 0, moveZ = 0;
	private static float restrain[][] = {
			{ 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.5f, 1.0f, 0.0f, 0.5f, 1.0f, 1.0f,
					1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f },
			{ 2.0f, 1.0f, 0.5f, 0.5f, 1.0f, 2.0f, 0.5f, 0.0f, 2.0f, 1.0f, 1.0f,
					1.0f, 1.0f, 0.5f, 2.0f, 1.0f, 2.0f },
			{ 1.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.5f, 2.0f, 1.0f, 0.5f, 1.0f, 1.0f,
					2.0f, 0.5f, 1.0f, 1.0f, 1.0f, 1.0f },
			{ 1.0f, 1.0f, 1.0f, 0.5f, 0.5f, 0.5f, 1.0f, 0.5f, 0.0f, 1.0f, 1.0f,
					2.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f },
			{ 1.0f, 1.0f, 0.0f, 2.0f, 1.0f, 2.0f, 0.5f, 1.0f, 2.0f, 2.0f, 1.0f,
					0.5f, 2.0f, 1.0f, 1.0f, 1.0f, 1.0f },
			{ 1.0f, 0.5f, 2.0f, 1.0f, 0.5f, 1.0f, 2.0f, 1.0f, 0.5f, 2.0f, 1.0f,
					1.0f, 1.0f, 1.0f, 2.0f, 1.0f, 1.0f },
			{ 1.0f, 0.5f, 0.5f, 0.5f, 1.0f, 1.0f, 1.0f, 0.5f, 0.5f, 0.5f, 1.0f,
					2.0f, 1.0f, 2.0f, 1.0f, 1.0f, 2.0f },
			{ 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 2.0f, 0.5f, 1.0f, 1.0f,
					1.0f, 1.0f, 2.0f, 1.0f, 1.0f, 0.5f },
			{ 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 2.0f, 1.0f, 1.0f, 0.5f, 0.5f, 0.5f,
					1.0f, 0.5f, 1.0f, 2.0f, 1.0f, 1.0f },
			{ 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.5f, 2.0f, 1.0f, 2.0f, 0.5f, 0.5f,
					2.0f, 1.0f, 1.0f, 2.0f, 0.5f, 1.0f },
			{ 1.0f, 1.0f, 1.0f, 1.0f, 2.0f, 2.0f, 1.0f, 1.0f, 1.0f, 2.0f, 0.5f,
					0.5f, 1.0f, 1.0f, 1.0f, 0.5f, 1.0f },
			{ 1.0f, 1.0f, 0.5f, 0.5f, 2.0f, 2.0f, 0.5f, 1.0f, 0.5f, 0.5f, 2.0f,
					0.5f, 1.0f, 1.0f, 1.0f, 0.5f, 1.0f },
			{ 1.0f, 1.0f, 2.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 2.0f,
					0.5f, 0.5f, 1.0f, 1.0f, 0.5f, 1.0f },
			{ 1.0f, 2.0f, 1.0f, 2.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.5f, 1.0f, 1.0f,
					1.0f, 1.0f, 0.5f, 1.0f, 1.0f, 0.0f },
			{ 1.0f, 1.0f, 2.0f, 1.0f, 2.0f, 1.0f, 1.0f, 1.0f, 0.5f, 0.5f, 0.5f,
					2.0f, 1.0f, 1.0f, 0.5f, 2.0f, 1.0f },
			{ 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.5f, 1.0f, 1.0f,
					1.0f, 1.0f, 1.0f, 1.0f, 2.0f, 1.0f },
			{ 1.0f, 0.5f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 2.0f, 0.5f, 1.0f, 1.0f,
					1.0f, 1.0f, 2.0f, 1.0f, 1.0f, 0.5f } };

	private static List<String> typess = Arrays.asList("普", "斗", "飞", "毒", "地",
			"岩", "虫", "鬼", "钢", "火", "水", "草", "电", "超", "冰", "龙", "恶");

	private static List<String> states = Arrays.asList("濒死", "异常", "害怕", "混乱",
			"着迷", "能力等级变化", "蓄力", "束缚", "硬直", "蓄气", "克制", "变身", "替身",
			"蜘蛛网·黑眼神·禁止通行", "心眼·锁定", "诅咒", "保护·见切", "同旅", "忍耐", "鼓掌",
			"跟我来·愤怒之粉", "挑拨", "帮手", "魔装反射", "封印", "怨念", "液态圈", "扎根", "电磁浮游",
			"念动力", "击坠", "自由下落", "寄生种子", "扣押", "回复封印", "胃液", "灭亡之歌", "噩梦",
			"识破·嗅觉", "奇迹之眼", "寻衅", "残废", "哈欠", "烦恼种子", "单纯光线", "变为同伴", "力量平分",
			"防御平分", "力量戏法", "浸水", "保护色", "镜面属性", "属性切换", "属性切换2", "玩水", "玩泥",
			"变圆", "变小", "充电", "身体净化", "能量储存", "抢夺", "引火", "许愿", "预知未来/破灭之愿",
			"治愈之愿", "新月之舞", "白雾", "光之壁", "反射盾", "神秘守护", "顺风", "咒语", "撒菱", "毒菱",
			"秘密岩石", "广域防御", "快速防御", "火海", "湿原", "彩虹", "天气", "重力", "欺骗空间",
			"神奇空间", "魔术空间");

	private boolean stateBool[] = new boolean[86];

	private static List<String> ability = Arrays.asList("恶臭", "降雨", "加速",
			"战斗盔甲", "坚硬", "潮湿", "柔软", "沙隐", "静电", "蓄电", "贮水", "钝感", "无天气",
			"复眼", "不眠", "变色", "免疫", "引火", "鳞粉", "自我中心", "吸盘", "威吓", "踩影",
			"鲨鱼皮", "奇异守护", "浮游", "孢子", "同步率", "净体", "自然回复", "避雷针", "天之恩惠",
			"轻快", "叶绿素", "发光", "复制", "大力士", "毒刺", "精神力", "熔岩铠甲", "水之掩护", "磁力",
			"防音", "接雨盘", "起沙", "压力", "厚脂肪", "早起", "火焰之躯", "逃足", "锐利目光", "怪力钳",
			"拾取", "懒惰", "紧张", "魅惑身躯", "正极", "负极", "气象台", "黏着", "蜕皮", "根性",
			"神秘鳞片", "毒液", "深绿", "猛火", "激流", "虫之预感", "石脑", "干旱", "蚁地狱", "干劲",
			"白烟", "瑜珈之力", "贝壳盔甲", "天气锁", "蹒跚", "电引擎", "斗争心", "不屈之心", "雪隐",
			"贪吃", "怒穴", "轻身", "耐热", "单纯", "干燥肌肤", "下载", "铁拳", "毒疗", "适应力",
			"技能连锁", "湿润身躯", "太阳力量", "早足", "普通皮肤", "狙击手", "魔法守护", "无防御", "后出",
			"技师", "绿叶守护", "不器用", "破格", "强运", "引爆", "危险预知", "预知梦", "天然", "有色眼镜",
			"过滤器", "缓慢启动", "胆气", "引水", "寒冰身躯", "坚岩", "降雪", "集蜜", "洞察", "舍身",
			"多重属性", "花之礼物", "梦魇", "偷盗恶习", "全力攻击", "性情乖僻", "紧张感", "不服输", "懦弱",
			"诅咒身躯", "治愈之心", "队友守护", "破碎铠甲", "重金属", "轻金属", "多重鳞片", "毒暴走", "热暴走",
			"收获", "超感知觉", "心意不定", "防尘", "毒手", "再生力", "胸甲", "挖沙", "奇迹皮肤", "分析",
			"幻影", "替代物", "穿透", "木乃伊", "自信过剩", "正义之心", "颤抖", "魔法反射", "食草",
			"恶作剧之心", "沙之力量", "铁棘", "不倒翁模式", "胜利之星", "涡轮火花", "垓级电压");

	public static int callPsysDamage(int cate, int moveH, int moveZ) {
		XCost.moveH = moveH;
		XCost.moveZ = moveZ;
		// 伤害 = ((((攻击方等级*2/5+2)*技能威力*攻击力/防御力)/50)+2)*修正
		int level = 0;
		int attack = 0;
		int defense = 0;
		int power = 0;
		int moveIndex = 0;

		if (cate == 0) {
			// 我方给予对方的伤害
			level = XInfo.teamHLevel[IGame.pmSeq];
			moveIndex = XInfo.teamHMoveIndex[IGame.pmSeq][moveH];
			if (moveIndex == 492) {
				attack = VData.xBattle.zTeamV[IGame.currPM][1];
			} else {
				attack = VData.xBattle.hTeamV[IGame.pmSeq][1];
			}
			defense = VData.xBattle.zTeamV[IGame.currPM][2];
			if (!XInfo.teamZAbility[IGame.currPM].equals("天然")) {
				// 进行攻防能力等级修正
				if (XBattle.hTeamS[1] > 0) {
					attack = attack * (2 + XBattle.hTeamS[1]) / 2;
				} else if (XBattle.hTeamS[1] < 0) {
					attack = attack * 2 / (2 - XBattle.hTeamS[1]);
				}
				if (XBattle.zTeamS[2] > 0) {
					defense = defense * (2 + XBattle.zTeamS[2]) / 2;
				} else if (XBattle.zTeamS[2] < 0) {
					defense = defense * 2 / (2 - XBattle.zTeamS[2]);
				}
			}
			attack = callAttack(cate, attack);
			defense = callDefense(cate, defense);
			power = callPower(cate, moveIndex,
					XInfo.teamHMovePower[IGame.pmSeq][moveH]);
		} else if (cate == 1) {
			// 对方给予我方的伤害
			level = XInfo.teamZLevel[IGame.currPM];
			moveIndex = XInfo.teamZMoveIndex[IGame.currPM][moveZ];
			if (moveIndex == 492) {
				attack = VData.xBattle.hTeamV[IGame.pmSeq][1];
			} else {
				attack = VData.xBattle.zTeamV[IGame.currPM][1];
			}
			defense = VData.xBattle.hTeamV[IGame.pmSeq][2];
			if (!XInfo.teamHAbility[IGame.pmSeq].equals("天然")) {
				// 攻防能力等级修正
				if (XBattle.zTeamS[1] > 0) {
					attack = attack * (2 + XBattle.zTeamS[1]) / 2;
				} else if (XBattle.zTeamS[1] < 0) {
					attack = attack * 2 / (2 - XBattle.zTeamS[1]);
				}
				if (XBattle.hTeamS[2] > 0) {
					defense = defense * (2 + XBattle.hTeamS[2]) / 2;
				} else if (XBattle.hTeamS[2] < 0) {
					defense = defense * 2 / (2 - XBattle.hTeamS[2]);
				}
			}
			attack = callAttack(cate, attack);
			defense = callDefense(cate, defense);
			power = callPower(cate, moveIndex,
					XInfo.teamZMovePower[IGame.currPM][moveZ]);
		}
		float baseModify = callBaseModify(cate);
		// float actualModify = callActualModify(cate);
		return (int) (((((level * 2 / 5 + 2) * power * attack / defense) / 50) + 2) * baseModify);
	}

	public static int callSpecDamage(int cate, int moveH, int moveZ) {
		XCost.moveH = moveH;
		XCost.moveZ = moveZ;
		// 伤害 = ((((攻击方等级*2/5+2)*技能威力*攻击力/防御力)/50)+2)*修正
		int level = 0;
		int attack = 0;
		int defense = 0;
		int power = 0;
		int moveIndex = 0;

		if (cate == 0) {
			// 我方给予对方的伤害
			level = XInfo.teamHLevel[IGame.pmSeq];
			moveIndex = XInfo.teamHMoveIndex[IGame.pmSeq][moveH];
			if (moveIndex == 492) {
				attack = VData.xBattle.zTeamV[IGame.currPM][1];
			} else {
				attack = VData.xBattle.hTeamV[IGame.pmSeq][4];
			}
			defense = VData.xBattle.zTeamV[IGame.currPM][5];
			if (!XInfo.teamZAbility[IGame.currPM].equals("天然")) {
				// 进行攻防能力等级修正
				if (XBattle.hTeamS[3] > 0) {
					attack = attack * (2 + XBattle.hTeamS[3]) / 2;
				} else if (XBattle.hTeamS[3] < 0) {
					attack = attack * 2 / (2 - XBattle.hTeamS[3]);
				}
				if (XBattle.zTeamS[4] > 0) {
					defense = defense * (2 + XBattle.zTeamS[4]) / 2;
				} else if (XBattle.zTeamS[4] < 0) {
					defense = defense * 2 / (2 - XBattle.zTeamS[4]);
				}
			}
			attack = callAttack(cate, attack);
			defense = callDefense(cate, defense);
			power = callPower(cate, moveIndex,
					XInfo.teamHMovePower[IGame.pmSeq][moveH]);
		} else if (cate == 1) {
			// 对方给予我方的伤害
			level = XInfo.teamZLevel[IGame.currPM];
			moveIndex = XInfo.teamZMoveIndex[IGame.currPM][moveZ];
			if (moveIndex == 492) {
				attack = VData.xBattle.hTeamV[IGame.pmSeq][1];
			} else {
				attack = VData.xBattle.zTeamV[IGame.currPM][4];
			}
			defense = VData.xBattle.hTeamV[IGame.pmSeq][5];
			if (!XInfo.teamHAbility[IGame.pmSeq].equals("天然")) {
				// 攻防能力等级修正
				if (XBattle.zTeamS[3] > 0) {
					attack = attack * (2 + XBattle.zTeamS[3]) / 2;
				} else if (XBattle.zTeamS[3] < 0) {
					attack = attack * 2 / (2 - XBattle.zTeamS[3]);
				}
				if (XBattle.hTeamS[4] > 0) {
					defense = defense * (2 + XBattle.hTeamS[4]) / 2;
				} else if (XBattle.hTeamS[4] < 0) {
					defense = defense * 2 / (2 - XBattle.hTeamS[4]);
				}
			}
			attack = callAttack(cate, attack);
			defense = callDefense(cate, defense);
			power = callPower(cate, moveIndex,
					XInfo.teamZMovePower[IGame.currPM][moveZ]);
		}
		float baseModify = callBaseModify(cate);
		// float actualModify = callActualModify(cate);
		return (int) (((((level * 2 / 5 + 2) * power * attack / defense) / 50) + 2) * baseModify);
	}

	private static int callAttack(int cate, int valueAttack) {
		float modify = 1.0f;
		if (cate == 0) {
			if (XInfo.teamZAbility[IGame.currPM].equals("厚脂肪")
					&& (XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("火") || (XInfo.teamHMoveType[IGame.pmSeq][moveH]
					.equals("冰")))) {
				modify *= 0.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("深绿")
					&& XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("草")
					&& 3 * XBattle.hTeamHP[IGame.pmSeq] <= XBattle.hTeamV[IGame.pmSeq][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("猛火")
					&& XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("火")
					&& 3 * XBattle.hTeamHP[IGame.pmSeq] <= XBattle.hTeamV[IGame.pmSeq][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("激流")
					&& XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("水")
					&& 3 * XBattle.hTeamHP[IGame.pmSeq] <= XBattle.hTeamV[IGame.pmSeq][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("虫之警报")
					&& XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("虫")
					&& 3 * XBattle.hTeamHP[IGame.pmSeq] <= XBattle.hTeamV[IGame.pmSeq][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("根性")
					&& XBattle.abnormalH[IGame.pmSeq] != 0) {
				modify *= 1.5f;
			}
			// 在双打对战或三打对战中，如果攻击方拥有正极或负极特性且攻击方的队友拥有正极或负极特性，且使用特殊技能，攻击力修正×1.5。
			if (XInfo.teamHAbility[IGame.pmSeq].equals("懦弱")
					&& 2 * XBattle.hTeamHP[IGame.pmSeq] <= XBattle.hTeamV[IGame.pmSeq][0]) {
				modify *= 0.5f;
			}
			if ((XInfo.teamHAbility[IGame.pmSeq].equals("瑜伽之力") || XInfo.teamHAbility[IGame.pmSeq]
					.equals("大力士"))
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")) {
				modify *= 2.0f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("太阳力量")
					&& XBattle.weather == 1
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("紧张")
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("引火")
					&& XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("火")) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("缓慢启动")
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")) {
				modify *= 0.5f;
			}
			if (XBattle.weather == 1
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")
					&& XInfo.teamZAbility[IGame.currPM].equals("花之礼物")) {
				modify *= 1.5f;
			}
			// 如果攻击方是携带粗骨头的可拉可拉或嘎拉嘎拉，且使用物理技能，攻击力修正×2。
			// 如果攻击方是携带深海之牙的珍珠贝，且使用特殊技能，攻击力修正×2。
			// 如果攻击方是携带电珠的皮卡丘，攻击力修正×2。
			// 如果攻击方是携带心之水珠的拉帝欧斯或拉帝亚斯，且使用特殊技能，攻击力修正×1.5。
			// 如果攻击方携带专爱头巾，且使用物理技能，攻击力修正×1.5。
			// 如果攻击方携带专爱眼镜，且使用特殊技能，攻击力修正×1.5。
		} else if (cate == 1) {
			if (XInfo.teamHAbility[IGame.pmSeq].equals("厚脂肪")
					&& (XInfo.teamZMoveType[IGame.currPM][moveZ].equals("火") || XInfo.teamZMoveType[IGame.currPM][moveZ]
					.equals("冰"))) {
				modify *= 0.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("深绿")
					&& XInfo.teamZMoveType[IGame.currPM][moveZ].equals("草")
					&& 3 * XBattle.zTeamHP[IGame.currPM] <= XBattle.zTeamV[IGame.currPM][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("猛火")
					&& XInfo.teamZMoveType[IGame.currPM][moveZ].equals("火")
					&& 3 * XBattle.zTeamHP[IGame.currPM] <= XBattle.zTeamV[IGame.currPM][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("激流")
					&& XInfo.teamZMoveType[IGame.currPM][moveZ].equals("水")
					&& 3 * XBattle.zTeamHP[IGame.currPM] <= XBattle.zTeamV[IGame.currPM][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("虫之警报")
					&& XInfo.teamZMoveType[IGame.currPM][moveZ].equals("虫")
					&& 3 * XBattle.zTeamHP[IGame.currPM] <= XBattle.zTeamV[IGame.currPM][0]) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("根性")
					&& XBattle.abnormalZ[IGame.currPM] != 0) {
				modify *= 1.5f;
			}
			// 在双打对战或三打对战中，如果攻击方拥有正极或负极特性且攻击方的队友拥有正极或负极特性，且使用特殊技能，攻击力修正×1.5。
			if (XInfo.teamZAbility[IGame.currPM].equals("懦弱")
					&& 2 * XBattle.zTeamHP[IGame.currPM] <= XBattle.zTeamV[IGame.currPM][0]) {
				modify *= 1.5f;
			}
			if ((XInfo.teamZAbility[IGame.currPM].equals("瑜伽之力") || XInfo.teamZAbility[IGame.currPM]
					.equals("大力士"))
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")) {
				modify *= 2.0f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("太阳力量")
					&& XBattle.weather == 1
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("紧张")
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("引火")
					&& XInfo.teamZMoveType[IGame.currPM][moveZ].equals("火")) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("缓慢启动")
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")) {
				modify *= 0.5f;
			}
			if (XBattle.weather == 1
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")
					&& XInfo.teamHAbility[IGame.pmSeq].equals("花之礼物")) {
				modify *= 1.5f;
			}
			// 如果攻击方是携带粗骨头的可拉可拉或嘎拉嘎拉，且使用物理技能，攻击力修正×2。
			// 如果攻击方是携带深海之牙的珍珠贝，且使用特殊技能，攻击力修正×2。
			// 如果攻击方是携带电珠的皮卡丘，攻击力修正×2。
			// 如果攻击方是携带心之水珠的拉帝欧斯或拉帝亚斯，且使用特殊技能，攻击力修正×1.5。
			// 如果攻击方携带专爱头巾，且使用物理技能，攻击力修正×1.5。
			// 如果攻击方携带专爱眼镜，且使用特殊技能，攻击力修正×1.5。
		}
		return (int) (valueAttack * modify);
	}

	private static int callDefense(int cate, int valueDefense) {
		float modify = 1.0f;
		if (cate == 0) {
			if (XBattle.weather == 3
					&& (XInfo.teamZType[IGame.currPM][0].equals("岩") || XInfo.teamZType[IGame.currPM][1]
					.equals("岩"))
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("花之礼物")
					&& XBattle.weather == 1
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("神秘鳞片")
					&& XBattle.abnormalZ[IGame.currPM] != 0
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")) {
				modify *= 1.5f;
			}
			// 如果防御方是携带深海之鳞的珍珠贝，且技能是特殊技能，防御力修正×2。
			// 如果防御方是携带金属粉末的百变怪，且技能是物理技能，防御力修正×2。
			// 如果防御方是携带心之水珠的拉帝欧斯或拉帝亚斯，且技能是特殊技能，防御力修正×1.5。
			// 如果防御方携带进化辉石，且防御方拥有进化型，防御力修正×1.5。
		} else if (cate == 1) {
			if (XBattle.weather == 3
					&& (XInfo.teamHType[IGame.pmSeq][0].equals("岩") || XInfo.teamHType[IGame.pmSeq][1]
					.equals("岩"))
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("花之礼物")
					&& XBattle.weather == 1
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("神秘鳞片")
					&& XBattle.abnormalH[IGame.pmSeq] != 0
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")) {
				modify *= 1.5f;
			}
			// 如果防御方是携带深海之鳞的珍珠贝，且技能是特殊技能，防御力修正×2。
			// 如果防御方是携带金属粉末的百变怪，且技能是物理技能，防御力修正×2。
			// 如果防御方是携带心之水珠的拉帝欧斯或拉帝亚斯，且技能是特殊技能，防御力修正×1.5。
			// 如果防御方携带进化辉石，且防御方拥有进化型，防御力修正×1.5。
		}
		return (int) (valueDefense * modify);
	}

	private static int callPower(int cate, int moveIndex, int movePower) {
		float modify = 1.0f;
		if (cate == 0) {
			if (XInfo.teamHAbility[IGame.pmSeq].equals("技师") && movePower <= 60) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("热暴走")
					&& XBattle.abnormalH[IGame.pmSeq] == 1
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("毒暴走")
					&& XBattle.abnormalH[IGame.pmSeq] == 4
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")) {
				modify *= 1.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("分析")
					&& (moveIndex != 248 && moveIndex != 353)
					&& (XBattle.priH < XBattle.priZ || XBattle.priH == XBattle.priZ
					&& XBattle.hTeamV[IGame.pmSeq][3] < XBattle.zTeamV[IGame.currPM][3])) {
				modify *= 1.3f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("舍身")
					&& (moveIndex == 26 || moveIndex == 36 || moveIndex == 38
					|| moveIndex == 66 || moveIndex == 136
					|| moveIndex == 344 || moveIndex == 394
					|| moveIndex == 413 || moveIndex == 452 || moveIndex == 457)) {
				modify *= 1.2f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("铁拳")
					&& (moveIndex == 4 || moveIndex == 5 || moveIndex == 7
					|| moveIndex == 8 || moveIndex == 9
					|| moveIndex == 146 || moveIndex == 183
					|| moveIndex == 223 || moveIndex == 264
					|| moveIndex == 309 || moveIndex == 325
					|| moveIndex == 327 || moveIndex == 359
					|| moveIndex == 409 || moveIndex == 418)) {
				modify *= 1.2f;
			}
			// 如果攻击方拥有斗争心特性，当攻击方与防御方性别相同时，威力修正×1.25；当攻击方与防御方性别相反时，威力修正×0.75。如果攻击方或防御方没有性别，跳过此步骤。
			if (XInfo.teamHAbility[IGame.pmSeq].equals("沙之力量")
					&& (XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("岩")
					|| XInfo.teamHMoveType[IGame.pmSeq][moveH]
					.equals("钢") || XInfo.teamHMoveType[IGame.pmSeq][moveH]
					.equals("地"))) {
				modify *= 1.3f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("耐热")
					&& (XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("火"))) {
				modify *= 0.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("干燥肌肤")
					&& (XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("火"))) {
				modify *= 1.25f;
			}
			// 如果攻击方拥有全力攻击特性，且技能带有正面追加效果，威力修正×1.3。
			// 如果攻击方携带属性强化道具，且技能是对应属性，威力修正×1.2。
			// 如果攻击方携带力量头巾，且使用物理技能，威力修正×1.1。
			// 如果攻击方携带知识眼镜，且使用特殊技能，威力修正×1.1。
			// 如果攻击方携带怪异之香，且技能是超能属性，威力修正×1.2。
			// 如果攻击方是携带金刚玉的帝牙卢卡，且技能是钢或龙属性，威力修正×1.2。
			// 如果攻击方是携带白玉的帕路奇犽，且技能是水或龙属性，威力修正×1.2。
			// 如果攻击方是携带白金玉的骑拉帝纳，且技能是鬼或龙属性，威力修正×1.2。
			// 如果此次攻击发动了对应属性宝石，威力修正×1.5。
			if (moveIndex == 362
					&& 2 * XBattle.zTeamHP[IGame.currPM] <= XBattle.zTeamV[IGame.currPM][0]) {
				modify *= 2.0f;
			}
			if (moveIndex == 474 && XBattle.abnormalZ[IGame.currPM] == 4) {
				modify *= 2.0f;
			}
			// 如果技能是报仇，且攻击方的队伍上回合有精灵濒死，威力修正×2。
			// 如果技能是交织火焰，且回合内上一个成功使用的技能是交织闪电，威力修正×2，反之亦然。
			if (moveIndex == 382) {
				modify *= 1.5f;
			}
			if (moveIndex == 76
					&& (XBattle.weather == 2 || XBattle.weather == 3 || XBattle.weather == 4)) {
				if (!(XInfo.teamHAbility[IGame.pmSeq].equals("天气锁")
						|| XInfo.teamHAbility[IGame.pmSeq].equals("无天气")
						|| XInfo.teamZAbility[IGame.currPM].equals("天气锁") || XInfo.teamZAbility[IGame.currPM]
						.equals("无天气"))) {
					modify *= 0.5f;
				}
			}
			// 如果攻击方处于充电状态，且技能是电属性，威力修正×2。
			// 如果攻击方处于帮手状态，威力修正×1.5。
			// 如果场上存在玩水状态，且攻击方是火属性，威力修正×0.5。
			// 如果场上存在玩泥状态，且攻击方是电属性，威力修正×0.5。
		} else if (cate == 1) {
			if (XInfo.teamZAbility[IGame.currPM].equals("技师")
					&& movePower <= 60) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("热暴走")
					&& XBattle.abnormalZ[IGame.currPM] == 1
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("特殊")) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("毒暴走")
					&& XBattle.abnormalZ[IGame.currPM] == 4
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")) {
				modify *= 1.5f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("分析")
					&& (moveIndex != 248 && moveIndex != 353)
					&& (XBattle.priH > XBattle.priZ || XBattle.priH == XBattle.priZ
					&& XBattle.hTeamV[IGame.pmSeq][3] >= XBattle.zTeamV[IGame.currPM][3])) {
				modify *= 1.3f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("舍身")
					&& (moveIndex == 26 || moveIndex == 36 || moveIndex == 38
					|| moveIndex == 66 || moveIndex == 136
					|| moveIndex == 344 || moveIndex == 394
					|| moveIndex == 413 || moveIndex == 452 || moveIndex == 457)) {
				modify *= 1.2f;
			}
			if (XInfo.teamZAbility[IGame.currPM].equals("铁拳")
					&& (moveIndex == 4 || moveIndex == 5 || moveIndex == 7
					|| moveIndex == 8 || moveIndex == 9
					|| moveIndex == 146 || moveIndex == 183
					|| moveIndex == 223 || moveIndex == 264
					|| moveIndex == 309 || moveIndex == 325
					|| moveIndex == 327 || moveIndex == 359
					|| moveIndex == 409 || moveIndex == 418)) {
				modify *= 1.2f;
			}
			// 如果攻击方拥有斗争心特性，当攻击方与防御方性别相同时，威力修正×1.25；当攻击方与防御方性别相反时，威力修正×0.75。如果攻击方或防御方没有性别，跳过此步骤。
			if (XInfo.teamZAbility[IGame.currPM].equals("沙之力量")
					&& (XInfo.teamZMoveType[IGame.currPM][moveZ].equals("岩")
					|| XInfo.teamZMoveType[IGame.currPM][moveZ]
					.equals("钢") || XInfo.teamZMoveType[IGame.currPM][moveZ]
					.equals("地"))) {
				modify *= 1.3f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("耐热")
					&& (XInfo.teamZMoveType[IGame.currPM][moveZ].equals("火"))) {
				modify *= 0.5f;
			}
			if (XInfo.teamHAbility[IGame.pmSeq].equals("干燥肌肤")
					&& (XInfo.teamZMoveType[IGame.currPM][moveZ].equals("火"))) {
				modify *= 1.25f;
			}
			// 如果攻击方拥有全力攻击特性，且技能带有正面追加效果，威力修正×1.3。
			// 如果攻击方携带属性强化道具，且技能是对应属性，威力修正×1.2。
			// 如果攻击方携带力量头巾，且使用物理技能，威力修正×1.1。
			// 如果攻击方携带知识眼镜，且使用特殊技能，威力修正×1.1。
			// 如果攻击方携带怪异之香，且技能是超能属性，威力修正×1.2。
			// 如果攻击方是携带金刚玉的帝牙卢卡，且技能是钢或龙属性，威力修正×1.2。
			// 如果攻击方是携带白玉的帕路奇犽，且技能是水或龙属性，威力修正×1.2。
			// 如果攻击方是携带白金玉的骑拉帝纳，且技能是鬼或龙属性，威力修正×1.2。
			// 如果此次攻击发动了对应属性宝石，威力修正×1.5。
			if (moveIndex == 362
					&& 2 * XBattle.hTeamHP[IGame.pmSeq] <= XBattle.hTeamV[IGame.pmSeq][0]) {
				modify *= 2.0f;
			}
			if (moveIndex == 474 && XBattle.abnormalH[IGame.pmSeq] == 4) {
				modify *= 2.0f;
			}
			// 如果技能是报仇，且攻击方的队伍上回合有精灵濒死，威力修正×2。
			// 如果技能是交织火焰，且回合内上一个成功使用的技能是交织闪电，威力修正×2，反之亦然。
			if (moveIndex == 382) {
				modify *= 1.5f;
			}
			if (moveIndex == 76
					&& (XBattle.weather == 2 || XBattle.weather == 3 || XBattle.weather == 4)) {
				if (!(XInfo.teamHAbility[IGame.pmSeq].equals("天气锁")
						|| XInfo.teamHAbility[IGame.pmSeq].equals("无天气")
						|| XInfo.teamZAbility[IGame.currPM].equals("天气锁") || XInfo.teamZAbility[IGame.currPM]
						.equals("无天气"))) {
					modify *= 0.5f;
				}
			}
			// 如果攻击方处于充电状态，且技能是电属性，威力修正×2。
			// 如果攻击方处于帮手状态，威力修正×1.5。
			// 如果场上存在玩水状态，且攻击方是火属性，威力修正×0.5。
			// 如果场上存在玩泥状态，且攻击方是电属性，威力修正×0.5。
		}
		return (int) (movePower * modify);
	}

	private static float callBaseModify(int cate) {
		float modify = 1.0f;

		// 在双打对战或三打对战中，如果技能范围内有多只精灵，目标修正×0.75。

		if (XBattle.weather == 1) {
			if (!(XInfo.teamHAbility[IGame.pmSeq].equals("天气锁")
					|| XInfo.teamHAbility[IGame.pmSeq].equals("无天气")
					|| XInfo.teamZAbility[IGame.currPM].equals("天气锁") || XInfo.teamZAbility[IGame.currPM]
					.equals("无天气"))) {
				if (cate == 0) {
					if (XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("火")) {
						modify *= 1.5f;
					} else if (XInfo.teamHMoveType[IGame.pmSeq][moveH]
							.equals("水")) {
						modify *= 0.5f;
					}
				} else if (cate == 1) {
					if (XInfo.teamZMoveType[IGame.currPM][moveZ].equals("火")) {
						modify *= 1.5f;
					} else if (XInfo.teamZMoveType[IGame.currPM][moveZ]
							.equals("水")) {
						modify *= 0.5f;
					}
				}
			}
		} else if (XBattle.weather == 2) {
			if (!(XInfo.teamHAbility[IGame.pmSeq].equals("天气锁")
					|| XInfo.teamHAbility[IGame.pmSeq].equals("无天气")
					|| XInfo.teamZAbility[IGame.currPM].equals("天气锁") || XInfo.teamZAbility[IGame.currPM]
					.equals("无天气"))) {
				if (cate == 0) {
					if (XInfo.teamHMoveType[IGame.pmSeq][moveH].equals("水")) {
						modify *= 1.5f;
					} else if (XInfo.teamHMoveType[IGame.pmSeq][moveH]
							.equals("火")) {
						modify *= 0.5f;
					}
				} else if (cate == 1) {
					if (XInfo.teamZMoveType[IGame.currPM][moveZ].equals("水")) {
						modify *= 1.5f;
					} else if (XInfo.teamZMoveType[IGame.currPM][moveZ]
							.equals("火")) {
						modify *= 0.5f;
					}
				}
			}
		}

		// 如果发生会心一击，伤害＝伤害×2。

		modify *= ((new Random().nextInt(16) + 85) / 100.0f);

		if (cate == 0) {
			if (XInfo.teamHMoveType[IGame.pmSeq][moveH]
					.equals(XInfo.teamHType[IGame.pmSeq][0])
					|| XInfo.teamHMoveType[IGame.pmSeq][moveH]
					.equals(XInfo.teamHType[IGame.pmSeq][1])) {
				if (XInfo.teamHAbility[IGame.pmSeq].equals("适应力")) {
					modify *= 2.0f;
				} else {
					modify *= 1.5f;
				}
			}
			if (XInfo.teamZType[IGame.currPM][0]
					.equals(XInfo.teamZType[IGame.currPM][1])) {
				modify *= restrain[typess
						.indexOf(XInfo.teamHMoveType[IGame.pmSeq][moveH])][typess
						.indexOf(XInfo.teamZType[IGame.currPM][0])];
			} else {
				modify *= (restrain[typess
						.indexOf(XInfo.teamHMoveType[IGame.pmSeq][moveH])][typess
						.indexOf(XInfo.teamZType[IGame.currPM][0])] * restrain[typess
						.indexOf(XInfo.teamHMoveType[IGame.pmSeq][moveH])][typess
						.indexOf(XInfo.teamZType[IGame.currPM][1])]);
			}
		} else if (cate == 1) {
			if (XInfo.teamZMoveType[IGame.currPM][moveZ]
					.equals(XInfo.teamZType[IGame.currPM][0])
					|| XInfo.teamZMoveType[IGame.currPM][moveZ]
					.equals(XInfo.teamZType[IGame.currPM][1])) {
				if (XInfo.teamZAbility[IGame.currPM].equals("适应力")) {
					modify *= 2.0f;
				} else {
					modify *= 1.5f;
				}
			}
			if (XInfo.teamHType[IGame.pmSeq][0]
					.equals(XInfo.teamHType[IGame.pmSeq][1])) {
				modify *= restrain[typess
						.indexOf(XInfo.teamZMoveType[IGame.currPM][moveZ])][typess
						.indexOf(XInfo.teamHType[IGame.pmSeq][0])];
			} else {
				modify *= (restrain[typess
						.indexOf(XInfo.teamZMoveType[IGame.currPM][moveZ])][typess
						.indexOf(XInfo.teamHType[IGame.pmSeq][0])] * restrain[typess
						.indexOf(XInfo.teamZMoveType[IGame.currPM][moveZ])][typess
						.indexOf(XInfo.teamHType[IGame.pmSeq][1])]);
			}
		}

		if (cate == 0) {
			if (XBattle.abnormalH[IGame.pmSeq] == 1
					&& XInfo.teamHMoveCate[IGame.pmSeq][moveH].equals("物理")
					&& !XInfo.teamHAbility[IGame.pmSeq].equals("根性")) {
				modify *= 0.5f;
			}
		} else if (cate == 1) {
			if (XBattle.abnormalZ[IGame.currPM] == 1
					&& XInfo.teamZMoveCate[IGame.currPM][moveZ].equals("物理")
					&& !XInfo.teamZAbility[IGame.currPM].equals("根性")) {
				modify *= 0.5f;
			}
		}
		return modify;
	}

	// private static float callActualModify(int cate) {
	// float modify = 1.0f;
	//
	// if (cate == 0) {
	// //
	// 如果防御方场上存在反射盾，技能是物理技能，且未出现会心一击，如果防御方场上只有一只精灵，伤害修正×0.5；如果防御方场上有多只精灵，伤害修正×2/3。
	// //
	// 如果防御方场上存在光之壁，技能是特殊技能，且未出现会心一击，如果防御方场上只有一只精灵，伤害修正×0.5；如果防御方场上有多只精灵，伤害修正×2/3。
	// // 如果防御方拥有多重鳞片特性，且防御方HP全满，伤害修正×0.5。
	// // 如果攻击方拥有有色眼镜特性，防御方抵抗技能属性，伤害修正×2。
	// // 如果防御方的队友拥有队友守护特性，伤害修正×0.75。
	// // 如果攻击方拥有狙击手特性，且发生会心一击，伤害修正×1.5。
	// // 如果防御方拥有坚岩或过滤器特性，且技能克制防御方，伤害修正×0.75。
	// // 如果攻击方携带节拍器，伤害修正×Min（1＋0.2×（技能连续使用次数－1）,2）。
	// // 如果攻击方携带达人腰带，且技能克制防御方，伤害修正×1.2。
	// // 如果攻击方携带生命之玉，伤害修正×1.3。
	// // 如果防御方携带属性抗性树果，且攻击方的技能是对应属性并克制防御方，伤害修正×0.5。
	// // 如果技能是践踏或坚硬滚动，且防御方处于变小状态，伤害修正×2。
	// // 如果技能是地震，且防御方处于挖洞状态，伤害修正×2。
	// // 如果技能是冲浪，且防御方处于潜水状态，伤害修正×2。
	// } else if (cate == 1) {
	// //
	// 如果防御方场上存在反射盾，技能是物理技能，且未出现会心一击，如果防御方场上只有一只精灵，伤害修正×0.5；如果防御方场上有多只精灵，伤害修正×2/3。
	// //
	// 如果防御方场上存在光之壁，技能是特殊技能，且未出现会心一击，如果防御方场上只有一只精灵，伤害修正×0.5；如果防御方场上有多只精灵，伤害修正×2/3。
	// // 如果防御方拥有多重鳞片特性，且防御方HP全满，伤害修正×0.5。
	// // 如果攻击方拥有有色眼镜特性，防御方抵抗技能属性，伤害修正×2。
	// // 如果防御方的队友拥有队友守护特性，伤害修正×0.75。
	// // 如果攻击方拥有狙击手特性，且发生会心一击，伤害修正×1.5。
	// // 如果防御方拥有坚岩或过滤器特性，且技能克制防御方，伤害修正×0.75。
	// // 如果攻击方携带节拍器，伤害修正×Min（1＋0.2×（技能连续使用次数－1）,2）。
	// // 如果攻击方携带达人腰带，且技能克制防御方，伤害修正×1.2。
	// // 如果攻击方携带生命之玉，伤害修正×1.3。
	// // 如果防御方携带属性抗性树果，且攻击方的技能是对应属性并克制防御方，伤害修正×0.5。
	// // 如果技能是践踏或坚硬滚动，且防御方处于变小状态，伤害修正×2。
	// // 如果技能是地震，且防御方处于挖洞状态，伤害修正×2。
	// // 如果技能是冲浪，且防御方处于潜水状态，伤害修正×2。
	// }
	// return modify;
	// }
}