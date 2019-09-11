package com.fanhehe.codepiece.constant;

public enum LabelEnum {
    ALL("ALL", "all"),
    JAVA("JAVA", "Java"),
    NODEJS("NODEJS", "NodeJS"),
    JAVASCRIPT("JAVASCRIPT", "Javascript")
    ;

    public static LabelEnum getLabelByName(String name) {

        if (name == null) {
            return LabelEnum.ALL;
        }

        for(LabelEnum item: LabelEnum.values()) {
            if (name.equalsIgnoreCase(item.getName())) {
                return item;
            }
        }

        return LabelEnum.ALL;
    }

    private String name;
    private String description;

    LabelEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
