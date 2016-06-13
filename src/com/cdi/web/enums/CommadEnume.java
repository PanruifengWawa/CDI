package com.cdi.web.enums;

public enum CommadEnume {
	/**
	 * “当前播放列表”,command值为1<br>
	 * 消息格式: {cmd:1,message:xxx}<br>
	 * 来源        :  <br>
	 * 接收        :
	 */
	MUSIC1(1,"当前播放列表"),
	/**
	 * “当前正在播放的音乐”,command值为2<br>
	 * 消息格式: {cmd:2,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	MUSIC2(2,"当前正在播放的音乐"),
	/**
	 * “当前音量”,command值为3<br>
	 * 消息格式: {cmd:3,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	MUSIC3(3,"当前音量"),
	/**
	 * “音乐的当前播放状态”,command值为4<br>
	 * 消息格式: {cmd:4,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	MUSIC4(4,"当前播放状态"),
	/**
	 * “是否是喜欢的歌曲”,command值为5<br>
	 * 消息格式: {cmd:5,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	MUSIC5(5,"是否是喜欢的歌曲"),
	/**
	 * “当前播放进度”,command值为6<br>
	 * 消息格式: {cmd:6,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	MUSIC6(6,"当前播放进度"),
	/**
	 * “选择上一首/下一首”,command值为7
	 * 消息格式: {cmd:7,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	MUSIC7(7,"选择上一首/下一首"),
	/**
	 * “选择上一个播放列表/下一个播放列表”,command值为8<br>
	 * 消息格式: {cmd:8,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	MUSIC8(8,"选择上一个播放列表/下一个播放列表"),
	/**
	 * “当前播放的电台”,command值为9<br>
	 * 消息格式: {cmd:9,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	RADIO1(9,"选择上一个播放列表/下一个播放列表"),
	/**
	 * “电台当前音量”,command值为10<br>
	 * 消息格式: {cmd:10,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	RADIO2(10,"当前电台音量"),
	/**
	 * “电台当前播放状态”,command值为11<br>
	 * 消息格式: {cmd:11,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	RADIO3(11,"电台当前播放状态"),
	/**
	 * “是否是喜欢的电台”,command值为12<br>
	 * 消息格式: {cmd:12,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	RADIO4(12,"是否是喜欢的电台"),
	/**
	 * “当前电台播放进度”,command值为13<br>
	 * 消息格式: {cmd:13,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	RADIO5(13,"当前电台播放进度"),
	/**
	 * “选择上一个/下一个电台”,command值为14<br>
	 * 消息格式: {cmd:14,message:xxx}<br>
	 * 来源        :<br>
	 * 接收        :
	 */
	RADIO6(14,"选择上一个/下一个电台"),
	/**
	 * “选择香氛类别，并打开香氛”,
	 * command值为15
	 */
	ATOSPHERE1(15,"选择香氛类别，并打开香氛"),
	/**
	 * “选择香氛类别”,
	 * command值为16
	 */
	ATOSPHERE2(16,"关闭香氛"),
	/**
	 * “设置香氛的强度”,
	 * command值为17
	 */
	ATOSPHERE3(17,"设置香氛的强度"),
	/**
	 * “选择氛围灯区域”,
	 * command值为18
	 */
	ATOSPHERE4(18,"选择氛围灯区域"),
	/**
	 * “氛围灯的开关”,
	 * command值为19
	 */
	ATOSPHERE5(19,"氛围灯的开关"),
	/**
	 * “氛围灯的颜色”,
	 * command值为20
	 */
	ATOSPHERE6(20,"氛围灯的颜色"),
	/**
	 * “氛围灯的亮度”,
	 * command值为21
	 */
	ATOSPHERE7(21,"氛围灯的亮度"),
	/**
	 * “空调温度”,
	 * command值为22
	 */
	ATOSPHERE8(22,"空调温度"),
	/**
	 * “空调模式”,
	 * command值为23
	 */
	ATOSPHERE9(23,"空调模式"),
	/**
	 * “座椅加热的开关”,
	 * command值为24
	 */
	ATOSPHERE10(25,"关闭香氛"),
	/**
	 * “座椅通风开关”,
	 * command值为26
	 */
	ATOSPHERE11(26,"座椅通风开关"),
	/**
	 * “氛围模式选择”,
	 * command值为27
	 */
	ATOSPHERE12(27,"氛围模式选择"),
	/**
	 * “氛围模式的开关”,
	 * command值为28
	 */
	ATOSPHERE13(28,"氛围模式的开关"),
	/**
	 * “建立新的氛围模式/删除氛围模式”,
	 * command值为29
	 */
	ATOSPHERE14(29,"建立新的氛围模式/删除氛围模式"),
	/**
	 * “分享音乐”,
	 * command值为30
	 */
	INTERCONNECT1(30,"分享音乐"),
	/**
	 * “分享电台”,
	 * command值为31
	 */
	INTERCONNECT2(31,"分享电台"),
	/**
	 * “分享氛围模式”,
	 * command值为32
	 */
	INTERCONNECT3(32,"分享氛围模式"),
	/**
	 * “同步移动设备”,
	 * command值为33
	 */
	INTERCONNECT4(33,"同步移动设备"),
	/**
	 * “选择联系人”,
	 * command值为34
	 */
	INTERCONNECT5(34,"选择联系人"),
	/**
	 * “接到电话”,
	 * command值为35
	 */
	INTERCONNECT6(35,"接到电话"),
	/**
	 * “收到短信”,
	 * command值为36
	 */
	INTERCONNECT7(36,"收到短信"),
	/**
	 * “拨出电话”,
	 * command值为37
	 */
	INTERCONNECT8(37,"拨出电话"),
	/**
	 * “发短信”,
	 * command值为38
	 */
	INTERCONNECT9(38,"发短信"),
	/**
	 * “接听/挂断电话”,
	 * command值为39
	 */
	INTERCONNECT10(39,"接听/挂断电话"),
	/**
	 * “忽略/阅读短信”,
	 * command值为40
	 */
	INTERCONNECT11(40,"忽略/阅读短信");
	
	private String label;
    private Integer code;
    public static CommadEnume parse(int code) {
        for (CommadEnume theEnum : CommadEnume.values()) {
            if (theEnum.getCode() == code) {
                return theEnum;
            }
        }
        return null;
    }
    
    CommadEnume() {
    }

    CommadEnume(Integer code, String label) {
        this.label = label;
        this.code = code;
    }

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
    
    
}
