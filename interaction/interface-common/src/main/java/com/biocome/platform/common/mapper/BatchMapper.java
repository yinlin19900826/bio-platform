package com.biocome.platform.common.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * mapper接口继承此接口可实现批量插入，根据id批量删除和批量查找
 * @param <T>
 */
public interface BatchMapper<T> extends IdsMapper<T>, InsertListMapper<T> {
}
