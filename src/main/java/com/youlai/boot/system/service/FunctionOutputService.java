package com.youlai.boot.system.service;

import com.youlai.boot.system.model.query.FunctionOutputQuery;
import com.youlai.boot.system.model.vo.FunctionOutputOverviewVO;

public interface FunctionOutputService {

    FunctionOutputOverviewVO getOverview(FunctionOutputQuery queryParams);
}
