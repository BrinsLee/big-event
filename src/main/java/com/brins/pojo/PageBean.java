package com.brins.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by lipeilin on 2024/10/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {

    // 总数
    private Long total;
    // 数据集合
    private List<T> items;
}
