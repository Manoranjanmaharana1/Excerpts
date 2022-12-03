package com.pastebin.Dao;

import com.pastebin.enums.Response;
import com.pastebin.pojo.Paste;
import com.pastebin.pojo.PasteResponse;

import lombok.NonNull;

public interface DataBaseAccessor {
    public PasteResponse getPaste(@NonNull String id);

    public Response createPaste(@NonNull Paste pasteData);
}
