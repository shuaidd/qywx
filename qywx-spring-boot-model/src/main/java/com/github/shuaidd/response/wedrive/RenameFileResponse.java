package com.github.shuaidd.response.wedrive;

import com.github.shuaidd.dto.wedrive.FileItem;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-02 22:10
 **/
public class RenameFileResponse extends AbstractBaseResponse {

    private FileItem file;

    public FileItem getFile() {
        return file;
    }

    public void setFile(FileItem file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RenameFileResponse.class.getSimpleName() + "[", "]")
                .add("file=" + file)
                .toString();
    }
}
