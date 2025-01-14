package net.somta.juggle.console.domain.domain.enums;

import net.somta.core.base.IBaseError;
import net.somta.juggle.common.constants.ApplicationConstants;

/**
 * @author husong
 */
public enum DomainErrorEnum implements IBaseError {
    DOMAIN_EXIST_API_ERROR(1000,  "该领域下存在接口，不能删除"),
    ;

    private int errorCode;
    private String errorMsg;

    DomainErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public long getErrorCode() {
        return ApplicationConstants.DOMAIN_CODE + errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
