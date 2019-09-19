package com.bbxyard.sfb.aspect.service;

import com.bbxyard.sfb.aspect.bo.MyHookBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MyHookService {

    public boolean save(MyHookBO myHookBO) {
        log.info("Hook Object: " + myHookBO.toString());
        return true;
    }

}
