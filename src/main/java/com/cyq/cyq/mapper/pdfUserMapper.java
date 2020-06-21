package com.cyq.cyq.mapper;

import com.cyq.cyq.model.pdfUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface pdfUserMapper {

    int insertpdf(pdfUser pdfUser);
}
