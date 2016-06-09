package com.cdi.web.enums;

public enum CommadEnume {
	CMD1("to,client1",1),
	CMD2("to",2),
	CMD3("client3",3);
	private String label;
    private Integer code;
    
    public static String parse(int code) {
        for (CommadEnume theEnum : CommadEnume.values()) {
            if (theEnum.getCode() == code) {
                return theEnum.getLabel();
            }
        }
        return "";
    }
    
    CommadEnume() {
    }

    CommadEnume(String label, Integer code) {
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
