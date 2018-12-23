package com.cn.xt.mp.feedback.mobile;

import com.cn.xt.mp.base.baseController.BaseController;
import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.entity.Tips;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.base.util.RandomUtils;
import com.cn.xt.mp.feedback.dao.dao.TbFeedbackMapper;
import com.cn.xt.mp.feedback.dao.readonlydao.ReadonlyTbCompanyUserMapper;
import com.cn.xt.mp.feedback.dao.readonlydao.ReadonlyTbFeedbackMapper;
import com.cn.xt.mp.feedback.mpModel.*;
import com.cn.xt.mp.feedback.service.ICompanyService;
import com.cn.xt.mp.feedback.service.ICompanyUserService;
import com.cn.xt.mp.feedback.service.IFeedbackRecordService;
import com.cn.xt.mp.feedback.service.IFeedbackService;
import com.cn.xt.mp.feedback.util.WxImgUtil;
import com.cn.xt.mp.feedback.vo.CompanyUserVO;
import com.cn.xt.mp.feedback.vo.TbFeedbackVO;
import com.cn.xt.mp.feedback.wxSecurity.service.TempMaterialService;
import com.cn.xt.mp.feedback.wxSecurity.util.WXUtil;
import com.cn.xt.mp.feedback.wxSecurity.wxentity.AccessToken;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 15:40
 **/
@Api(value = "MpCompanyUserCtrl",tags = "企业用户操作",description = "企业/个人信息设置")
@RequestMapping("company/user")
@RestController
public class MpCompanyUserCtrl extends BaseController {


    @Autowired
    public TempMaterialService tempMaterialService;

    @Autowired
    public ICompanyUserService companyUserService;
    @Autowired
    public ICompanyService companyService;

    @Autowired
    public IFeedbackRecordService feedbackRecordService;
    @Autowired
    public IFeedbackService feedbackService;

    @Autowired
    public ReadonlyTbCompanyUserMapper readonlyTbCompanyUserMapper;
    @Autowired
    public ReadonlyTbFeedbackMapper readonlyTbFeedbackMapper;
    @Autowired
    public TbFeedbackMapper tbFeedbackMapper;

    @ApiOperation(value = "获取当前用户信息",notes = "获取当前用户信息",httpMethod = "GET")
    @RequestMapping("getCurrentUserDetail")
    public Msg getUserDetail() throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        return Msg.success().add(Constants.COMPANY_USER,userVO);
    }

    @ApiOperation(value = "更新用户信息",notes = "更新用户信息",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="主键id(非openid)",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="name",value="姓名",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="phone",value="手机号",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="email",value="email",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="address",value="地址",dataType="string", paramType = "query",required = true),
    })
    @RequestMapping("updateUserDetail")
    public Msg updateUserDetail(String id,String name,String phone,String email,String address) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        if(!userVO.getId().equals(id)){
            return Msg.fail("您不能修改他人信息");
        }
        TbCompanyUser companyUser = companyUserService.selectByPrimaryKey(TbCompanyUser.class, id);
        companyUser.setPhone(phone);
        companyUser.setName(name);
        companyUser.setAddress(address);
        companyUser.setEmail(email);
        companyUserService.updateOne(companyUser,true);
        return Msg.success();
    }

    @ApiOperation(value = "获取当前用户所属机构信息",notes = "获取当前用户所属机构信息",httpMethod = "GET")
    @RequestMapping("getUserCompanyDetail")
    public Msg getUserCompanyDetail() throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        TbCompany company = new TbCompany();
        if(!StringUtils.isEmpty(userVO.getCompanyId() )){
            company = companyUserService.selectByPrimaryKey(TbCompany.class,userVO.getCompanyId());
        }
        return Msg.success().add("company",company);
    }

    @ApiOperation(value = "根据信用代码获取机构信息",notes = "根据信用代码获取机构信息",httpMethod = "GET")
    @RequestMapping("getUserCompanyDetailByCode/{code}")
    public Msg getUserCompanyDetailByCode(@PathVariable String code) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        TbCompany company = new TbCompany();
        if(!StringUtils.isEmpty(code)){
            company = companyService.getCompanyByCode(code);
        }
        return Msg.success().add("company",company);
    }

    @ApiOperation(value = "加入已有机构（公司)",notes = "加入已有机构（公司)",httpMethod = "POST")
    @RequestMapping("joinCompany")
    public Msg joinCompany(@RequestParam(required = true) String companyId) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        TbCompany company  = companyUserService.selectByPrimaryKey(TbCompany.class,userVO.getCompanyId());
        if(null == company){
            return Msg.fail("加入失败，加入的机构不存在");
        }
        TbCompanyUser user = new TbCompanyUser();
        user.setId(userVO.getId());
        user.setCompanyId(companyId);
        companyUserService.updateOne(user,true);
        return Msg.success().add("company",company);
    }


    @ApiOperation(value = "新增或编辑所属公司",notes = "新增或编辑所属公司",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="编辑时候带上原有id,新增留空",dataType="string", paramType = "query"),
            @ApiImplicitParam(name="name",value="名称",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="socialCreditCode",value="社会统一信用码",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="linkman",value="法定代表人（负责人）",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="address",value="地址",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="await_code_img",value="营业执照（通过微信端提交，只需要serverId）",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name="await_company_img",value="公司照片（通过微信端提交，只需要serverId）",dataType="string", paramType = "query",required = true),
    })
    @RequestMapping("joinCompany")
    public Msg editCompany(TbCompany company ) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        WxSecurityPO security = WXUtil.getWxSecurityByAppId(userVO.getAppId());
        AccessToken accessToken = WXUtil.getAccessToken(userVO.getAppId());
        if(StringUtils.isEmpty(company.getSocialCreditCode())||StringUtils.isEmpty(company.getName())){
            return Msg.fail("社会统一信用码或者名称不能为空");
        }
        if(!StringUtils.isEmpty(company.getAwaitCodeImg())){
            String awaitCodeImg = WxImgUtil.saveImg(company.getAwaitCodeImg(),accessToken.getToken(),security.getDiyDomain());
            if(StringUtils.isEmpty(awaitCodeImg)){
                return Msg.fail("营业执照保存失败");
            }
            company.setAwaitCodeImg(awaitCodeImg);
        }
        if(!StringUtils.isEmpty(company.getAwaitCompanyImg())){
            String awaitCompanyImg =WxImgUtil.saveImg(company.getAwaitCodeImg(),accessToken.getToken(),security.getDiyDomain());
            if(StringUtils.isEmpty(awaitCompanyImg)){
                return Msg.fail("公司照片保存失败");
            }
            company.setAwaitCompanyImg(awaitCompanyImg);
        }


        TbCompany company0  = companyUserService.selectByPrimaryKey(TbCompany.class,company.getId());



        if(null == company0){//新增
                company0 = companyService.getCompanyByCode(company.getSocialCreditCode());
                if(company0!=null){
                    return Msg.fail("当前存在机构【"+company0.getName()+"】,请通过加入方式选择机构");
                }
            company.setId(RandomUtils.getUUID32());
//            company.setCodeImg(awaitCodeImg);
//            company.setCompanyImg(awaitCompanyImg);
            TbCompanyUser user = new TbCompanyUser();
            user.setId(userVO.getId());
            user.setCompanyId(company.getId());
            companyUserService.updateOne(user,true);
        }else{

        }

        return Msg.success().add("company",company);
    }


}
