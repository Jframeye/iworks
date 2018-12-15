package com.xiaoye.iworks.basic.api.input.generate;

import com.xiaoye.iworks.core.api.input.Input;

/**
 * @author yehl
 * @date 2018/11/1 16:28
 */
public class TableDeleteInput extends Input {
    private static final long serialVersionUID = 8080945262692470292L;

    /** 主键 **/
    private Long pkid;

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }
}
