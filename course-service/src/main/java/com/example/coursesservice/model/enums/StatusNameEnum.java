package com.example.coursesservice.model.enums;

public enum StatusNameEnum {
  ENROLL, PRACTICE, CONTINUE;

    public StatusNameEnum getNext() {
        return this.ordinal() < StatusNameEnum.values().length - 1
                ? StatusNameEnum.values()[this.ordinal() + 1]
                : null;
    }

}
