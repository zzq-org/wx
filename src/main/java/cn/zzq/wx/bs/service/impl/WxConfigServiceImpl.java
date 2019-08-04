package cn.zzq.wx.bs.service.impl;

import cn.zzq.wx.bs.service.WxConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class WxConfigServiceImpl implements WxConfigService {

}
