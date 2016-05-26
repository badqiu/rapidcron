package com.github.rapidcron.enums;

import com.github.rapid.common.lang.enums.EnumBase;

public enum ClientStatus implements EnumBase<String>{
	ONLINE("在线"),OFFLINE("离线");

	private final String desc;
	
	private ClientStatus(String desc){
		this.desc = desc;
	}
	
	@Override
	public String getCode() {
		return name();
	}

	@Override
	public String getDesc() {
		return desc;
	}

}
