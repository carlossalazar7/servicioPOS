package com.consiti.serviceImplement;

import java.util.List;

import com.consiti.entity.TranHead;
import com.consiti.repository.TranHeadRepository;
import com.consiti.service.TranHeadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TranHeadServiceImp implements TranHeadService {

    @Autowired
    TranHeadRepository repository;

    @Override
    public List<TranHead> allTranHeads() {
        
        return repository.findAll();
    }

    @Override
    public TranHead getOneTranHead(Integer tranSeqNo) {
        // TODO Auto-generated method stub
        return repository.findById(tranSeqNo).get();
    }

    @Override
    public void updateTranHead(TranHead tranHead) {
        repository.save(tranHead);
    }

    @Override
    public boolean existeTranHead(Integer tranSeqNo) {
        return repository.existsById(tranSeqNo);
    }
    
}
