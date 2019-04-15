package com.baymin.restroomapi.service.impl;

import com.baymin.restroomapi.dao.AppointKeyDao;
import com.baymin.restroomapi.dao.BlacklistDao;
import com.baymin.restroomapi.dao.FishsDao;
import com.baymin.restroomapi.dao.specs.BlacklistSpecs;
import com.baymin.restroomapi.entity.Blacklist;
import com.baymin.restroomapi.entity.Fishs;
import com.baymin.restroomapi.ret.R;
import com.baymin.restroomapi.ret.enums.ResultEnum;
import com.baymin.restroomapi.ret.exception.MyException;
import com.baymin.restroomapi.service.FishsService;
import com.baymin.restroomapi.utils.ShellKit;
import com.baymin.restroomapi.utils.StreamGobblerCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FishsServiceImpl implements FishsService {

    @Autowired
    private FishsDao fishsDao;
    @Autowired
    private AppointKeyDao appointKeyDao;
    @Autowired
    private BlacklistDao blacklistDao;


    @Override
    public Object deleteById(String id) throws MyException {
        return R.callBackRet(fishsDao.findById(id), new R.OptionalResult() {
            @Override
            public Object onTrue(Object data) {
                fishsDao.deleteById(id);
                fishsDao.flush();
                return R.success();
            }
            @Override
            public Object onFalse() {
                return R.error(ResultEnum.FAIL_NO_PRODUCT);
            }
        });
    }

    @Override
    public Object updateStatusById(String id, Integer status) throws MyException {
        return R.callBackRet(fishsDao.findById(id), new R.OptionalResult() {
            @Override
            public Object onTrue(Object data) {
                Fishs fishs=(Fishs)data;
                fishs.setStatusType(status);
                if(fishsDao.save(fishs)!=null) return R.success();
                return R.error(ResultEnum.FAIL_ACTION_MESSAGE);
            }
            @Override
            public Object onFalse() {
                return R.error(ResultEnum.FAIL_NO_PRODUCT);
            }
        });
    }

    Object findBy(Integer appointKeyId, Optional<Integer> status, Pageable pageable){
        if(appointKeyId !=null){
            return R.callBackRet(status, new R.OptionalResult() {
                @Override
                public Object onTrue(Object data) {
                    Page<Fishs> retPage= fishsDao.findAllByAppointKey_IdAndAppointKeyStatus(appointKeyId, status.get(),pageable);//userDao.findAll(example,pageable);
                    if(retPage.getSize()>0)return R.success(retPage);else return R.error(ResultEnum.NO_LIST,retPage);
                }

                @Override
                public Object onFalse() {
                    Page<Fishs> retPage= fishsDao.findAllByAppointKey_Id(appointKeyId,pageable);//userDao.findAll(example,pageable);
                    if(retPage.getSize()>0)return R.success(retPage);else return R.error(ResultEnum.NO_LIST,retPage);
                }
            });
        }
        else {
            return R.callBackRet(status, new R.OptionalResult() {
                @Override
                public Object onTrue(Object data) {
                    Page<Fishs> retPage= fishsDao.findAllByAppointKeyStatus(status.get(), pageable);//userDao.findAll(example,pageable);
                    if(retPage.getSize()>0)return R.success(retPage);else return R.error(ResultEnum.NO_LIST,retPage);
                }

                @Override
                public Object onFalse() {
                    Page<Fishs> retPage= fishsDao.findAll(pageable);//userDao.findAll(example,pageable);
                    if(retPage.getSize()>0)return R.success(retPage);else return R.error(ResultEnum.NO_LIST,retPage);
                }
            });
        }
    }

    @Override
    public Object findAll(Optional<Integer> appointKeyId,Optional<Integer> status,Pageable pageable) throws MyException {
        return R.callBackRet(appointKeyId, new R.OptionalResult() {//有指定查询的关键词
            @Override
            public Object onTrue(Object dd) {
                return findBy(appointKeyId.get(), status, pageable);
            }
            @Override
            public Object onFalse() {
                return findBy(null, status, pageable);
            }
        });
    }

    @Override
    public Object findAllAppointKey() throws MyException {
        return R.success(appointKeyDao.findAll());
    }

    @Override
    public Object findAllBlacklist() throws MyException {
        return R.success(blacklistDao.findAll());
    }

    @Override
    public Object addBlacklist(String str, Integer type) throws MyException {
        Blacklist blacklist=new Blacklist();
        if(type==0) blacklist.setText(str);
        else blacklist.setUserNick(str);
        if(blacklistDao.save(blacklist)!=null)return R.success();
        return R.error(ResultEnum.FAIL_ACTION_MESSAGE);
    }
}
