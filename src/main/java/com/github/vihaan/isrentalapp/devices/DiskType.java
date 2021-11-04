package com.github.vihaan.isrentalapp.devices;

import java.util.Arrays;

public enum DiskType {
    HDD("HDD"), SSD("SSD"), NO_DISK("No disk");

    private final String diskType;

    DiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getDiskType() {
        return diskType;
    }

    public static DiskType createFromString(String diskTypeName) {
        return Arrays.stream(DiskType.values())
                .filter(diskType -> diskType.diskType.equalsIgnoreCase((diskTypeName)))
                .findFirst()
                .orElse(DiskType.NO_DISK);
    }
}
