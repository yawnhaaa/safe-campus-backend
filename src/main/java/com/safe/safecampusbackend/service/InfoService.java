package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.InfoUserDTO;
import com.safe.safecampusbackend.model.vo.InfoListVO;
import com.safe.safecampusbackend.model.vo.InfoVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface InfoService {
    Result<List<InfoListVO>> getInfoList();
    Result<InfoVO> getInfoById(String id);
    Result<String> handleInfo(InfoUserDTO infoUserDTO);
}
