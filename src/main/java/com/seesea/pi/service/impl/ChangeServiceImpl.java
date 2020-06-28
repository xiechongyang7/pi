package com.seesea.pi.service.impl;

import com.seesea.pi.base.BaseService;
import com.seesea.pi.base.exception.BizException;
import com.seesea.pi.common.Const;
import com.seesea.pi.service.ChangeService;
import com.seesea.pi.util.ShellUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/6/23 14:57
 */
@Service
public class ChangeServiceImpl extends BaseService implements ChangeService {

    @Value("${nginx.config.path.root}")
    private String path;
    @Value("${shell.path}")
    private String shellPath;
    @Override
    public void change(String type) throws BizException {

        try {
            File file = new File(path);
            List<String> logStrList = FileUtils.readLines(file);

            StringBuilder builder = new StringBuilder();

            if(Const.change_tpye_sql.equals(type)){
                for(String str:logStrList){
                    if(str.contains("sql")){
                        str = "#" + str;
                    }
                    if(str.contains("ssh")){
                        str = str.replaceAll("#","");
                    }
                    builder.append(str).append("\n");
                }
            }else {
                for(String str:logStrList){
                    if(str.contains("sql")){
                        str = str.replaceAll("#","");
                    }
                    if(str.contains("ssh")){
                        str = "#" + str;
                    }
                    builder.append(str).append("\n");
                }
            }

            FileUtils.writeStringToFile(file,builder.toString(),false);
            ShellUtil.runShell(shellPath);
        } catch (IOException e) {
           logger.error("错误",e);
           throw new BizException("11",e.getMessage());
        }


    }
}
