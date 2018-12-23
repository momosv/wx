package com.cn.xt.mp.base.util;


public class Constants {
    /**
     * 文件存储根路径
     */
    public static final String UPLOAD_DIR_ROOT = "/opt/upload-dir";

    /**
     * feedback '1 用户提交 2 人工登记',
     */
    public static final Integer FEEDBACK_COMPANY_USER_CREATE =1;
    public static final Integer FEED_BACK_HELPER_CREATE =2;

    // '0 提交，1转发，2 中介驳回，3接收受理，4受理单位驳回 ，5 评论',
    public  enum RECORD{

       PUT(0,"提交"),
       TRANSMI(1,"转发"),
       AGENCY_REBUT(2,"中介驳回"),
       ACCEPT (3,"受理"),
       UNIT_REBUT (4,"驳回"),
       UNIT_COMMENT (5,"什么评论也没留下");

        int code;
        String desc;
        RECORD(int _nCode, String _nDesc) {
            this.code = _nCode;
            this.desc = _nDesc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public static final String COMPANY_USER_TOKEN = "companyUserToken";
    public static final String COMPANY_USER = "companyUser";
    public static final String SYS_TOKEN = "sys";
    public static final String UNIT_USER_TOKEN = "unitUserToken";
    int MAX_FILE_UPLOAD_SIZE = 5242880;
   //全部-1,0未审批，1通过，2不通过，3已经审批
   public final static Integer USER_ALL_TYPE=-1;
   //0邮箱未验证,1是邮箱认证，2是待审批，3是审批通过，4是不通过
   public final static Integer USER_EMAIL_UN_IDENTIFY=0;
   public final static Integer USER_EMAIL_IDENTIFY=1;
   public final static Integer USER_UN_APPROVED=2;
   public final static Integer USER_PASSED=3;
   public final static Integer USER_UN_PASSED=4;

}
