package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述 位置控件
 *
 * @author ddshuai
 * @date 2021-07-16 17:50
 **/
public class LocationFormControl implements ApplyFormControl {

    private LocationData location;

    public LocationData getLocation() {
        return location;
    }

    public void setLocation(LocationData location) {
        this.location = location;
    }

    @Override
    public String formControlName() {
        return "Location";
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LocationFormControl.class.getSimpleName() + "[", "]")
                .add("location=" + location)
                .toString();
    }
}
