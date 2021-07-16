package com.github.shuaidd.dto.oa.formcontrol;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述 附件控件
 *
 * @author ddshuai
 * @date 2021-07-16 14:25
 **/
public class FileFormControl implements ApplyFormControl {

    private List<FileData> files;

    public List<FileData> getFiles() {
        return files;
    }

    public void setFiles(List<FileData> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FileFormControl.class.getSimpleName() + "[", "]")
                .add("files=" + files)
                .toString();
    }

    @Override
    public String formControlName() {
        return "File";
    }
}
