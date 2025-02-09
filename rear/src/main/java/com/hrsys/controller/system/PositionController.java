package com.hrsys.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrsys.pojo.entity.Position;
import com.hrsys.pojo.entity.Position;
import com.hrsys.pojo.entity.Result;
import com.hrsys.service.IPositionService;
import com.hrsys.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 职位表 前端控制器
 * </p>
 *
 * @author 
 * @since 2024-11-27
 */
@RestController
@RequestMapping("/position")
@RequiredArgsConstructor
@Api(tags = "职位管理")
@Validated
public class PositionController {

    private final IPositionService positionService;

    @PostMapping
    @ApiOperation(value = "添加职位")
    public Result<?> add(@RequestBody @Valid Position position) {
        positionService.save(position);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除职位")
    public Result<?> delete(@PathVariable @NotNull(message = "id不能为空") Integer id) {
        positionService.removeById(id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation(value = "修改职位")
    public Result<?> update(@RequestBody @Valid Position position) {
        positionService.updateById(position);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取职位列表")
    public Result<IPage<Position>> list(@RequestParam(defaultValue = "1") Integer current,
                                         @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Position> page = new Page<>(current, pageSize);
        return Result.success(positionService.page(page));
    }
}
