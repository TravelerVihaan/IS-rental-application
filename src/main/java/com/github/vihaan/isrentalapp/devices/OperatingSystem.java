package com.github.vihaan.isrentalapp.devices;

import java.util.Arrays;

public enum OperatingSystem {
    Windows7("Windows 7"),
    Windows10("Windows 10"),
    Linux("Linux"),
    MacOS("MacOS"),
    NoOSInstalled("No operating system");

    private final String operatingSystemName;

    OperatingSystem(String operatingSystemName) {
        this.operatingSystemName = operatingSystemName;
    }

    public String getOperatingSystemName() {
        return operatingSystemName;
    }

    public static OperatingSystem createFromString(String operatingSystemName) {
        return Arrays.stream(OperatingSystem.values())
                .filter(operatingSystem -> operatingSystem.operatingSystemName.equalsIgnoreCase((operatingSystemName)))
                .findFirst()
                .orElse(OperatingSystem.NoOSInstalled);
    }
}
