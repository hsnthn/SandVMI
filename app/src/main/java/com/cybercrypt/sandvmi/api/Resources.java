package com.cybercrypt.sandvmi.api;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.cybercrypt.sandvmi.api.Status.ERROR;
import static com.cybercrypt.sandvmi.api.Status.SUCCESS;
import static com.cybercrypt.sandvmi.api.Status.LOADING;


public class Resources<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    private final String message;

    private Resources(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resources<T> success(@Nullable T data,@Nullable String msg) {
        return new Resources<>(SUCCESS, data, msg);
    }

    public static <T> Resources<T> error(@NonNull String msg, @Nullable T data) {
        return new Resources<>(ERROR, data, msg);
    }

    public static <T> Resources<T> loading(@Nullable T data) {
        return new Resources<>(LOADING, data, null);
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}