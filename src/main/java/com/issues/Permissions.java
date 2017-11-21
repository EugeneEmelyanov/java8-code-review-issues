package com.issues;

import java.util.Arrays;

public enum Permissions {
    ADMIN, USER, MANAGER;

    public Permissions getByType(final String strPermission) {
        return Arrays.stream(Permissions.values())
                .filter(permission -> permission.toString().equals(strPermission))
                .findFirst()
                .get();
    }
}
