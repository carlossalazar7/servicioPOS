package com.consiti.service;

import java.util.List;

import com.consiti.entity.TranHead;

public interface TranHeadService {
    
    public List<TranHead> allTranHeads();

    public TranHead getOneTranHead(Integer tranSeqNo);

    public void updateTranHead(TranHead tranHead);

    public boolean existeTranHead(Integer tranSeqNo);

}

