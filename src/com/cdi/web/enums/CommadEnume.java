package com.cdi.web.enums;

public enum CommadEnume {
	PlayScentys(15,"play scentys"),
	ScentysIntensity(17,"scentys intensity"),
	Other(0,"other");

	
	private String label;
    private Integer code;
    public static CommadEnume parse(int code) {
        for (CommadEnume theEnum : CommadEnume.values()) {
            if (theEnum.getCode() == code) {
                return theEnum;
            }
        }
        return CommadEnume.Other;
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
