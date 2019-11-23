package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.ResponseResult;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
@Api(description ="讲师管理")
public class TeacherAdminController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师信息")
    @GetMapping
    public ResponseResult list(){
        List<Teacher> list = teacherService.list(null);
        return ResponseResult.ok().data("items", list);
    }

    @ApiOperation(value = "按ID删除讲师信息")
    @DeleteMapping("{id}")
    public ResponseResult removeById(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        teacherService.removeById(id);
        return ResponseResult.ok();
    }
    @ApiOperation(value = "分页查询讲师信息")
    @GetMapping("{page}/{limit}")
    public ResponseResult queryTeacherPageList(
            @ApiParam(name = "page",value = "当前页码",required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit",value = "页数",required = true)
            @PathVariable Long limit
    ){
        Page<Teacher> pageParam = new Page<>(page,limit);
        teacherService.page(pageParam,null);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return ResponseResult.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("page/{page}/{limit}")
    public ResponseResult pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery){

        Page<Teacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  ResponseResult.ok().data("total", total).data("rows", records);
    }
}
