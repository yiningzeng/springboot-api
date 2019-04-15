package com.baymin.restroomapi.service;

import com.baymin.restroomapi.ret.exception.MyException;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/**
 * Created by baymin
 * 2018-08-08 11:43
 */
public interface FishsService {

    /**
     * 删除
     * @param id
     * @return
     * @throws MyException
     */
    Object deleteById(String id) throws MyException;

    /**
     * 更新
     * @param id
     * @return
     * @throws MyException
     */
    Object updateStatusById(String id, Integer status) throws MyException;

    /**
     * 查找全部
     * @param pageable
     * @return
     * @throws MyException
     */
    Object findAll(Optional<Integer> appointKeyId, Optional<Integer> status, Pageable pageable)throws MyException;


    /**
     * 查找所有的key
     * @return
     * @throws MyException
     */
    Object findAllAppointKey()throws MyException;


    /**
     * 查询是否存在黑名单中
     * @return
     * @throws MyException
     */
    Object findAllBlacklist()throws MyException;

    /**
     * 查询是否存在黑名单中
     * @param str
     * @return
     * @throws MyException
     */
    Object addBlacklist(String str, Integer type)throws MyException;
}
