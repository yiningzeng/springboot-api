package com.baymin.restroomapi.controller;

import com.baymin.restroomapi.entity.Fishs;
import com.baymin.restroomapi.ret.exception.MyException;
import com.baymin.restroomapi.service.FishsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fishs")
@Slf4j
@Validated
@Api(description = "闲鱼记录接口")
public class FishsController {

    @Autowired
    private FishsService fishsService;


    @ApiOperation(value="更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "id", value = "闲鱼商品id",required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "status", required = true, value = "状态{0:朕已阅 1:未读 2:置顶}", dataType = "string", paramType = "query"),
    })
    @PatchMapping("/fishs/{id}")
    public Object update(@PathVariable(value = "id") Integer id,
                         @RequestParam(value = "status") Integer status)throws MyException{
        return fishsService.updateStatusById(id,status);
    }


    @ApiOperation(value="删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "authorization token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "id",value = "闲鱼商品id", required = true, dataType = "string",paramType = "path"),
    })
    @DeleteMapping(value = "/fishs/{id}")
    public Object deleteRestRoom(@PathVariable("id") Integer id)throws MyException{
        return fishsService.deleteById(id);
    }

    /**
     * 获取个人列表分页
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "获取产品列表[分页]", response = Fishs.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "appointKeyId", value = "查询的字段id",dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", defaultValue = "0", value = "页数,不传默认0", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", defaultValue = "100", value = "每页数量,不传默认100", required = true, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "sortType", value = "排序类型",defaultValue = "desc",required = false, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "sortField", value = "排序字段",defaultValue = "createTime",required = false, dataType = "string", paramType = "query")
    })
    @GetMapping(value = "/fishs/{appointKeyId}")
    public Object getRestRoomListByPage(
            @PathVariable(value = "appointKeyId", required = false) Integer appointKeyId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "100") @Min(value = 1, message = "值不能小于1") Integer size
//            @RequestParam(value = "sortType", defaultValue = "desc") String sortType,
//            @RequestParam(value = "sortField", defaultValue = "createTime") String sortField
    ) throws Exception {
        Sort sort=new Sort(Sort.Direction.DESC, "searchTime");
        sort.and(new Sort(Sort.Direction.DESC,"status"));
        return fishsService.findAll(Optional.ofNullable(appointKeyId), Optional.ofNullable(status), PageRequest.of(page,size,sort));
    }
}
